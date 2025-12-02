package dev.whitespace.core.service.bundler.resolver;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.hibernate.cache.spi.entry.CacheEntry;

import dev.whitespace.core.service.bundler.domain.Reference;
import lombok.Getter;

public class ReferenceResolver {
	private final Integer MAX_CONCURRENT_REQUESTS = 50;
	private final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_REQUESTS);
	private final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();
	private final HttpClient client = HttpClient.newHttpClient();

	public Result resolve(List<Reference> references) {
		var result = new Result();

		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			for (Reference reference : references) {
				var cached = cache.get(reference.toString());
				if (cached != null) {
					continue;
				}

				semaphore.acquireUninterruptibly();

					var request = HttpRequest.newBuilder()
							.uri(reference.url())
							.method("GET", HttpRequest.BodyPublishers.noBody())
							.build();

					executor.submit(() -> {
						try {
							var response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
							var file = new InMemoryFile(
									reference.toString(),
									response.body(),
									response.headers().firstValue("content-type").orElse("application/octet-stream"));
							result.addFile(file);
							cache.put(reference.toString(), new CacheEntry(file, null));
						} catch (Exception e) {
							result.addFailedReference(reference);
							cache.put(reference.toString(), new CacheEntry(null, e.toString()));
						} finally {
							semaphore.release();
						}
					});
			}
		}
		catch (Exception e) {

		}

		return result;
	}


	@Getter
	public class Result {
		private final List<InMemoryFile> files = new ArrayList<>();
		private final List<Reference> failedReferences = new ArrayList<>();

		public void addFile(InMemoryFile file) {
			files.add(file);
		}

		public void addFailedReference(Reference failedReference) {
			failedReferences.add(failedReference);
		}
	}
	private record CacheEntry(InMemoryFile file, String error) {}
	public record InMemoryFile(String name, byte[] content, String contentType) {}
}
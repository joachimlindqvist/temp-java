package dev.whitespace.service.bundler.resolver;

import dev.whitespace.service.bundler.domain.Reference;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ReferenceResolver {
    private final Integer MAX_CONCURRENT_REQUESTS = 10;
    private final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_REQUESTS);
    private final HttpClient client = HttpClient.newHttpClient();

    public Result resolve(List<Reference> references) {
        var result = new Result();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (Reference reference : references) {
                if (result.has(reference)) {
                    continue;
                }

                executor.submit(() -> {
                    try {
                        semaphore.acquire();
                        var request = HttpRequest.newBuilder()
                                .uri(reference.url())
                                .method("GET", HttpRequest.BodyPublishers.noBody())
                                .build();
                        var response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

                        if (response.statusCode() >= 400) {
                            result.addFailedReference(reference);
                            return;
                        }

                        var file = new InMemoryFile(
                                reference.toString(),
                                response.body(),
                                response.headers().firstValue("content-type").orElse("application/octet-stream"));
                        result.addFile(reference, file);
                    } catch (Exception e) {
                        result.addFailedReference(reference);
                    } finally {
                        semaphore.release();
                    }
                });
            }
        } catch (Exception e) {

        }

        return result;
    }


    public static class Result {
        private final ConcurrentHashMap<String, InMemoryFile> filesMap = new ConcurrentHashMap<>();
        private final ConcurrentHashMap<String, Reference> failedReferencesMap = new ConcurrentHashMap<>();

        public Collection<InMemoryFile> files() {
            return filesMap.values();
        }

        public Collection<Reference> failedReferences() {
            return failedReferencesMap.values();
        }

        public void addFile(Reference reference, InMemoryFile file) {
            filesMap.put(reference.toString(), file);
        }

        public void addFailedReference(Reference failedReference) {
            failedReferencesMap.put(failedReference.toString(), failedReference);
        }

        public boolean has(Reference reference) {
            return filesMap.containsKey(reference.toString()) || failedReferencesMap.containsKey(reference.toString());
        }
    }

    public record InMemoryFile(String name, byte[] content, String contentType) {
    }
}
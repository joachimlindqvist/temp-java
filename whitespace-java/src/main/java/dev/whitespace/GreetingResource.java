package dev.whitespace;

import dev.whitespace.entity.Account;
import dev.whitespace.entity.AccountUser;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

@Path("/hello/{name}")
public class GreetingResource {

    @Inject
    PathResource<String> card;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Map<String, Integer> get(@PathParam("name") String name) {
        card.test();
        AccountUser.create("email@addresss.tld", Account.builder().build());
        var au = AccountUser.builder()
                .superadmin(false)
                .build();
        au.persist();
        return Map.of("hello", 1);
    }
}

@Dependent
class PathResource<T> {
    private T entity;

    @Inject
    private UriInfo uriInfo;

    public void test() {
        IO.println(uriInfo.getAbsolutePath());
    }
}

@Provider
class EntityConverterProvider implements ParamConverterProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(
            Class<T> rawType, Type genericType, Annotation[] annotations) {

        if (rawType.equals(String.class)) {
            return (ParamConverter<T>) new ParamConverter<String>() {
                @Override
                public String fromString(String idString) {
                    if (idString == null) return null;

                    try {
                        var id = Long.parseLong(idString);
                        return Long.toString(id);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("ID must be a number.", e);
                    }
                }

                @Override
                public String toString(String product) {
                    return product;
                }
            };
        }
        return null; // Let other providers handle other types
    }
}
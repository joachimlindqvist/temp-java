package org.acme;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;

@Path("/hello")
@RunOnVirtualThread
public class GreetingResource {
    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    @Inject
    GreeterService greeterService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public String hello(@Nonnull @QueryParam("name") String name) {
        greeterService.greet(name);
        LOG.info("LOGGGG");
        var ge = new GreetingEntity("hello from", name, LocalDateTime.now());
        ge.persist();

        LOG.info(GreetingEntity.listAll());
        return "hello from "+name;
    }
}

package dev.whitespace.controller;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/api/auth")
public class ApiAuthController {

    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_JSON)
    public PostTokenResponse postToken(PostTokenRequest body) {
        assertRequestBodyNotNull(body);
        IO.println("body");
        IO.println(body);
        return new PostTokenResponse("token");
    }

    public record PostTokenRequest(String email) {}
    public record PostTokenResponse(String token) {}

    <T> void assertRequestBodyNotNull(T body) {
        if (body == null) {
            throw new BadRequestException("invalid request body");
        }
    }
}

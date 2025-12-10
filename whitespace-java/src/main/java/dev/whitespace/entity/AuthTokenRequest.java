package dev.whitespace.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity(name = "auth_token_request")
@Builder
public class AuthTokenRequest extends WsPanacheEntity<AuthTokenRequest> {
    public Instant validUntil;
    public String email;
    public String key;
    public Boolean resolved;
    public Boolean invalidated;

    Boolean isResolvable() {
        if (resolved) {
            return false;
        }
        if (invalidated) {
            return false;
        }

        return validUntil.isBefore(Instant.now());
    }

//    INSERT INTO
//    auth_token_requests (
//            valid_until,
//            email,
//            key,
//            resolved,
//            invalidated,
//            created_at
//            )
//    VALUES
//            (
//                    $1,
//                    LOWER($2),
//    $3,
//            false,
//            false,
//    CURRENT_TIMESTAMP
//    ) RETURNING id, created_at, deleted_at, valid_until, email, key, resolved, invalidated
    public static AuthTokenRequest create(String email, Instant validUntil, String key) {
        return AuthTokenRequest.builder()
                .email(email)
                .validUntil(validUntil)
                .key(key)
                .build()
                .persistAndReturn();

    }
}

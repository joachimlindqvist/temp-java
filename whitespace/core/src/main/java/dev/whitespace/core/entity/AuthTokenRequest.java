package dev.whitespace.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity(name = "auth_token_request")
@Getter
@Setter
@Builder
public class AuthTokenRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    private Instant createdAt;
    private Instant deletedAt;

    private Instant validUntil;
    private String email;
    private String key;
    private Boolean resolved;
    private Boolean invalidated;

    Boolean isResolvable() {
        if (resolved) {
            return false;
        }
        if (invalidated) {
            return false;
        }

        return validUntil.isBefore(Instant.now());
    }
}

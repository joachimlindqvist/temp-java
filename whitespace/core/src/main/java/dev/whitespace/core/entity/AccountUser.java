package dev.whitespace.core.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity(name = "account_users")
@Getter
@Setter
@Builder
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    private Instant createdAt;
    private Instant deletedAt;
    private String email;
    private Integer accountId;
    private Boolean superadmin;
}

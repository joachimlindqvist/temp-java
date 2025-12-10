package dev.whitespace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.jspecify.annotations.Nullable;

import java.time.Instant;
import java.util.List;

@Entity(name = "account_teams")
@Builder
public class AccountTeam extends WsPanacheEntity<AccountTeam> {
    public String name;
    @ManyToOne
    public AccountUser createdBy;
    public @Nullable String password;

    public static List<AccountTeam> byCreatedBy(AccountUser createdBy) {
        return list("created_by = ?1", createdBy);
    }
}

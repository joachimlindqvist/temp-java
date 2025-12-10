package dev.whitespace.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity(name = "account_users")
@Builder
public class AccountUser extends WsPanacheEntity<AccountUser> {
    public String email;
    @ManyToOne
    public Account account;
    public Boolean superadmin;

    public Boolean memberOfAnyTeam() {
        return !this.createdTeams().isEmpty();
    }

    public List<AccountTeam> createdTeams() {
        return AccountTeam.byCreatedBy(this);
    }

    public static List<AccountUser> all() {
        return listAll();
    }

    public static AccountUser byAccountId(Integer accountId) {
        return find("account_id = ?1", accountId).firstResult();
    }

    public static AccountUser byEmail(String email) {
        return find("lower(email) = lower(?1)", email).firstResult();
    }

    public static AccountUser create(String email, Account account) {
        return AccountUser.builder()
//                .account(account)
                .email(email.toLowerCase())
                .build()
                .persistAndReturn();
    }
}

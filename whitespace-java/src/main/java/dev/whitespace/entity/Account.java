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

//ID                 int32
//CreatedAt          time.Time
//DeletedAt          sql.NullTime
//StripeCustomerID   sql.NullString
//SubscriptionActive bool

@Entity(name = "accounts")
@Builder
public class Account extends WsPanacheEntity<Account> {
    public @Nullable String stripeCustomerId;
    public Boolean subscriptionActive;
}

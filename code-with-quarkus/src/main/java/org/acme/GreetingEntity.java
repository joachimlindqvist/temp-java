package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class GreetingEntity extends PanacheEntity {
    public String phrase;
    public String name;
    public LocalDateTime createdAt;

    public GreetingEntity() {
        this(null, null, null);
    }

    public GreetingEntity(String phrase, String name, LocalDateTime createdAt) {
        this.phrase = phrase;
        this.name = name;
        this.createdAt = createdAt;
    }
}

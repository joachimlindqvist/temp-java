package dev.whitespace.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

public class WsPanacheEntity<T> extends PanacheEntity  {
    @CreationTimestamp
    public Instant createdAt;
    public Instant deletedAt;

    public T persistAndReturn() {
        this.persist();
        return (T)this;
    }
}

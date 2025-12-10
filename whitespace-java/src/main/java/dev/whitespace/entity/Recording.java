package dev.whitespace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.jspecify.annotations.Nullable;

import java.time.Instant;

@Entity(name = "recordings")
@Builder
public class Recording extends WsPanacheEntity<Recording> {
    public String pid;
    public String title;
    @OneToOne
    public RecordingSource source;
    @ManyToOne
    public Account account;
    public String recordablesInput;
    public String recordables;
    public String controller;
    public String playback;
    @ManyToOne
    public AccountUser createdBy;
    @OneToOne
    public RecordingMeta meta;
    public Status status;
    public Boolean migratedToObjectStorage;

    public enum Status {
        Pending,
        Done,
        Error
    }
}

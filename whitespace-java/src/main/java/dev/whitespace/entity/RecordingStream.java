package dev.whitespace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "recording_streams")
@Builder
public class RecordingStream extends WsPanacheEntity<RecordingStream> {
    @OneToOne
    public Recording recording;
    public String streamId;
    public String stream;
}

package dev.whitespace.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

@Entity(name = "recording_metas")
@Builder
public class RecordingMeta extends WsPanacheEntity<RecordingMeta> {
    public Float trimmedStart;
    public Float trimmedEnd;
    public Float windowSizeWidth;
    public Float windowSizeHeight;
    public Integer duration;
    public @Nullable String location;
}

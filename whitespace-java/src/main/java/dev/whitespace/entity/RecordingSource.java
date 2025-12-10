package dev.whitespace.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

@Entity(name = "recording_sources")
@Builder
public class RecordingSource extends WsPanacheEntity<RecordingSource> {
    public String browser;
    public String browserVersion;
    public String os;
    public String osVersion;
    public String device;
    public String deviceType;
    public String full;
}

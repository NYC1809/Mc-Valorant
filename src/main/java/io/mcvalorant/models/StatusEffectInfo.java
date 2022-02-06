package io.mcvalorant.models;

import java.time.Duration;
import java.time.LocalDateTime;

public class StatusEffectInfo {

    private final LocalDateTime startTime;
    private final Duration duration;
    private final int value;

    public StatusEffectInfo(LocalDateTime startTime, Duration duration, int value) {
        this.startTime = startTime;
        this.duration = duration;
        this.value = value;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getValue() {
        return value;
    }
}

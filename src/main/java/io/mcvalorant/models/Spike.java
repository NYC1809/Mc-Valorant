package io.mcvalorant.models;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Spike {

    public Boolean isPlanted;
    public String spikeHolder;
    public LocalDateTime timeWhenSpikePlanted;
    public LocalDateTime timeWhenSpikeDefused;
    public LocalDateTime timeWhenSpikeExploded;
    public Double explosionRange;
    public Location spikelocation;


    public Spike(Boolean isPlanted, String spikeHolder, Double explosionRange, Location spikelocation) {
        this.isPlanted = isPlanted;
        this.spikeHolder = spikeHolder;
        this.explosionRange = explosionRange;
        this.spikelocation = spikelocation;
    }

    public Boolean ifPlanted() {
        return isPlanted;
    }

    public void setPlanted(Boolean isPlanted) {
        this.isPlanted = isPlanted;
    }

    public String getSpikeHolder() {
        return spikeHolder;
    }

    public void setSpikeHolder(String spikeHolder) {
        this.spikeHolder = spikeHolder;
    }

    public LocalDateTime getTimeWhenSpikePlanted() {
        return timeWhenSpikePlanted;
    }

    public void setTimeWhenSpikePlanted(LocalDateTime timeWhenSpikePlanted) {
        this.timeWhenSpikePlanted = timeWhenSpikePlanted;
    }

    public LocalDateTime getTimeWhenSpikeDefused() {
        return  timeWhenSpikeDefused;
    }

    public void setTimeWhenSpikeDefused(LocalDateTime timeWhenSpikeDefused) {
        this.timeWhenSpikeDefused = timeWhenSpikeDefused;
    }

    public LocalDateTime getTimeWhenSpikeExploded() {
        return timeWhenSpikeExploded;
    }

    public void setTimeWhenSpikeExploded(LocalDateTime timeWhenSpikeExploded) {
        this.timeWhenSpikeExploded = timeWhenSpikeExploded;
    }

    public Double getExplosionRange() {
        return explosionRange;
    }

    public void setExplosionRange(Double explosionRange) {
        this.explosionRange = explosionRange;
    }

    public Location getSpikelocation() {
        return spikelocation;
    }

    public void setSpikelocation(Location spikelocation) {
        this.spikelocation = spikelocation;
    }

}

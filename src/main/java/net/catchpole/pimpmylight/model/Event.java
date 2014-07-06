package net.catchpole.pimpmylight.model;

import java.util.Date;

public class Event {
    private final RailwaySignal railwaySignal;
    private final Date date;

    public Event(RailwaySignal railwaySignal) {
        this.railwaySignal = railwaySignal;
        this.date = new Date();
    }

    public Event(RailwaySignal railwaySignal, Date date) {
        this.railwaySignal = railwaySignal;
        this.date = date;
    }

    public RailwaySignal getRailwaySignal() {
        return railwaySignal;
    }

    public Date getDate() {
        return date;
    }
}

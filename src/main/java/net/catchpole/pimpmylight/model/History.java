package net.catchpole.pimpmylight.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class History implements Iterable<Event> {
    private final int maxEvents;
    private final List<Event> events = new ArrayList<Event>();

    public History(int maxEvents) {
        this.maxEvents = maxEvents;
    }

    public void addEvent(Event event) {
        if (events.size() > maxEvents) {
            events.remove(0);
        }
        this.events.add(0, event);
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}

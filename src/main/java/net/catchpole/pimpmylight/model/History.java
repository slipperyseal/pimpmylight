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
        this.events.add(0, event);
        if (this.events.size() > maxEvents) {
            events.remove(this.events.size()-1);
        }
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}

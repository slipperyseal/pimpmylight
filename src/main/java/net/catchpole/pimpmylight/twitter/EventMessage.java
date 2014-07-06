package net.catchpole.pimpmylight.twitter;

import net.catchpole.pimpmylight.model.Event;
import net.catchpole.pimpmylight.model.History;
import net.catchpole.pimpmylight.model.Light;
import net.catchpole.pimpmylight.model.RailwaySignal;

import java.util.Iterator;
import java.util.Random;

public class EventMessage {
    private String[] exclaim = { "ZOMG", "Zang", "Cool", "Here I go", "Ladies and gentlemen" };
    private Random random = new Random();
    private long longTime;

    public EventMessage(long longTime) {
        this.longTime = longTime;
    }

    public String generateMessage(History history) {
        Iterator<Event> iterator = history.iterator();
        Event last = null;
        Event secondLast = null;
        if (iterator.hasNext()) {
            last = iterator.next();
        }
        if (iterator.hasNext()) {
            secondLast = iterator.next();
        }
        if (last == null || secondLast == null) {
            return null;
        }

        long dif = last.getDate().getTime() - secondLast.getDate().getTime();
        if (dif > longTime) {
            StringBuilder sb = new StringBuilder();
            sb.append(random(exclaim) + ". I have been idle for " + (dif/1000/60) +
                    " minutes but now someone has set me to");
            for (Light light : last.getRailwaySignal()) {
                sb.append(' ');
                sb.append(light.getName());
                sb.append(' ');
                sb.append(light.isIlluminated() ? "on" : "off");
            }
            sb.append(".");
            return sb.toString();
        }
        return null;
    }

    private String random(String[] strings) {
        return strings[random.nextInt(strings.length)];
    }

    private Light getChangedLight(RailwaySignal railwaySignal1, RailwaySignal railwaySignal2) {
        for (Light light1 : railwaySignal1) {
            for (Light light2 : railwaySignal2) {
                if (light1.getName().equals(light2.getName())) {
                    if (light1.isIlluminated() != light2.isIlluminated()) {
                        return light2;
                    }
                }
            }
        }
        return railwaySignal2.iterator().next();
    }
}
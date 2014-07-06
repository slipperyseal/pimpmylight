package net.catchpole.twitter;

import junit.framework.TestCase;
import net.catchpole.pimpmylight.model.Event;
import net.catchpole.pimpmylight.model.History;
import net.catchpole.pimpmylight.model.Light;
import net.catchpole.pimpmylight.model.RailwaySignal;
import net.catchpole.pimpmylight.twitter.EventMessage;
import org.junit.Test;

import java.util.Date;

public class EventMessageTest {
    @Test
    public void test() {
        RailwaySignal railwaySignal = new RailwaySignal();
        railwaySignal.addLight(new Light("red", true));
        railwaySignal.addLight(new Light("orange", true));
        railwaySignal.addLight(new Light("green", true));

        History history = new History(1000);

        EventMessage eventMessage = new EventMessage(1000);
        TestCase.assertNull(eventMessage.generateMessage(history));

        history.addEvent(new Event(railwaySignal,new Date(1000000000)));
        TestCase.assertNull(eventMessage.generateMessage(history));

        history.addEvent(new Event(railwaySignal,new Date(1001000000)));
        history.addEvent(new Event(railwaySignal,new Date(1002000000)));
        history.addEvent(new Event(railwaySignal,new Date(1003000000)));
        history.addEvent(new Event(railwaySignal,new Date(1004000000)));
        history.addEvent(new Event(railwaySignal,new Date(1005000000)));

        TestCase.assertNotNull(eventMessage.generateMessage(history));
        System.out.println(eventMessage.generateMessage(history));

    }
}

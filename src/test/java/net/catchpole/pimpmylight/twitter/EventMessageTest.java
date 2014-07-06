package net.catchpole.pimpmylight.twitter;

//   Copyright 2014 catchpole.net
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

import junit.framework.TestCase;
import net.catchpole.pimpmylight.model.Event;
import net.catchpole.pimpmylight.model.History;
import net.catchpole.pimpmylight.model.Light;
import net.catchpole.pimpmylight.model.RailwaySignal;
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

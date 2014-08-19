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

import net.catchpole.pimpmylight.model.Event;
import net.catchpole.pimpmylight.model.History;
import net.catchpole.pimpmylight.model.Light;
import net.catchpole.pimpmylight.model.RailwaySignal;

import java.util.Iterator;
import java.util.Random;

public class EventMessage {
    private String[] exclaim = { "ZOMG!", "Zang.", "Cool.", "Here I go.", "Ladies and gentlemen,", "WOOPWOOPWOOP!", ":D", ":O", "Who was that?" };
    private String[] nowIm = { "Now I'm", "My lights are now", "I'm all", "Behold mah", "Check it out."};
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

            sb.append(random(exclaim));
            sb.append(" I have been idle for ");
            sb.append(dif/1000/60);
            sb.append(" minutes but now someone has changed my ");
            sb.append(getChangedLight(last.getRailwaySignal(), secondLast.getRailwaySignal()).getName());
            sb.append(" signal. ");
            sb.append(random(nowIm));
            for (Light light : last.getRailwaySignal()) {
                sb.append(' ');
                sb.append(light.getName());
                sb.append(':');
                sb.append(light.isIlluminated() ? "on" : "off");
            }
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

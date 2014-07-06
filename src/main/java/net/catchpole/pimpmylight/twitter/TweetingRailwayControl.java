package net.catchpole.pimpmylight.twitter;

import net.catchpole.pimpmylight.control.RailwaySignalControl;
import net.catchpole.pimpmylight.model.Event;
import net.catchpole.pimpmylight.model.History;
import net.catchpole.pimpmylight.model.RailwaySignal;
import net.catchpole.silicone.lang.Throw;

import java.util.Date;

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

public class TweetingRailwayControl implements RailwaySignalControl {
    private History history = new History(1000);
    private EventMessage eventMessage = new EventMessage(1000*60*30);
    private TwitterClient twitterClient;
    private int total;

    public TweetingRailwayControl() {
        try {
            this.twitterClient = new TwitterClient();
        } catch (Exception e) {
            throw Throw.unchecked(e);
        }
    }

    @Override
    public void updateRailwaySignal(RailwaySignal railwaySignal) {
        if (total == 0) {
            twitterClient.tweet("My Tomcat instance has just restarted. Server time is " + new Date() + ". Hooray!");
        }
        total++;

        history.addEvent(new Event(railwaySignal));
        String message = eventMessage.generateMessage(history);
        if (message != null) {
            twitterClient.tweet(message);
        }
    }
}

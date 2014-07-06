package net.catchpole.pimpmylight.twitter;

import net.catchpole.pimpmylight.control.RailwaySignalControl;
import net.catchpole.pimpmylight.model.Event;
import net.catchpole.pimpmylight.model.History;
import net.catchpole.pimpmylight.model.RailwaySignal;
import net.catchpole.silicone.lang.Throw;

import java.util.Date;

public class TweetingRailwayControl implements RailwaySignalControl {
    private History history = new History(1000);
    private EventMessage eventMessage = new EventMessage(1000*60*30);
    private Tweet tweet;
    private int total;

    public TweetingRailwayControl() {
        try {
            this.tweet = new Tweet();
        } catch (Exception e) {
            throw Throw.unchecked(e);
        }
    }

    @Override
    public void updateRailwaySignal(RailwaySignal railwaySignal) {
        if (total == 0) {
            tweet.tweet("My Tomcat instance has just restarted. Server time is " + new Date() + ". Hooray!");
        }
        total++;

        history.addEvent(new Event(railwaySignal));
        String message = eventMessage.generateMessage(history);
        if (message != null) {
            tweet.tweet(message);
        }
    }
}

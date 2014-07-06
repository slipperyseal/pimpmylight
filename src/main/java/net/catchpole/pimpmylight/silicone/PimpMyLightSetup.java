package net.catchpole.pimpmylight.silicone;

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

import net.catchpole.pimpmylight.FatController;
import net.catchpole.pimpmylight.control.PiRailwaySignalControl;
import net.catchpole.pimpmylight.control.RailwaySignalControl;
import net.catchpole.pimpmylight.model.RailwaySignal;
import net.catchpole.pimpmylight.silicone.action.UpdateAction;
import net.catchpole.pimpmylight.silicone.artefact.Change;
import net.catchpole.pimpmylight.silicone.artefact.Status;
import net.catchpole.pimpmylight.silicone.artefact.Update;
import net.catchpole.pimpmylight.silicone.artefact.Watch;
import net.catchpole.pimpmylight.twitter.TweetingRailwayControl;
import net.catchpole.silicone.SiliconeConfig;
import net.catchpole.silicone.SiliconeSetup;
import net.catchpole.silicone.action.Action;
import net.catchpole.silicone.action.Artefacts;
import net.catchpole.silicone.async.AsyncGroup;

public class PimpMyLightSetup implements SiliconeSetup {
    private FatController fatController = new FatController();

    public PimpMyLightSetup() {
        fatController.setHardwarelRailwaySignalControl(new PiRailwaySignalControl());
    }

    public void setupSilicon(SiliconeConfig siliconeConfig) {
        siliconeConfig.addAction(new UpdateAction(this.fatController));
        siliconeConfig.registerArtefact(Update.class);
        siliconeConfig.registerArtefact(Watch.class);
        siliconeConfig.registerArtefact(Change.class);

        siliconeConfig.setGlobalAction(new Action() {
            public void perform(Object object, Artefacts artefacts) {
                artefacts.set(new Update());
            }
        });

        siliconeConfig.setStatelessAction(new Action() {
            public void perform(Object object, Artefacts artefacts) {
                artefacts.set(new Status(fatController));
            }
        });

        siliconeConfig.setSessionAction(new Action() {
            public void perform(Object object, Artefacts artefacts) {
            }
        });

        final AsyncGroup<RailwaySignal> asyncGroup = new AsyncGroup<RailwaySignal>();
        this.fatController.addObserverRailwaySignalControl(new RailwaySignalControl() {
            @Override
            public void updateRailwaySignal(RailwaySignal railwaySignal) {
                asyncGroup.notifyAllListeners(railwaySignal);
            }
        });
        this.fatController.addObserverRailwaySignalControl(new TweetingRailwayControl());

        siliconeConfig.registerGlobalEndpoint(new Change(this.fatController));
        siliconeConfig.registerGlobalEndpoint(new Watch(asyncGroup));
    }
}

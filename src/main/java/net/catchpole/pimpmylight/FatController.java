package net.catchpole.pimpmylight;

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

import net.catchpole.pimpmylight.control.RailwaySignalControl;
import net.catchpole.pimpmylight.model.Light;
import net.catchpole.pimpmylight.model.RailwaySignal;

public class FatController {
    private RailwaySignalControl actualRailwaySignalControl;
    private RailwaySignalControl observerRailwaySignalControl;
    private RailwaySignal railwaySignal = blankLight();
    private boolean sleep = false;

    public FatController() {
    }

    public RailwaySignal getStatus() {
        return railwaySignal;
    }

    public synchronized void sleepMode(boolean sleep) {
        this.sleep = sleep;
        if (sleep && this.actualRailwaySignalControl != null) {
            this.actualRailwaySignalControl.updateRailwaySignal(blankLight());
        }
    }

    public synchronized void change(String name) {
        for (Light light : railwaySignal) {
            if (light.getName().equals(name)) {
                this.railwaySignal = new RailwaySignal(this.railwaySignal, new Light(name, !light.isIlluminated()));
                notifyAllListeners();
                return;
            }
        }
    }

    private void notifyAllListeners() {
        if (!sleep && this.actualRailwaySignalControl != null) {
            actualRailwaySignalControl.updateRailwaySignal(this.railwaySignal);
        }

        if (observerRailwaySignalControl != null) {
            observerRailwaySignalControl.updateRailwaySignal(this.railwaySignal);
        }
    }

    public void setActualRailwaySignalControl(RailwaySignalControl actualRailwaySignalControl) {
        this.actualRailwaySignalControl = actualRailwaySignalControl;
    }

    public void setObserverRailwaySignalControl(RailwaySignalControl actualRailwaySignalControl) {
        this.observerRailwaySignalControl = actualRailwaySignalControl;
    }

    private RailwaySignal blankLight() {
        RailwaySignal railwaySignal = new RailwaySignal();
        railwaySignal.addLight(new Light("green", false));
        railwaySignal.addLight(new Light("orange", false));
        railwaySignal.addLight(new Light("red", false));
        return railwaySignal;
    }
}

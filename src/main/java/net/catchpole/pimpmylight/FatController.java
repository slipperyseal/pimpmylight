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

import net.catchpole.pimpmylight.control.DispatchingRailwaySignalControl;
import net.catchpole.pimpmylight.control.RailwaySignalControl;
import net.catchpole.pimpmylight.model.Light;
import net.catchpole.pimpmylight.model.RailwaySignal;

public class FatController {
    private RailwaySignalControl hardwarelRailwaySignalControl;
    private DispatchingRailwaySignalControl dispatchingRailwaySignalControl = new DispatchingRailwaySignalControl();
    private RailwaySignal currentRailwaySignal = blankLight();
    private boolean sleep = false;

    public FatController() {
    }

    public RailwaySignal getStatus() {
        return currentRailwaySignal;
    }

    public synchronized void sleepMode(boolean sleep) {
        this.sleep = sleep;
        if (this.hardwarelRailwaySignalControl != null) {
            if (sleep) {
                this.hardwarelRailwaySignalControl.updateRailwaySignal(blankLight());
            } else {
                this.hardwarelRailwaySignalControl.updateRailwaySignal(currentRailwaySignal);
            }
        }
    }

    public synchronized RailwaySignal change(String name) {
        for (Light light : currentRailwaySignal) {
            if (light.getName().equals(name)) {
                this.currentRailwaySignal = new RailwaySignal(this.currentRailwaySignal, new Light(name, !light.isIlluminated()));
                notifyAllListeners();
                return this.currentRailwaySignal;
            }
        }
        return this.currentRailwaySignal;
    }

    private void notifyAllListeners() {
        if (!sleep && this.hardwarelRailwaySignalControl != null) {
            hardwarelRailwaySignalControl.updateRailwaySignal(this.currentRailwaySignal);
        }
        dispatchingRailwaySignalControl.updateRailwaySignal(this.currentRailwaySignal);
    }

    public void setHardwarelRailwaySignalControl(RailwaySignalControl hardwarelRailwaySignalControl) {
        this.hardwarelRailwaySignalControl = hardwarelRailwaySignalControl;
    }

    public void addObserverRailwaySignalControl(RailwaySignalControl observerRailwaySignalControl) {
        this.dispatchingRailwaySignalControl.add(observerRailwaySignalControl);
    }

    private RailwaySignal blankLight() {
        RailwaySignal railwaySignal = new RailwaySignal();
        railwaySignal.addLight(new Light("green", false));
        railwaySignal.addLight(new Light("orange", false));
        railwaySignal.addLight(new Light("red", false));
        return railwaySignal;
    }
}

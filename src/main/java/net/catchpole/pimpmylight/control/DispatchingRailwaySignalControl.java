package net.catchpole.pimpmylight.control;

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

import net.catchpole.pimpmylight.model.RailwaySignal;

import java.util.HashMap;
import java.util.Map;

public class DispatchingRailwaySignalControl<K> implements RailwaySignalControl {
    private Map<K,RailwaySignalControl> railwaySignalControls = new HashMap<K, RailwaySignalControl>();

    public void add(K key, RailwaySignalControl railwaySignalControl) {
        this.railwaySignalControls.put(key, railwaySignalControl);
    }

    public void remove(K key) {
        this.railwaySignalControls.remove(key);
    }

    @Override
    public void updateRailwaySignal(RailwaySignal railwaySignal) {
        for (RailwaySignalControl railwaySignalControl : this.railwaySignalControls.values()) {
            railwaySignalControl.updateRailwaySignal(railwaySignal);
        }
    }
}

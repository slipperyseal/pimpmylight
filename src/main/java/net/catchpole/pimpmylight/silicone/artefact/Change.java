package net.catchpole.pimpmylight.silicone.artefact;

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
import net.catchpole.pimpmylight.model.Light;
import net.catchpole.pimpmylight.model.RailwaySignal;
import net.catchpole.silicone.action.Endpoint;
import net.catchpole.silicone.action.RequestDetails;

public class Change implements Endpoint<Light,RailwaySignal> {
    private FatController fatController;

    public Change(FatController fatController) {
        this.fatController = fatController;
    }

    @Override
    public RailwaySignal handle(Light light, RequestDetails requestDetails) {
        if (light == null) {
            return null;
        }
        return fatController.change(light.getName());
    }
}

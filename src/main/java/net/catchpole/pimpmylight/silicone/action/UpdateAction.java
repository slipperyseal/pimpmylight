package net.catchpole.pimpmylight.silicone.action;

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
import net.catchpole.pimpmylight.lang.PropertiesFile;
import net.catchpole.pimpmylight.silicone.artefact.Update;
import net.catchpole.silicone.action.Action;
import net.catchpole.silicone.action.Artefacts;
import net.catchpole.silicone.action.annotation.AllowGetRequest;

@AllowGetRequest
public class UpdateAction implements Action<Update> {
    private final FatController fatController;
    private final PropertiesFile propertiesFile = new PropertiesFile("pimpmylight.properties");
    private String sleepCommand = propertiesFile.getProperty("pimp.sleep");
    private String wakeCommand = propertiesFile.getProperty("pimp.wake");

    public UpdateAction(FatController fatController) {
        this.fatController = fatController;
    }

    public void perform(Update update, Artefacts artefacts) {
        if (update.getUpdate().equals(sleepCommand)) {
            fatController.sleepMode(true);
        } else if (update.getUpdate().equals(wakeCommand)) {
            fatController.sleepMode(false);
        } else {
            fatController.change(update.getUpdate());
        }
    }
}

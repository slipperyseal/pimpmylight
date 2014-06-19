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

import com.pi4j.io.gpio.*;
import net.catchpole.pimpmylight.model.Light;
import net.catchpole.pimpmylight.model.RailwaySignal;

import java.util.HashMap;
import java.util.Map;

public class PiRailwaySignalControl implements RailwaySignalControl {
    private final GpioController gpio = GpioFactory.getInstance();
    private final Map<String,GpioPinDigitalOutput> pins = new HashMap<String, GpioPinDigitalOutput>();

    public PiRailwaySignalControl() {
        pins.put("green", gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "green", PinState.HIGH));
        pins.put("orange", gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "orange", PinState.HIGH));
        pins.put("red", gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "red", PinState.HIGH));
    }

    public void shutdown() {
        gpio.shutdown();
    }

    private void setPin(String name, boolean illuminated) {
        GpioPinDigitalOutput gpioPinDigitalOutput = pins.get(name);
        if (gpioPinDigitalOutput != null) {
            gpioPinDigitalOutput.setState(!illuminated);
        }
    }

    @Override
    public void updateRailwaySignal(RailwaySignal railwaySignal) {
        for (Light light : railwaySignal) {
            setPin(light.getName(), light.isIlluminated());
        }
    }
}

package net.catchpole.pimpmylight.model;

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

public class Light {
    private String name;
    private boolean illuminated;

    public Light(String name, boolean illuminated) {
        this.name = name;
        this.illuminated = illuminated;
    }

    public Light(Light light, boolean illuminated) {
        this.name = light.name;
        this.illuminated = illuminated;
    }

    public String getName() {
        return name;
    }

    public boolean isIlluminated() {
        return illuminated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Light light = (Light) o;

        if (illuminated != light.illuminated) return false;
        if (!name.equals(light.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (illuminated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Light{" +
                "name='" + name + '\'' +
                ", illuminated=" + illuminated +
                '}';
    }
}

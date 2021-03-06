/**
 * Copyright [2015] [Christian Loehnert]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.ks.standbein.sample.customizedlocalization;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ks.standbein.launch.Launcher;
import de.ks.standbein.module.ApplicationModule;

public class CustomLocalizationExample {
  public static void main(String[] args) {
    //create guice injector
    Injector injector = Guice.createInjector(new CustomLocalizationModule(), new ApplicationModule());

    Launcher launcher = injector.getInstance(Launcher.class);
    launcher.launchAndWaitForUIThreads(args); //launch services
  }
}

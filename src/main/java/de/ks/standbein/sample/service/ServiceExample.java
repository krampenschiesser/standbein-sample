/**
 * Copyright [2015] [Christian Loehnert]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.ks.standbein.sample.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ks.standbein.launch.Launcher;
import de.ks.standbein.module.ApplicationModule;

public class ServiceExample {
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new ServiceModule(), new ApplicationModule());

    /*
     * Something special is happening here.
     * The launcher launches its services in waves.
     * All services with the same runlevel are launched at the same time.
     * The services with a higher runlevel are launched after all of the services with the lower level are started
     *
     * The ApplicationService has the runlevel 5 and will be started before MyPostApplicationService
     * So we will see the UI and can still setup some resources in the background.
     * Fancy :)
     */
    Launcher launcher = injector.getInstance(Launcher.class);
    launcher.launchAndWaitForUIThreads(args); //launch services
  }
}

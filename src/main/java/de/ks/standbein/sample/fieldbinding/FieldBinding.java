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
package de.ks.standbein.sample.fieldbinding;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ks.standbein.launch.Launcher;
import de.ks.standbein.module.ApplicationModule;

public class FieldBinding {
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new FieldBindingModule(), new ApplicationModule());
    injector.getInstance(Launcher.class).launchAndWaitForUIThreads();
  }
}

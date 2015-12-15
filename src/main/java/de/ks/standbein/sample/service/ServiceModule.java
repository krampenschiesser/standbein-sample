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

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import de.ks.standbein.application.ApplicationCfg;
import de.ks.standbein.application.MainWindow;
import de.ks.standbein.launch.Service;

/**
 * Guice module used to configure the application
 */
public class ServiceModule extends AbstractModule {
  @Override
  protected void configure() {
    //define our initial window
    bind(ApplicationCfg.class).toInstance(new ApplicationCfg("Startup service example", 500, 120));
    bind(MainWindow.class).to(ServiceWindow.class);

    //we use Guice's multibinding extension to add as many services as we want
    Multibinder<Service> multibinder = Multibinder.newSetBinder(binder(), Service.class);
    multibinder.addBinding().to(MyLongRunningStartupService.class);
    multibinder.addBinding().to(MyPostApplicationService.class);
  }
}

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
package de.ks.standbein.sample.hello;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import de.ks.standbein.application.ApplicationCfg;
import de.ks.standbein.application.MainWindow;
import de.ks.standbein.module.ApplicationServiceModule;

/**
 * Guice module used to configure the application
 */
public class HelloWorldModule extends AbstractModule {
  @Override
  protected void configure() {
    //define our initial window
    bind(ApplicationCfg.class).toInstance(new ApplicationCfg("app.title", 300, 100));
    bind(MainWindow.class).to(HelloWindow.class);

    //define a fancy app icon, although this is optional
    bind(String.class).annotatedWith(Names.named(ApplicationServiceModule.APPICON)).toInstance("helloIcon.jpg");

    //uncomment to switch between both registered locales. Default locale is Locale.getDefault();
//    OptionalBinder.newOptionalBinder(binder(),Locale.class).setBinding().toInstance(Locale.GERMAN);
//    OptionalBinder.newOptionalBinder(binder(),Locale.class).setBinding().toInstance(Locale.ENGLISH);
  }
}

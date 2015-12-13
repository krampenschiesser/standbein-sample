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

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.multibindings.OptionalBinder;
import com.google.inject.name.Names;
import de.ks.standbein.application.ApplicationCfg;
import de.ks.standbein.application.MainWindow;
import de.ks.standbein.i18n.LocalizationModule;
import de.ks.standbein.sample.hello.HelloWindow;

public class CustomLocalizationModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(MainWindow.class).to(HelloWindow.class);
    bind(ApplicationCfg.class).toInstance(new ApplicationCfg("app.title", 300, 100).setLocalized(true));

    OptionalBinder.newOptionalBinder(binder(), Key.get(String.class, Names.named(LocalizationModule.BASENAME)))//
      .setBinding().toInstance("de.ks.customlocalization.MyLocalization");
    OptionalBinder.newOptionalBinder(binder(), Key.get(String.class, Names.named(LocalizationModule.FILENAME)))//
      .setBinding().toInstance("MyLocalization");
  }
}

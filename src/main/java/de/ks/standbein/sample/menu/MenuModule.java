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
package de.ks.standbein.sample.menu;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import de.ks.standbein.application.ApplicationCfg;
import de.ks.standbein.application.MainWindow;
import de.ks.standbein.menu.MenuEntry;
import de.ks.standbein.menu.ShowNodeAction;

/**
 * Guice module used to configure the application
 */
public class MenuModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(ApplicationCfg.class).toInstance(new ApplicationCfg("app.title", 300, 100).setLocalized(true));
    bind(MainWindow.class).to(MenuWindow.class);

    Multibinder<MenuEntry> menuBinder = Multibinder.newSetBinder(binder(), MenuEntry.class);
    menuBinder.addBinding().toInstance(new MenuEntry("/main/hello", "world", new ShowNodeAction(MenuNodeProvider.class)));
    menuBinder.addBinding().toInstance(new MenuEntry("/main/hello", "sauerland", new ShowNodeAction(MenuNodeProvider.class)));
    menuBinder.addBinding().toInstance(new MenuEntry("/main/other", "info", new ShowNodeAction(MenuNodeProvider.class)));
    menuBinder.addBinding().toInstance(new MenuEntry("/main/other", "help", new ShowNodeAction(MenuNodeProvider.class)));
  }
}

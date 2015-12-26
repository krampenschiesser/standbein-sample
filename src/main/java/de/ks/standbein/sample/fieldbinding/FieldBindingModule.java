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

import com.google.inject.AbstractModule;
import de.ks.standbein.activity.InitialActivity;
import de.ks.standbein.application.ApplicationCfg;
import de.ks.standbein.sample.fieldbinding.activity.FieldBindingActivity;

public class FieldBindingModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(ApplicationCfg.class).toInstance(new ApplicationCfg("Field binding", 800, 600).setLocalized(false));
    bind(InitialActivity.class).toInstance(new InitialActivity(FieldBindingActivity.class));
  }
}
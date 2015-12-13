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

import de.ks.standbein.application.MainWindow;
import de.ks.standbein.i18n.Localized;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import javax.inject.Inject;

/**
 * The main windo that will be opened. Entry point for your application.
 */
public class HelloWindow extends MainWindow {
  @Inject
  Localized localized;

  @Override
  public String getApplicationTitle() {
    return localized.get("app.title");
  }

  @Override
  public Parent getNode() {
    StackPane pane = new StackPane();
    pane.setPrefSize(300, 100);//the root size is defined by the content size

    String parameter = "Sauerland";//localization supports parameters
    String translatedKey = localized.get("hello", parameter);//translate the key with parameter
    Label label = new Label(translatedKey);

    pane.getChildren().add(label);
    return pane;
  }
}

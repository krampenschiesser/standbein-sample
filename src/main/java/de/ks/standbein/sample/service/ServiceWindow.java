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

import de.ks.executor.JavaFXExecutorService;
import de.ks.standbein.application.MainWindow;
import de.ks.standbein.i18n.Localized;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.inject.Inject;

/**
 * The main windo that will be opened. Entry point for your application.
 */
public class ServiceWindow extends MainWindow {
  @Inject
  Localized localized;

  @Inject
  MyPostApplicationService service;
  @Inject
  JavaFXExecutorService javaFXExecutorService;

  @Override
  public Parent getNode() {
    StackPane pane = new StackPane();

    VBox vBox = new VBox();
    vBox.setSpacing(10);
    Label label = new Label("Sample with 2 services:\n 1 Before application startup, 1 that completes 5s after application startup.");
    vBox.getChildren().add(label);
    ProgressIndicator progressIndicator = new ProgressIndicator(-1);
    vBox.getChildren().add(progressIndicator);
    Label valueLabel = new Label("Value of long running service: ");
    vBox.getChildren().add(valueLabel);

    service.getValue().thenAcceptAsync(value -> {
      String text = valueLabel.getText();
      valueLabel.setText(text + value);
      progressIndicator.setProgress(1);
    }, javaFXExecutorService);

    pane.getChildren().add(vBox);
    return pane;
  }
}

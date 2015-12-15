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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

/**
 * The main windo that will be opened. Entry point for your application.
 */
public class ServiceWindow extends MainWindow {
  @Inject
  MyPostApplicationService service;
  @Inject
  JavaFXExecutorService javaFXExecutorService;

  @Override
  public Parent getNode() {
    StackPane pane = new StackPane();

    //setup some content
    VBox vBox = new VBox();
    vBox.setSpacing(10);
    Label label = new Label("Sample with 2 services:\n 1 Before application startup, 1 that completes 5s after application startup.");
    vBox.getChildren().add(label);
    ProgressIndicator progressIndicator = new ProgressIndicator(-1);
    vBox.getChildren().add(progressIndicator);
    Label valueLabel = new Label("Value of long running service: ");
    vBox.getChildren().add(valueLabel);

    //add a callback for the long running operation of MyPostApplicationService
    CompletableFuture<String> future = service.getValue();
    //when the future is done we call the method applyValue which does its stuff in the UI. This method is executed in the JavaFX UI thread.
    future.thenAcceptAsync(value -> applyValue(value, valueLabel, progressIndicator), javaFXExecutorService);//<- responsible service for executing the code in the FX UI thread

    pane.getChildren().add(vBox);
    return pane;
  }

  private void applyValue(String value, Label valueLabel, ProgressIndicator progressIndicator) {
    String text = valueLabel.getText();
    valueLabel.setText(text + value);
    progressIndicator.setProgress(1);
  }
}

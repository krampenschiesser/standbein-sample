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

import de.ks.standbein.application.MainWindow;
import de.ks.standbein.menu.MenuBarCreator;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javax.inject.Inject;

public class MenuWindow extends MainWindow {
  @Inject
  MenuBarCreator menu;

  private StackPane presenter;

  @Override
  public Parent getNode() {
    return new Label("Select a menu item.");
  }

  @Override
  public Pane getRoot() {
    BorderPane borderPane = new BorderPane();
    MenuBar menu = this.menu.createMenu("/main");
    borderPane.setTop(menu);
    presenter = new StackPane();
    borderPane.setCenter(presenter);
    return borderPane;
  }

  @Override
  public StackPane getContentPresenter() {
    return presenter;
  }
}

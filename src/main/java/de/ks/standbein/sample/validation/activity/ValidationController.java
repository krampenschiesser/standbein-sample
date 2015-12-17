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
package de.ks.standbein.sample.validation.activity;

import de.ks.standbein.BaseController;
import de.ks.standbein.validation.ValidationRegistry;
import de.ks.standbein.validation.ValidationResult;
import de.ks.standbein.validation.Validator;
import de.ks.standbein.validation.validators.NotEmptyValidator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class ValidationController extends BaseController<ValidatedModel> {
  @FXML
  private Slider slider;
  @FXML
  private Button submit;
  @FXML
  private TextField name;

  @FXML
  private ToggleGroup radioGroup;
  @FXML
  private RadioButton btn1;
  @FXML
  private RadioButton btn2;
  @FXML
  private RadioButton btn3;

  @Inject
  ValidationRegistry validationRegistry;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    validationRegistry.registerValidator(name, new NotEmptyValidator(localized));
    validationRegistry.registerValidator(slider, (Slider slider, Double value) -> {
      if (value > slider.getMax() / 2) {
        return null;
      } else {
        return ValidationResult.createError("Please move above half");
      }
    });

    Validator<RadioButton, Boolean> radioButtonValidator = (RadioButton radioButton, Boolean selected) -> {
      if (selected) {
        return ValidationResult.createError("Please select number 2");
      } else {
        return null;
      }
    };
    validationRegistry.registerValidator(btn1, radioButtonValidator);
    validationRegistry.registerValidator(btn3, radioButtonValidator);

    submit.disableProperty().bind(validationRegistry.invalidProperty());
  }

  @FXML
  void onSubmit() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Welcome to validation");
    alert.setHeaderText(null);
    alert.setContentText("Everything is fine");

    alert.showAndWait();
  }
}

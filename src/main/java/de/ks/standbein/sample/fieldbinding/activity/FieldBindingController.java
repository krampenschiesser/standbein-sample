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
package de.ks.standbein.sample.fieldbinding.activity;

import de.ks.standbein.BaseController;
import de.ks.standbein.validation.ValidationRegistry;
import de.ks.standbein.validation.validators.IntegerValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class FieldBindingController extends BaseController<FieldBindingExampleModel> {
  @FXML
  private Button save;
  @FXML
  private TextField stringField;
  @FXML
  private TextField intField;
  @FXML
  private DatePicker dateField;
  @FXML
  private ProgressIndicator progress;

  @Inject
  ValidationRegistry registry;

  private int count = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    registry.registerValidator(intField, new IntegerValidator(localized));//make sure valid values are integer
    save.disableProperty().bind(registry.invalidProperty());//disable save button when validation has failed

    //bind the name to the name field
    //binding is bidirectional, so when we save the model the new values are applied to the fields
    store.getBinding().getStringProperty(FieldBindingExampleModel.class, FieldBindingExampleModel::getName).bindBidirectional(stringField.textProperty());
    //bind the value to the int field using a numberconverter to convert from and to the integer
    intField.textProperty().bindBidirectional(store.getBinding().getIntegerProperty(FieldBindingExampleModel.class, FieldBindingExampleModel::getValue), new NumberStringConverter());
    //object bindings are also valid
    dateField.valueProperty().bindBidirectional(store.getBinding().getObjectProperty(FieldBindingExampleModel.class, FieldBindingExampleModel::getDate));

    //disable input when loading/saving is in progress
    stringField.disableProperty().bind(store.loadingProperty());
    intField.disableProperty().bind(store.loadingProperty());
    dateField.disableProperty().bind(store.loadingProperty());

    //be fancy and show a progress when
    progress.visibleProperty().bind(store.loadingProperty());
  }

  @Override
  public void duringLoad(FieldBindingExampleModel model) {
    super.duringLoad(model);

    //modify the model during loading, add a count to the name
    String name = model.getName();
    name = StringUtils.removePattern(name, "\\d*");
    if (!name.endsWith(" ")) {
      name += " ";
    }
    model.setName(name + count++);
  }

  @FXML
  void onSave() {
    //save the model, this writes the bound values back to the model, make sure you have accompaning setters for those getters
    store.save();
    //reload the model, apply the new input the the fields
    store.reload();
  }
}

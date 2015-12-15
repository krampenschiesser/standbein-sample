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

import de.ks.standbein.datasource.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class FieldBindingDS implements DataSource<FieldBindingExampleModel> {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  FieldBindingExampleModel model = new FieldBindingExampleModel("Name value", LocalDate.of(2015, 12, 15), 42);

  @Override
  public FieldBindingExampleModel loadModel(Consumer<FieldBindingExampleModel> furtherProcessing) {
    furtherProcessing.accept(model);//always accept all further callbacks. @see FieldBindingController.duringLoad
    //the reason the callbacks are not execute automatically is that I don't know how you access your datasource.
    //if you use JPA you might want to open an entitymanager, accept all callbacks to avoid lazy loading and close the EM again.
    return model;
  }

  @Override
  public void saveModel(FieldBindingExampleModel model, Consumer<FieldBindingExampleModel> beforeSaving) {
    beforeSaving.accept(model);//same as above, acceppt all callbacks. If needed wrap them in your transactional block/operation
    this.model = model;//we just store the model

    log.info("Saved new model {}", this.model);
    try {
      Thread.sleep(TimeUnit.SECONDS.toMillis(2));//introduce a little delay to  show field disablement
    } catch (InterruptedException e) {
      return;
    }
  }
}

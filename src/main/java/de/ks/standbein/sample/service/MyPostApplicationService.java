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

import de.ks.standbein.launch.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Singleton//singleton is important, otherwise guice would provide a new instance with each access
public class MyPostApplicationService extends Service {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  final CompletableFuture<String> value = new CompletableFuture<>();

  @Override
  protected void doStart() {
    //just some stalling
    log.info("Post application service is stalling....");
    for (int i = 0; i < 5; i++) {
      try {
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
      } catch (InterruptedException e) {
        return;
      }
    }
    //real work
    log.info("Post application service is done. setting value.");
    value.complete(this.getClass().getSimpleName() + " is done");//write the value back and trigger ServiceWindow.applyValue(...)
  }

  @Override
  protected void doStop() {

  }

  public CompletableFuture<String> getValue() {
    return value;
  }

  @Override
  public int getRunLevel() {
    return 10;//de.ks.standbein.application.ApplicationService.getRunlevel() has runlevel 5, therefore we have the application before this service is visible
  }
}

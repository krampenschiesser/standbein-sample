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

import java.time.LocalDate;

public class FieldBindingExampleModel {
  private String name;
  private LocalDate date;
  private int value;

  public FieldBindingExampleModel(String name, LocalDate date, int value) {
    this.name = name;
    this.date = date;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FieldBindingExampleModel{");
    sb.append("name='").append(name).append('\'');
    sb.append(", date=").append(date);
    sb.append(", value=").append(value);
    sb.append('}');
    return sb.toString();
  }
}

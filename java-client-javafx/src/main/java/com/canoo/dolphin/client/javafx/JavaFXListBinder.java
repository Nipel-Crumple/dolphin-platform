/*
 * Copyright 2015-2016 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.canoo.dolphin.client.javafx;

import com.canoo.dolphin.collections.ObservableList;
import static com.canoo.dolphin.util.Assert.*;

public interface JavaFXListBinder<S> {

    default Binding to(ObservableList<? extends S> dolphinList) {
        requireNonNull(dolphinList, "dolphinList");
        return to(dolphinList, n -> n);
    }

    <T> Binding to(ObservableList<T> dolphinList, Converter<? super T, ? extends S> converter);

}

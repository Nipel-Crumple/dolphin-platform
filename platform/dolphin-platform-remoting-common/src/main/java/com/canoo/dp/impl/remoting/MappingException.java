/*
 * Copyright 2015-2017 Canoo Engineering AG.
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
package com.canoo.dp.impl.remoting;

/**
 * Exception that is thrown if an error happens in the Dolphin Platform model mapping. This can happen if a
 * model class definition isn't working for the Dolphin Platform.
 */
public class MappingException extends RuntimeException {

    /**
     * Default constructor
     */
    public MappingException() {
    }

    /**
     * Constructor
     * @param message error message
     */
    public MappingException(String message) {
        super(message);
    }

    /**
     * Constructor
     * @param message error message
     * @param cause the cause
     */
    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     * @param cause the cause
     */
    public MappingException(Throwable cause) {
        super(cause);
    }
}

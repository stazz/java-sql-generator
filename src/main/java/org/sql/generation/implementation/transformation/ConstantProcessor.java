/*
 * Copyright (c) 2010, Stanislav Muhametsin. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.sql.generation.implementation.transformation;

import org.atp.api.Typeable;
import org.sql.generation.implementation.transformation.spi.SQLProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class ConstantProcessor
        implements SQLProcessor {

    private final String _constant;

    public ConstantProcessor(String constant) {
        if (constant == null) {
            constant = "";
        }

        this._constant = constant;
    }

    @Override
    public void process(final SQLProcessorAggregator aggregator, final Typeable<?> object, final StringBuilder builder) {
        builder.append(this._constant);
    }
}

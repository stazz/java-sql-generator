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

package org.sql.generation.implementation.transformation.mysql;

import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.common.TableNameFunction;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.TableNameDirectProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.TableNameFunctionProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class TableProcessing {
    public static class MySQLTableNameDirectProcessor extends TableNameDirectProcessor {
        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final TableNameDirect object, final StringBuilder builder) {
            // MySQL does not understand schema-qualified table names
            builder.append(object.getTableName());
        }
    }

    public static class MySQLTableNameFunctionProcessor extends TableNameFunctionProcessor {
        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final TableNameFunction object, final StringBuilder builder) {
            // MySQL does not understand schema-qualified table names
            processor.process(object.getFunction(), builder);
        }
    }
}

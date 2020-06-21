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

package org.sql.generation.api.grammar.builders.modification;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.modification.ColumnSourceByValues;

import java.util.List;

/**
 * This builder builds the {@link ColumnSourceByValues} syntax element.
 *
 * @author Stanislav Muhametsin
 */
public interface ColumnSourceByValuesBuilder
        extends AbstractBuilder<ColumnSourceByValues> {
    /**
     * Adds the expressions as values to columns.
     *
     * @param values The value expressions to add.
     * @return This builder.
     */
    ColumnSourceByValuesBuilder addValues(ValueExpression... values);

    /**
     * Returns the value expressions for the columns.
     *
     * @return The value expressions for the columns.
     */
    List<ValueExpression> getValues();

    /**
     * Adds the names for the columns.
     *
     * @param columnNames The names for columns.
     * @return This builder.
     */
    ColumnSourceByValuesBuilder addColumnNames(String... columnNames);

    /**
     * Returns the names for the columns.
     *
     * @return The names for the columns.
     */
    List<String> getColumnNames();
}

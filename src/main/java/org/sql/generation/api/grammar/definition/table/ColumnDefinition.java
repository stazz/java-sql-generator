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

package org.sql.generation.api.grammar.definition.table;

import org.sql.generation.api.grammar.common.datatypes.SQLDataType;

/**
 * This syntax element represents the column definition of the table definition statement.
 *
 * @author Stanislav Muhametsin
 * @see TableDefinition
 */
public interface ColumnDefinition
        extends TableElement {

    /**
     * Returns the name of this column.
     *
     * @return The name of this column.
     */
    String getColumnName();

    /**
     * Returns the data type of this column.
     *
     * @return The data type of this column.
     */
    SQLDataType getDataType();

    /**
     * Returns the default clause for this column. May be {@code null} if no default value is defined.
     *
     * @return The default clause for this column. May be {@code null} if no default value is defined.
     */
    String getDefault();

    /**
     * Returns {@code true} if this column may have {@code NULL} values, otherwise (when {@code NOT NULL} constraints is
     * present) returns {@code false}.
     *
     * @return {@code true} if this column may have {@code NULL} values; {@code false} otherwise.
     */
    Boolean mayBeNull();

    /**
     * Returns the auto generation policy for this column. May be {@code null} if no auto generation policy exists for
     * this column.
     *
     * @return Auto generation policy for this column, or {@code null} if the column is not auto-generated.
     */
    AutoGenerationPolicy getAutoGenerationPolicy();

}

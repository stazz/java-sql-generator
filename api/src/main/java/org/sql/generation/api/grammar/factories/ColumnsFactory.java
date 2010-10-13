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

package org.sql.generation.api.grammar.factories;

import java.util.List;

import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.query.ColumnReferenceByExpression;
import org.sql.generation.api.grammar.query.ColumnReferenceByName;
import org.sql.generation.api.vendor.SQLVendor;

/**
 * A factory to create various expressions related to columns. This factory is obtainable from {@link SQLVendor}.
 * 
 * @author Stanislav Muhametsin
 * @see SQLVendor
 */
public interface ColumnsFactory
{

    /**
     * Creates column reference, which has value of some expression.
     * 
     * @param expression The expression for the column.
     * @return The new {@link ColumnReferenceByExpression}.
     */
    public ColumnReferenceByExpression colExp( ValueExpression expression );

    /**
     * <p>
     * Creates column reference, which references column by name, without table name.
     * </p>
     * <p>
     * Calling this method is equivalent in calling {@link #colName(String, String)} and passing {@code null} as first
     * argument.
     * </p>
     * 
     * @param colName The name of the column.
     * @return The new {@link ColumnReferenceByName}.
     */
    public ColumnReferenceByName colName( String colName );

    /**
     * Creates column reference, which reference column by its name and by name of table, to which it belongs.
     * 
     * @param tableName The name of the table. May be {@code null}.
     * @param colName The name of the column.
     * @return The new {@link ColumnReferenceByName}.
     */
    public ColumnReferenceByName colName( String tableName, String colName );

    /**
     * Constructs new {@link ColumnNameList}.
     * 
     * @param names The column names. At least one element must be present.
     * @return The new {@link ColumnNameList}.
     */
    public ColumnNameList colNames( String... names );

    /**
     * Constructs new {@link ColumnNameList} using specified column names. A new copy of List will be allocated for the
     * {@link ColumnNameList}.
     * 
     * @param names The column names. Must contain at least one name.
     * @return The new {@link ColumnNameList}.
     */
    public ColumnNameList colNames( List<String> names );

}

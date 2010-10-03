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

import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.builders.query.TableReferenceBuilder;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.TableAlias;
import org.sql.generation.api.grammar.query.TableReferenceByExpression;
import org.sql.generation.api.grammar.query.TableReferenceByName;
import org.sql.generation.api.grammar.query.TableReferencePrimary;
import org.sql.generation.api.grammar.query.joins.JoinCondition;
import org.sql.generation.api.grammar.query.joins.NamedColumnsJoin;
import org.sql.generation.api.vendor.SQLVendor;

/**
 * A factory for creating builders and syntax elements related to tables. This factory is obtainable from
 * {@link SQLVendor}.
 * 
 * @author Stanislav Muhametsin
 * @see SQLVendor
 */
public interface TableReferenceFactory
{
    /**
     * <p>
     * Creates a new table reference, which uses given table name, without table alias.
     * </p>
     * <p>
     * Calling this method is equivalent to calling {@link #table(TableName, TableAlias)} and passing {@code null} as
     * second parameter.
     * </p>
     * 
     * @param tableName The table name to use.
     * @return The new {@link TableReferenceByName}.
     */
    public TableReferenceByName table( TableName tableName );

    /**
     * Creates a new table references, which uses given table name along with given table alias.
     * 
     * @param tableName The table name to use.
     * @param alias The table alias to use. May be {@code null}.
     * @return The new {@link TableReferenceByName}.
     */
    public TableReferenceByName table( TableName tableName, TableAlias alias );

    /**
     * <p>
     * Creates a new table name, which isn't schema-qualified.
     * </p>
     * <p>
     * Calling this method is equivalent to calling {@link #tableName(String, String)} and passing {@code null} as first
     * parameter.
     * </p>
     * 
     * @param tableName The name of the table.
     * @return The new {@link TableName}.
     */
    public TableName tableName( String tableName );

    /**
     * Creates a new table name. If the given schema-name is non-{@code null}, the table name is said to be
     * schema-qualified.
     * 
     * @param schemaName The schema name to use. May be {@code null}.
     * @param tableName The table name to use.
     * @return The new {@link TableName}.
     */
    public TableName tableName( String schemaName, String tableName );

    /**
     * <p>
     * Creates a new alias for table.
     * </p>
     * <p>
     * Calling this method is equivalent to calling {@link #tableAliasWithCols(String, String...)} and not pass anything
     * to varargs parameter.
     * </p>
     * 
     * @param tableNameAlias The alias for table name.
     * @return The new {@link TableAlias}.
     */
    public TableAlias tableAlias( String tableNameAlias );

    /**
     * Creates a new table alias for table, with renamed columns.
     * 
     * @param tableNameAlias The alias for table name.
     * @param colNames The new column names for table.
     * @return The new {@link TableAlias}.
     */
    public TableAlias tableAliasWithCols( String tableNameAlias, String... colNames );

    /**
     * <p>
     * Creates a new table reference, which will use the values returned by query as if they were values of the table.
     * </p>
     * <p>
     * Calling this method is equivalent to calling {@link #table(QueryExpression, TableAlias)} and passing {@code null}
     * as second parameter.
     * </p>
     * 
     * @param query The query to use.
     * @return The new {@link TableReferenceByExpression}.
     */
    public TableReferenceByExpression table( QueryExpression query );

    /**
     * Creates a new table reference, which will use the values returned by query as if they were values of the table.
     * Optionally, the table will has a given alias.
     * 
     * @param query The query to use.
     * @param alias The table alias to use. May be {@code null} if no alias is needed.
     * @return The new {@link TableReferenceByExpression}.
     */
    public TableReferenceByExpression table( QueryExpression query, TableAlias alias );

    /**
     * Creates a new {@link TableReferenceBuilder} typically used to build joined tables.
     * 
     * @param firstTable The starting table.
     * @return The new {@link TableReferenceBuilder}.
     */
    public TableReferenceBuilder tableBuilder( TableReferencePrimary firstTable );

    /**
     * Creates a join-condition using specified boolean expression to join tables.
     * 
     * @param condition The condition to join tables.
     * @return The new {@link JoinCondition}.
     */
    public JoinCondition jc( BooleanExpression condition );

    /**
     * Creates a new named columns join specification, which will use column names to join tables.
     * 
     * @param columnNames The column names to use to join tables.
     * @return The new {@link NamedColumnsJoin}.
     */
    public NamedColumnsJoin nc( ColumnNameList columnNames );

}

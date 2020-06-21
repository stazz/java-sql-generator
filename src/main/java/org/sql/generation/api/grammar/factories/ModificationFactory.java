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

import org.sql.generation.api.grammar.builders.modification.ColumnSourceByValuesBuilder;
import org.sql.generation.api.grammar.builders.modification.DeleteBySearchBuilder;
import org.sql.generation.api.grammar.builders.modification.InsertStatementBuilder;
import org.sql.generation.api.grammar.builders.modification.UpdateBySearchBuilder;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.modification.*;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.vendor.SQLVendor;

/**
 * A factory, which creates SQL syntax elements related to modification statements ({@code INSERT INTO},
 * {@code DELETE FROM}, and {@code UPDATE}). This factory is obtainable from {@link SQLVendor}.
 *
 * @author Stanislav Muhametsin
 * @see SQLVendor
 */
public interface ModificationFactory {

    /**
     * Creates a builder to add values as column sources in {@code INSERT INTO} statement.
     *
     * @return The new {@link ColumnSourceByValuesBuilder} for {@link ColumnSourceByValues}.
     */
    ColumnSourceByValuesBuilder columnSourceByValues();

    /**
     * <p>
     * Creates a column source, which uses a query as a source for columns in {@code INSERT INTO} statement.
     * </p>
     * <p>
     * Calling this method is equivalent in calling {@link #columnSourceByQuery(ColumnNameList, QueryExpression)} and
     * passing {@code null} as first argument.
     * </p>
     *
     * @param query The query to use as source for columns in {@code INSERT INTO} statement.
     * @return The new {@link ColumnSourceByQuery}.
     */
    ColumnSourceByQuery columnSourceByQuery(QueryExpression query);

    /**
     * Creates a column source, which uses specified target table column names and query as source columns in
     * {@code INSERT INTO} statement.
     *
     * @param columnNames The column names to use in target table.
     * @param query       The query to use to populate target table.
     * @return The new {@link ColumnSourceByQuery}.
     */
    ColumnSourceByQuery columnSourceByQuery(ColumnNameList columnNames, QueryExpression query);

    /**
     * Creates builder to create {@link DeleteBySearch} statements.
     *
     * @return The new builder for {@link DeleteBySearch}.
     * @see DeleteBySearchBuilder
     */
    DeleteBySearchBuilder deleteBySearch();

    /**
     * Creates builder to create {@link InsertStatement}s.
     *
     * @return The new builder for {@link InsertStatement}.
     * @see InsertStatementBuilder
     */
    InsertStatementBuilder insert();

    /**
     * Creates builder to create {@link UpdateBySearch} statements.
     *
     * @return The new builder for {@link UpdateBySearch} statements.
     * @see UpdateBySearchBuilder
     */
    UpdateBySearchBuilder updateBySearch();

    /**
     * <p>
     * Creates new target table to use in modification statements.
     * </p>
     * <p>
     * Calling this method is equivalent for calling {@link #createTargetTable(TableName, Boolean)} and passing
     * {@code false} as second parameter.
     *
     * @param tableName The name of the table.
     * @return The new {@link TargetTable}.
     */
    TargetTable createTargetTable(TableNameDirect tableName);

    /**
     * Creates new target table to use in modification statements.
     *
     * @param tableName The name of the table.
     * @param isOnly    Whether modification should affect child-tables too.
     * @return The new {@link TargetTable}.
     */
    TargetTable createTargetTable(TableNameDirect tableName, Boolean isOnly);

    /**
     * Creates a new source for {@code UPDATE} statement. This source will use specified expression as a source for
     * values.
     *
     * @param expression The expression to use.
     * @return The new {@link UpdateSourceByExpression}.
     * @see UpdateBySearch
     */
    UpdateSourceByExpression updateSourceByExp(ValueExpression expression);

    /**
     * Creates a new set clause for {@code UPDATE} statement.
     *
     * @param updateTarget The target of the update, typically name of the column.
     * @param updateSource The source for data to be put into that column.
     * @return The new {@link SetClause}.
     * @see UpdateBySearch
     */
    SetClause setClause(String updateTarget, UpdateSource updateSource);

}

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
import org.sql.generation.api.grammar.builders.definition.ForeignKeyConstraintBuilder;
import org.sql.generation.api.grammar.builders.definition.SchemaDefinitionBuilder;
import org.sql.generation.api.grammar.builders.definition.TableDefinitionBuilder;
import org.sql.generation.api.grammar.builders.definition.TableElementListBuilder;
import org.sql.generation.api.grammar.builders.definition.UniqueConstraintBuilder;
import org.sql.generation.api.grammar.builders.definition.ViewDefinitionBuilder;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.common.datatypes.SQLDataType;
import org.sql.generation.api.grammar.definition.table.CheckConstraint;
import org.sql.generation.api.grammar.definition.table.ColumnDefinition;
import org.sql.generation.api.grammar.definition.table.ConstraintCharacteristics;
import org.sql.generation.api.grammar.definition.table.ForeignKeyConstraint;
import org.sql.generation.api.grammar.definition.table.LikeClause;
import org.sql.generation.api.grammar.definition.table.TableConstraint;
import org.sql.generation.api.grammar.definition.table.TableConstraintDefinition;
import org.sql.generation.api.grammar.definition.table.TableDefinition;
import org.sql.generation.api.grammar.definition.view.RegularViewSpecification;
import org.sql.generation.api.grammar.definition.view.ViewDefinition;

/**
 * This is factory for creating builders and syntax elements related to SQL Data Definition (typically {@code CREATE}
 * statements).
 * 
 * @author Stanislav Muhametsin
 */
public interface DefinitionFactory
{

    /**
     * Creates an empty builder for {@code CREATE SCHEMA} statement.
     * 
     * @return An empty builder for {@code CREATE SCHEMA} statement.
     */
    public SchemaDefinitionBuilder createSchemaDefinitionBuilder();

    /**
     * Creates an empty builder for {@code CREATE TABLE} statement.
     * 
     * @return An empty builder for {@code CREATE TABLE} statement.
     */
    public TableDefinitionBuilder createTableDefinitionBuilder();

    /**
     * Creates an empty builder for defining columns and constraints for {@code CREATE TABLE} statement.
     * 
     * @return An empty builder for defining columns and constraints for {@code CREATE TABLE} statement.
     * @see TableDefinition
     */
    public TableElementListBuilder createTableElementListBuilder();

    /**
     * Creates a new definition of column with specified name and data type. Invoking this method is equivalent to
     * invoking {@link #createColumnDefinition(String, String, String, Boolean)} and pass {@code null} and {@code true}
     * as last two parameters (meaning that there is no default value for column, and it may have {@code NULL} values).
     * 
     * @param columnName The name of the column.
     * @param columnDataType The data type of the column.
     * @return The syntax element for definition of column with specified name and data type.
     * @see #createColumnDefinition(String, String, String, Boolean)
     */
    public ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType );

    /**
     * Creates a new definition of column with specified name, data type, and {@code NULL} value policy. Invoking this
     * method is equivalent to invoking {@link #createColumnDefinition(String, String, String, Boolean)} and pass
     * {@code null} and {@code mayBeNull} as last two parameters (meaning that there is no default value for column).
     * 
     * @param columnName The name of the column.
     * @param columnDataType The data type of the column.
     * @param mayBeNull The {@code NULL} value policy. Setting this to {@code false} is same as specifying
     *            {@code NOT NULL} in column definition in SQL.
     * @return The syntax element for definition of column with specified name, data type, and {@code NULL} value
     *         policy.
     * @see #createColumnDefinition(String, String, String, Boolean)
     */
    public ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType, Boolean mayBeNull );

    /**
     * Creates a new definition of column with specified name, data type, default value. Invoking this method is
     * equivalent to invoking {@link #createColumnDefinition(String, String, String, Boolean)} and pass
     * {@code columnDefault} and {@code true} as last two parameters (meaning that column may have {@code NULL} values).
     * 
     * @param columnName The name of the column.
     * @param columnDataType The data type of the column.
     * @param columnDefault The default value of the column.
     * @return The syntax element for definition of column with specified name, data type, default value.
     * @see #createColumnDefinition(String, String, String, Boolean)
     */
    public ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType, String columnDefault );

    /**
     * Creates a new definition of column with specified name, data type, default value, and {@code NULL} value policy.
     * 
     * @param columnName The name of the column.
     * @param columnDataType The data type of the column.
     * @param columnDefault The default value of the column.
     * @param mayBeNull The {@code NULL} value policy for the column. Setting this to {@code false} is same as
     *            specifying {@code NOT NULL} in column definition in SQL.
     * @return The syntax element for definition of column with specified name, data type, default value, and
     *         {@code NULL} value policy.
     */
    public ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType,
        String columnDefault, Boolean mayBeNull );

    /**
     * Creates a new {@code LIKE
     * <table name>} clause for {@code CREATE TABLE} statement.
     * 
     * @param tableName The name of the target table.
     * @return The syntax element for {@code LIKE
     * <table name>} clause for {@code CREATE TABLE} statement.
     */
    public LikeClause createLikeClause( TableName tableName );

    /**
     * Creates a new unnamed table constraint without any {@link ConstraintCharacteristics}. Invoking this method is
     * equivalent to invoking
     * {@link #createTableConstraintDefinition(String, TableConstraint, ConstraintCharacteristics)} and passing
     * {@code null}s as first and last parameters.
     * 
     * @param constraint The constraint for the table.
     * @return The syntax element for unnamed table constraint without any {@link ConstraintCharacteristics}.
     * @see #createColumnDefinition(String, String, String, Boolean)
     */
    public TableConstraintDefinition createTableConstraintDefinition( TableConstraint constraint );

    /**
     * Creates a new, named table constraint without any {@link ConstraintCharacteristics}. Invoking this method is
     * equivalent to invoking
     * {@link #createTableConstraintDefinition(String, TableConstraint, ConstraintCharacteristics)} and passing
     * {@code null} as last parameter.
     * 
     * @param name The name for the constraint.
     * @param constraint The constraint for the table.
     * @return The syntax element for named table constraint without any {@link ConstraintCharacteristics}.
     * @see #createColumnDefinition(String, String, String, Boolean)
     */
    public TableConstraintDefinition createTableConstraintDefinition( String name, TableConstraint constraint );

    /**
     * Creates a new unnamed table constraint with specified {@link ConstraintCharacteristics}. Invoking this method is
     * equivalent to invoking
     * {@link #createTableConstraintDefinition(String, TableConstraint, ConstraintCharacteristics)} and passing
     * {@code null} as first parameter.
     * 
     * @param constraint The constraint for the table.
     * @param characteristics The constraint characteristics for the constraint.
     * @return The syntax element for unnamed table constraint with specified {@link ConstraintCharacteristics}.
     * @see #createColumnDefinition(String, String, String, Boolean)
     * @see ConstraintCharacteristics
     */
    public TableConstraintDefinition createTableConstraintDefinition( TableConstraint constraint,
        ConstraintCharacteristics characteristics );

    /**
     * Creates a new named table constraint with specified {@link ConstraintCharacteristics}.
     * 
     * @param name The name for the constraint.
     * @param constraint The constraint for the table.
     * @param characteristics The characteristics for the constraint.
     * @return The syntax element for named table constraint with specified {@link ConstraintCharacteristics}.
     * @see ConstraintCharacteristics
     */
    public TableConstraintDefinition createTableConstraintDefinition( String name, TableConstraint constraint,
        ConstraintCharacteristics characteristics );

    /**
     * Creates a {@code CHECK} clause, typically used in {@code CREATE TABLE} statements.
     * 
     * @param check The boolean expression for check.
     * @return The syntax element for {@code CHECK} clause, typically used in {@code CREATE TABLE} statements.
     */
    public CheckConstraint createCheckConstraint( BooleanExpression check );

    /**
     * Creates an empty builder for unique constraint (either {@code UNIQUE(columns...)} or
     * {@code PRIMARY KEY(columns...)}), typically used in {@code CREATE TABLE} statements.
     * 
     * @return An empty builder for unique constraints (either {@code UNIQUE(columns...)} or
     *         {@code PRIMARY KEY(columns...)}).
     */
    public UniqueConstraintBuilder createUniqueConstraintBuilder();

    /**
     * Creates an empty builder for {@code FOREIGN KEY} constraint, typically used in {@code CREATE TABLE} statements.
     * 
     * @return An empty builder for {@code FOREIGN KEY} constraint.
     * @see ForeignKeyConstraintBuilder
     * @see ForeignKeyConstraint
     */
    public ForeignKeyConstraintBuilder createForeignKeyConstraintBuilder();

    /**
     * Creates an empty builder for {@code CREATE VIEW} statement.
     * 
     * @return An empty builder for {@code CREATE VIEW} statement.
     * @see ViewDefinitionBuilder
     * @see ViewDefinition
     */
    public ViewDefinitionBuilder createViewDefinitionBuilder();

    /**
     * Creates a new view specification with given columns. Must have at least one column.
     * 
     * @param columnNames The names of the columns.
     * @return A new {@link RegularViewSpecification}.
     * @see RegularViewSpecification
     */
    public RegularViewSpecification createRegularViewSpecification( String... columnNames );

}

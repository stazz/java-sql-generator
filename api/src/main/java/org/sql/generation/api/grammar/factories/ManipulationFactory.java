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

import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.definition.table.ColumnDefinition;
import org.sql.generation.api.grammar.definition.table.TableConstraintDefinition;
import org.sql.generation.api.grammar.manipulation.AddColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AddTableConstraintDefinition;
import org.sql.generation.api.grammar.manipulation.AlterColumnAction;
import org.sql.generation.api.grammar.manipulation.AlterColumnAction.DropDefault;
import org.sql.generation.api.grammar.manipulation.AlterColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;
import org.sql.generation.api.grammar.manipulation.AlterTableStatement;
import org.sql.generation.api.grammar.manipulation.DropBehaviour;
import org.sql.generation.api.grammar.manipulation.DropColumnDefinition;
import org.sql.generation.api.grammar.manipulation.DropSchemaStatement;
import org.sql.generation.api.grammar.manipulation.DropTableConstraintDefinition;
import org.sql.generation.api.grammar.manipulation.DropTableOrViewStatement;
import org.sql.generation.api.grammar.manipulation.ObjectType;
import org.sql.generation.api.grammar.manipulation.SetColumnDefault;

/**
 * This is factory for creating builders and syntax elements related to SQL Data Manipulation (typically {@code ALTER}
 * and {@code DROP} statements).
 * 
 * @author Stanislav Muhametsin
 */
public interface ManipulationFactory
{

    /**
     * Creates the {@code ALTER TABLE} statement with specified parameter.
     * 
     * @param tableName The name of the table.
     * @param action The action to be done to the table.
     * @return The {@code ALTER TABLE} statement syntax element.
     * @see AlterTableStatement
     * @see #createAddColumnDefinition(ColumnDefinition)
     * @see #createAddTableConstraintDefinition(TableConstraintDefinition)
     * @see #createAlterColumnDefinition(String, AlterColumnAction)
     * @see #createDropColumnDefinition(String, DropBehaviour)
     * @see #createDropTableConstraintDefinition(String, DropBehaviour)
     */
    public AlterTableStatement createAlterTableStatement( TableName tableName, AlterTableAction action );

    /**
     * Creates the syntax element for adding column ({@code ADD COLUMN ...}) in {@code ALTER TABLE} statement.
     * 
     * @param definition The column to add to table.
     * @return The syntax element for adding column ({@code ADD COLUMN ...}) in {@code ALTER TABLE} statement.
     * @see AlterTableStatement
     */
    public AddColumnDefinition createAddColumnDefinition( ColumnDefinition definition );

    /**
     * Creates the syntax element for adding table constraint({@code ADD ...}) in {@code ALTER TABLE} statement.
     * 
     * @param constraintDefinition The table constraint to add to table.
     * @return The syntax element for adding table constraint ({@code ADD ...}) in {@code ALTER TABLE} statement.
     * @see AlterTableStatement
     */
    public AddTableConstraintDefinition createAddTableConstraintDefinition(
        TableConstraintDefinition constraintDefinition );

    /**
     * Creates the syntax element for altering column definition ({@code ALTER COLUMN}) in {@code ALTER TABLE}
     * statement.
     * 
     * @param columnName The name of the column to alter.
     * @param action The way how column should be altered.
     * @return The syntax element for altering column definition ({@code ALTER COLUMN}) in {@code ALTER TABLE}
     *         statement.
     * @see AlterColumnAction
     * @see DropDefault
     * @see #createSetColumnDefault(String)
     */
    public AlterColumnDefinition createAlterColumnDefinition( String columnName, AlterColumnAction action );

    /**
     * Creates the syntax element for setting a new default for column ({@code SET DEFAULT ...}) in {@code ALTER TABLE}
     * statement.
     * 
     * @param newDefault The new default value for column.
     * @return The syntax element for setting a new default for column ({@code SET DEFAULT ...}) in {@code ALTER TABLE}
     *         statement.
     * @see AlterColumnAction
     */
    public SetColumnDefault createSetColumnDefault( String newDefault );

    /**
     * Creates the syntax element for dropping a column definition ({@code DROP COLUMN}) in {@code ALTER TABLE}
     * statement.
     * 
     * @param columnName The name of the column to drop.
     * @param dropBehaviour The drop behaviour.
     * @return The syntax element for dropping a column definition ({@code DROP COLUMN}) in {@code ALTER TABLE}
     *         statement.
     * @see DropBehaviour
     * @see AlterTableStatement
     */
    public DropColumnDefinition createDropColumnDefinition( String columnName, DropBehaviour dropBehaviour );

    /**
     * Creates the syntax element for dropping a table constraint ({@code DROP CONSTRAINT ...}) in {@code ALTER TABLE}
     * statement.
     * 
     * @param constraintName The name of the constraint to drop.
     * @param dropBehaviour The drop behaviour.
     * @return The syntax element for dropping a table constraint ({@code DROP CONSTRAINT ...}) in {@code ALTER TABLE}
     *         statement.
     * @see DropBehaviour
     * @see AlterTableStatement
     */
    public DropTableConstraintDefinition createDropTableConstraintDefinition( String constraintName,
        DropBehaviour dropBehaviour );

    /**
     * Creates the statement to drop schema ({@code DROP SCHEMA ...}).
     * 
     * @param schemaName The name of the schema to drop.
     * @param dropBehaviour The drop behaviour.
     * @return The statement to drop schema ({@code DROP SCHEMA ...}).
     */
    public DropSchemaStatement createDropSchemaStatement( String schemaName, DropBehaviour dropBehaviour );

    /**
     * Creates the statement to drop table ({@code DROP TABLE ...}) or view ({@code DROP VIEW ...}).
     * 
     * @param tableName The name of the table or view to drop.
     * @param theType What to drop. Must be either {@link ObjectType#TABLE} for tables or {@link ObjectType#VIEW} for
     *            views.
     * @param dropBehaviour The drop behaviour.
     * @return The statement to drop table ({@code DROP TABLE ...}) or view ({@code DROP VIEW ...}).
     * @see DropBehaviour
     * @see ObjectType
     */
    public DropTableOrViewStatement createDropTableOrViewStatement( TableName tableName, ObjectType theType,
        DropBehaviour dropBehaviour );

}

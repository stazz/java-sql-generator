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

import org.sql.generation.api.grammar.common.SchemaDefinitionStatement;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.definition.schema.SchemaElement;

/**
 * This syntax element represents the table definition ({@code CREATE TABLE }) statement.
 * 
 * @author Stanislav Muhametsin
 */
public interface TableDefinition
    extends SchemaDefinitionStatement, SchemaElement
{
    /**
     * Returns the table scope for this table. May be {@code null} if no scope defined.
     * 
     * @return The table scope for this table. May be {@code null} if no scope defined.
     * @see TableScope
     */
    public TableScope getTableScope();

    /**
     * Returns the name for this table.
     * 
     * @return The name for this table.
     * @see TableName
     */
    public TableNameDirect getTableName();

    /**
     * Returns the commit action for this table. May be {@code null} if no commit action defined.
     * 
     * @return The commit action for this table. May be {@code null} if no commit action defined.
     * @see TableCommitAction
     */
    public TableCommitAction getCommitAction();

    /**
     * Returns the actual body of this table definition. Usually is a list of column definitions and constraints.
     * 
     * @return The actual body of this table definition.
     */
    public TableContentsSource getContents();
}

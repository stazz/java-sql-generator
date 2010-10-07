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

import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.TableName;

/**
 * This syntax element represents the {@code FOREIGN KEY(col1, col2, ...) ...} table constraint in table definition.
 * 
 * @author Stanislav Muhametsin
 * @see TableConstraint
 * @see TableDefinition
 */
public interface ForeignKeyConstraint
    extends TableConstraint
{

    /**
     * Returns the list of column names in this table.
     * 
     * @return The list of column names in this table.
     */
    public ColumnNameList getSourceColumns();

    /**
     * Returns the name of the table where the columns in this table are referencing to.
     * 
     * @return The name of the table where the columns in this table are referencing to.
     */
    public TableName getTargetTableName();

    /**
     * Returns the list of column names in target table.
     * 
     * @return The list of column names in target table.
     */
    public ColumnNameList getTargetColumns();

    /**
     * Returns the match type for this foreign key. May be {@code null} if no match type is specified.
     * 
     * @return The match type for this foreign key. May be {@code null} if no match type is specified.
     */
    public MatchType getMatchType();

    /**
     * Returns the referential action of updating the rows. May be {@code null} if none specified.
     * 
     * @return The referential action of updating the rows. May be {@code null} if none specified.
     * @see ReferentialAction
     */
    public ReferentialAction getOnUpdate();

    /**
     * Returns the referential action of deleting the rows. May be {@code null} if none specified.
     * 
     * @return The referential action of deleting the rows. May be {@code null} if none specified.
     * @see ReferentialAction
     */
    public ReferentialAction getOnDelete();
}

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

import java.util.List;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.builders.BooleanBuilder;
import org.sql.generation.api.grammar.modification.SetClause;
import org.sql.generation.api.grammar.modification.TargetTable;
import org.sql.generation.api.grammar.modification.UpdateBySearch;

/**
 * This builder builds statements updating rows matching search condition ({@code UPDATE} table {@code SET} set-clauses
 * {@code [WHERE} searchCondition {@code ]}).
 * 
 * @see SetClause
 * @see BooleanBuilder
 * @author Stanislav Muhametsin
 */
public interface UpdateBySearchBuilder
    extends AbstractBuilder<UpdateBySearch>
{
    /**
     * Sets the target table for this {@code UPDATE} statement.
     * 
     * @param table The target table for this {@code UPDATE} statement.
     * @return This builder.
     */
    public UpdateBySearchBuilder setTargetTable( TargetTable table );

    /**
     * Returns the builder for search condition for this {@code UPDATE} statement (boolean expression after
     * {@code WHERE}). The search condition is optional.
     * 
     * @return The builder for search condition for this {@code UPDATE} statement.
     */
    public BooleanBuilder getWhereBuilder();

    /**
     * Adds the clause to the set-clause list of this {@code UPDATE} statement.
     * 
     * @param clauses The set-clauses for this {@code UPDATE} statement.
     * @return This builder.
     */
    public UpdateBySearchBuilder addSetClauses( SetClause... clauses );

    /**
     * Returns the list of set clauses which has been added to this builder.
     * 
     * @return The list of set clauses which has been added to this builder.
     */
    public List<SetClause> getSetClauses();
}

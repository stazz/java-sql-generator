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

package org.sql.generation.api.grammar.query;

import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.builders.query.QuerySpecificationBuilder;

/**
 * This syntax element represents the single {@code SELECT} statement.
 * 
 * @author Stanislav Muhametsin
 * @see QuerySpecificationBuilder
 * @see SelectColumnClause
 * @see FromClause
 * @see BooleanExpression
 * @see GroupByClause
 * @see OrderByClause
 */
public interface QuerySpecification
    extends QueryExpressionBodyQuery
{

    /**
     * Returns the columns in this {@code SELECT} statement.
     * 
     * @return The columns in this {@code SELECT} statement.
     */
    public SelectColumnClause getColumns();

    /**
     * Returns the {@code FROM} clause of this {@code SELECT} statement.
     * 
     * @return The {@code FROM} clause of this {@code SELECT} statement.
     */
    public FromClause getFrom();

    /**
     * Returns the search condition for resulting rows of this {@code SELECT} statement.
     * 
     * @return The search condition for resulting rows of this {@code SELECT} statement.
     */
    public BooleanExpression getWhere();

    /**
     * Returns the {@code GROUP BY} clause of this {@code SELECT} statement.
     * 
     * @return The {@code GROUP BY} clause of this {@code SELECT} statement.
     */
    public GroupByClause getGroupBy();

    /**
     * Returns the grouping condition for {@code GROUP BY} clause of this {@code SELECT} statement.
     * 
     * @return The grouping condition for {@code GROUP BY} clause of this {@code SELECT} statement.
     */
    public BooleanExpression getHaving();

    /**
     * Returns the {@code ORDER BY} clause of this {@code SELECT} statement.
     * 
     * @return The {@code ORDER BY} clause of this {@code SELECT} statement.
     */
    public OrderByClause getOrderBy();

    /**
     * Returns the {@code FETCH FIRST <number> ROWS ONLY} expression for this {@code SELECT} statement.
     * 
     * @return The {@code FETCH FIRST <number> ROWS ONLY} expression for this {@code SELECT} statement.
     */
    public LimitSpecification getLimitSpecification();

    /**
     * Returns the {@code OFFSET <number> ROWS} expression for this {@code SELECT} statement.
     * 
     * @return The {@code OFFSET <number> ROWS} expression for this {@code SELECT} statement.
     */
    public OffsetSpecification getOffsetSpecification();

}

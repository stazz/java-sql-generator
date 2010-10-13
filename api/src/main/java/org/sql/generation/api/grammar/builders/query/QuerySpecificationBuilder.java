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

package org.sql.generation.api.grammar.builders.query;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.builders.booleans.BooleanBuilder;
import org.sql.generation.api.grammar.query.QuerySpecification;

/**
 * This builder builds a single {@code SELECT} query. It acts as an aggregator for {@link ColumnsBuilder} for columns in
 * {@code SELECT}, {@link FromBuilder} for tables in {@code FROM} clause, {@link BooleanBuilder} for search condition in
 * {@code WHERE} clause, {@link GroupByBuilder} for {@code GROUP BY} clause, another {@link BooleanBuilder} for grouping
 * conditions in {@code HAVING} clause, and finally {@link OrderByBuilder} for {@code ORDER BY} clause.
 * 
 * @author Stanislav Muhametsin
 * @see QuerySpecification
 */
public interface QuerySpecificationBuilder
    extends AbstractBuilder<QuerySpecification>
{

    /**
     * Gets the builder for columns in this {@code SELECT} statement.
     * 
     * @return The builder for columns in this {@code SELECT} statement.
     */
    public ColumnsBuilder getSelect();

    /**
     * Gets the builder for {@code FROM} clause of this {@code SELECT} statement.
     * 
     * @return The builder for {@code FROM} clause of this {@code SELECT} statement.
     */
    public FromBuilder getFrom();

    /**
     * Gets the builder for search condition in {@code WHERE} clause of this {@code SELECT} statement.
     * 
     * @return The builder for search condition in {@code WHERE} clause of this {@code SELECT} statement.
     */
    public BooleanBuilder getWhere();

    /**
     * Gets the builder for {@code GROUP BY} clause of this {@code SELECT} statement.
     * 
     * @return The builder for {@code GROUP BY} clause of this {@code SELECT} statement.
     */
    public GroupByBuilder getGroupBy();

    /**
     * Gets the builder for grouping condition in {@code HAVING} clause of this {@code SELECT} statement.
     * 
     * @return The builder for grouping condition in {@code HAVING} clause of this {@code SELECT} statement.
     */
    public BooleanBuilder getHaving();

    /**
     * Gets the builder for {@code ORDER BY} clause of this {@code SELECT} statement.
     * 
     * @return The builder for {@code ORDER BY} clause of this {@code SELECT} statement.
     */
    public OrderByBuilder getOrderBy();

    /**
     * Checks that all selected columns are in {@code GROUP BY} clause. If they are not, it adds them as last columns of
     * {@code GROUP BY} clause.
     * 
     * @return This builder.
     */
    public QuerySpecificationBuilder trimGroupBy();

    /**
     * Sets the builder for columns in {@code SELECT} statement.
     * 
     * @return This builder.
     */
    public QuerySpecificationBuilder setSelect( ColumnsBuilder builder );

    /**
     * Sets the builder for {@code FROM} clause of this {@code SELECT} statement.
     * 
     * @return This builder.
     */
    public QuerySpecificationBuilder setFrom( FromBuilder builder );

    /**
     * Sets the builder for search condition in {@code WHERE} clause of this {@code SELECT} statement.
     * 
     * @return This builder.
     */
    public QuerySpecificationBuilder setWhere( BooleanBuilder builder );

    /**
     * Sets the builder for {@code GROUP BY} clause of this {@code SELECT} statement.
     * 
     * @return This builder.
     */
    public QuerySpecificationBuilder setGroupBy( GroupByBuilder builder );

    /**
     * Sets the builder for grouping condition in {@code HAVING} clause of this {@code SELECT} statement.
     * 
     * @return This builder.
     */
    public QuerySpecificationBuilder setHaving( BooleanBuilder builder );

    /**
     * Sets the builder for {@code ORDER BY} clause of this {@code SELECT} statement.
     * 
     * @return This builder.
     */
    public QuerySpecificationBuilder setOrderBy( OrderByBuilder builder );
}

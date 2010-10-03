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

package org.sql.generation.api.grammar.builders.query.pgsql;

import org.sql.generation.api.grammar.builders.BooleanBuilder;
import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.builders.query.FromBuilder;
import org.sql.generation.api.grammar.builders.query.GroupByBuilder;
import org.sql.generation.api.grammar.builders.query.OrderByBuilder;
import org.sql.generation.api.grammar.builders.query.QuerySpecificationBuilder;
import org.sql.generation.api.grammar.query.pgsql.LimitClause;
import org.sql.generation.api.grammar.query.pgsql.OffsetClause;
import org.sql.generation.api.grammar.query.pgsql.PgSQLQuerySpecification;

/**
 * Builder to build query specifications with extra elements that are added by PostgreSQL.
 * 
 * @author Stanislav Muhametsin
 * @see QuerySpecificationBuilder
 */
public interface PgSQLQuerySpecificationBuilder
    extends QuerySpecificationBuilder
{
    /**
     * Sets the {@code OFFSET} value of this {@code SELECT} statement.
     * 
     * @param offset The offset for this {@code SELECT} statement.
     * @return This builder.
     */
    public PgSQLQuerySpecificationBuilder offset( OffsetClause offset );

    /**
     * Sets the {@code LIMIT} value of this {@code SELECT} statement
     * 
     * @param limit The limit (maximum amount of results) for this {@code SELECT} statement.
     * @return This builder.
     */
    public PgSQLQuerySpecificationBuilder limit( LimitClause limit );

    /**
     * Helper method to add the first column of this {@code SELECT} statement to {@code ORDER BY} clause, if
     * {@code LIMIT} or {@code OFFSET} clause is present. Calling this method on a {@code SELECT} * query has no effect.
     * 
     * @return This builder.
     */
    public PgSQLQuerySpecificationBuilder setOrderByToFirstColumnIfOffsetOrLimit();

    // The rest are from QuerySpecificationBuilder , overridden here with different return type in order to properly
    // sustain builder-pattern.

    public PgSQLQuerySpecification createExpression();

    public PgSQLQuerySpecificationBuilder trimGroupBy();

    public PgSQLQuerySpecificationBuilder setSelect( ColumnsBuilder builder );

    public PgSQLQuerySpecificationBuilder setFrom( FromBuilder builder );

    public PgSQLQuerySpecificationBuilder setWhere( BooleanBuilder builder );

    public PgSQLQuerySpecificationBuilder setGroupBy( GroupByBuilder builder );

    public PgSQLQuerySpecificationBuilder setHaving( BooleanBuilder builder );

    public PgSQLQuerySpecificationBuilder setOrderBy( OrderByBuilder builder );
}

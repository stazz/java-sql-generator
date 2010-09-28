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
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.query.pgsql.LimitClause;
import org.sql.generation.api.grammar.query.pgsql.OffsetClause;
import org.sql.generation.api.grammar.query.pgsql.PgSQLQuerySpecification;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface PgSQLQuerySpecificationBuilder
    extends QuerySpecificationBuilder
{
    public PgSQLQuerySpecificationBuilder offset( OffsetClause offset );

    public PgSQLQuerySpecificationBuilder limit( LimitClause limit );

    public PgSQLQuerySpecificationBuilder setOrderByToFirstColumnIfOffsetOrLimit( QueryFactory q );

    // The rest are from QuerySpecificationBuilder , overridden here with different return type in order to properly
    // sustain builder-pattern.

    public PgSQLQuerySpecification createExpression();

    public PgSQLQuerySpecificationBuilder trimGroupBy( QueryFactory q );

    public PgSQLQuerySpecificationBuilder setSelect( ColumnsBuilder builder );

    public PgSQLQuerySpecificationBuilder setFrom( FromBuilder builder );

    public PgSQLQuerySpecificationBuilder setWhere( BooleanBuilder builder );

    public PgSQLQuerySpecificationBuilder setGroupBy( GroupByBuilder builder );

    public PgSQLQuerySpecificationBuilder setHaving( BooleanBuilder builder );

    public PgSQLQuerySpecificationBuilder setOrderBy( OrderByBuilder builder );
}

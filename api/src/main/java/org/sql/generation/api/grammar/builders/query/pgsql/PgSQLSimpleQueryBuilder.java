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

import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.builders.query.SimpleQueryBuilder;

/**
 * This builder extends {@link SimpleQueryBuilder} functionality with PostgreSQL-specific elements.
 * 
 * @author Stanislav Muhametsin
 */
public interface PgSQLSimpleQueryBuilder
    extends SimpleQueryBuilder
{

    /**
     * Sets the {@code OFFSET} for this query.
     * 
     * @param offset The offset for this query. Use {@code null} for none.
     * @return This builder.
     */
    public PgSQLSimpleQueryBuilder offset( Integer offset );

    /**
     * Sets the {@code LIMIT} for this query.
     * 
     * @param limit The limit for this query. Use {@code null} for none.
     * @return This builder.
     */
    public PgSQLSimpleQueryBuilder limit( Integer limit );

    // The rest are for builder type compatibility.
    public PgSQLSimpleQueryBuilder select( String... columnNames );

    public PgSQLSimpleQueryBuilder as( String columnAlias );

    public PgSQLSimpleQueryBuilder from( String... tableNames );

    public PgSQLSimpleQueryBuilder where( BooleanExpression searchCondition );

    public PgSQLSimpleQueryBuilder groupBy( String... columns );

    public PgSQLSimpleQueryBuilder having( BooleanExpression groupingCondition );

    public PgSQLSimpleQueryBuilder orderByAsc( String... columns );

    public PgSQLSimpleQueryBuilder orderByDesc( String... columns );
}

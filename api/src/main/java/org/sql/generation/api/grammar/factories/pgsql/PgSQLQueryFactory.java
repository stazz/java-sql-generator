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

package org.sql.generation.api.grammar.factories.pgsql;

import org.sql.generation.api.grammar.builders.query.pgsql.PgSQLQuerySpecificationBuilder;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.query.pgsql.LimitByNumber;
import org.sql.generation.api.grammar.query.pgsql.OffsetClause;

/**
 * The query factory, which is able to create PostgreSQL-specific syntax elements.
 * 
 * @author Stanislav Muhametsin
 */
public interface PgSQLQueryFactory
    extends QueryFactory
{
    /**
     * Returns query builder, which is capable of accepting PostgreSQL-specific syntax elements.
     */
    public PgSQLQuerySpecificationBuilder querySpecificationBuilder();

    /**
     * Creates new limit clause, which limits result by number.
     * 
     * @param limit The maximum amount of results for query.
     * @return The limit clause.
     */
    public LimitByNumber limit( Integer limit );

    /**
     * Creates new offset clause, which sets the offset for query.
     * 
     * @param offset Offset for query.
     * @return The offset clause.
     */
    public OffsetClause offset( Integer offset );
}

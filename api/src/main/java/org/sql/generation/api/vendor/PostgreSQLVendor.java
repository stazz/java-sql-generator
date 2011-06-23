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

package org.sql.generation.api.vendor;

import org.sql.generation.api.grammar.factories.pgsql.PgSQLDataTypeFactory;
import org.sql.generation.api.grammar.factories.pgsql.PgSQLManipulationFactory;
import org.sql.generation.api.grammar.factories.pgsql.PgSQLQueryFactory;
import org.sql.generation.api.grammar.query.pgsql.LimitClause;
import org.sql.generation.api.grammar.query.pgsql.OffsetClause;

/**
 * This is vendor for PostgreSQL database. PostgreSQL provides some extra SQL syntax elements for queries (notably
 * {@code LIMIT} and {@code OFFSET} clauses), and this vendor gives access to factory, which enables the creation of
 * these elements.
 * 
 * @author Stanislav Muhametsin
 * @see PgSQLQueryFactory
 * @see LimitClause
 * @see OffsetClause
 */
public interface PostgreSQLVendor
    extends SQLVendor
{

    /**
     * Returns the query factory, which knows to create PostgreSQL-specific query elements.
     */
    public PgSQLQueryFactory getQueryFactory();

    /**
     * Returns the data type factory, which knows to create PostgreSQL-specific data types as well as pre-defined
     * standard ones.
     */
    public PgSQLDataTypeFactory getDataTypeFactory();

    /**
     * Returns the manipulation factory, which knows to create PostgreSQL-specific data manipulation statements.
     */
    public PgSQLManipulationFactory getManipulationFactory();
}

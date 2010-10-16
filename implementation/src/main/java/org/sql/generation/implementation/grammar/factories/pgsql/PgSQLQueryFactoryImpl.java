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

package org.sql.generation.implementation.grammar.factories.pgsql;

import org.sql.generation.api.grammar.builders.query.pgsql.PgSQLQuerySpecificationBuilder;
import org.sql.generation.api.grammar.builders.query.pgsql.PgSQLSimpleQueryBuilder;
import org.sql.generation.api.grammar.factories.pgsql.PgSQLQueryFactory;
import org.sql.generation.api.grammar.query.pgsql.LimitByNumber;
import org.sql.generation.api.grammar.query.pgsql.OffsetClause;
import org.sql.generation.api.vendor.PostgreSQLVendor;
import org.sql.generation.implementation.grammar.builders.query.pgsql.PgSQLQuerySpecificationBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.pgsql.PgSQLSimpleQueryBuilderImpl;
import org.sql.generation.implementation.grammar.factories.DefaultQueryFactory;
import org.sql.generation.implementation.grammar.query.pgsql.LimitByNumberImpl;
import org.sql.generation.implementation.grammar.query.pgsql.OffsetClauseImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class PgSQLQueryFactoryImpl extends DefaultQueryFactory
    implements PgSQLQueryFactory
{

    public PgSQLQueryFactoryImpl( PostgreSQLVendor vendor )
    {
        super( vendor );
    }

    @Override
    public PgSQLQuerySpecificationBuilder querySpecificationBuilder()
    {
        return new PgSQLQuerySpecificationBuilderImpl( this, this.columnsBuilder(), this.fromBuilder(), this
            .getVendor().getBooleanFactory().booleanBuilder(), this.groupByBuilder(), this.getVendor()
            .getBooleanFactory().booleanBuilder(), this.orderByBuilder() );
    }

    public LimitByNumber limit( Integer limit )
    {
        return new LimitByNumberImpl( limit );
    }

    public OffsetClause offset( Integer offset )
    {
        return new OffsetClauseImpl( offset );
    }

    @Override
    public PgSQLSimpleQueryBuilder simpleQueryBuilder()
    {
        return new PgSQLSimpleQueryBuilderImpl( (PostgreSQLVendor) this.getVendor() );
    }
}

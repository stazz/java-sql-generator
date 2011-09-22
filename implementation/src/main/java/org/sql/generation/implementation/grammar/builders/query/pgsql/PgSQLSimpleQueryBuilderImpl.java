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

package org.sql.generation.implementation.grammar.builders.query.pgsql;

import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.builders.query.QuerySpecificationBuilder;
import org.sql.generation.api.grammar.builders.query.pgsql.PgSQLQuerySpecificationBuilder;
import org.sql.generation.api.grammar.builders.query.pgsql.PgSQLSimpleQueryBuilder;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.factories.pgsql.PgSQLQueryFactory;
import org.sql.generation.api.vendor.PostgreSQLVendor;
import org.sql.generation.implementation.grammar.builders.query.SimpleQueryBuilderImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class PgSQLSimpleQueryBuilderImpl extends SimpleQueryBuilderImpl
    implements PgSQLSimpleQueryBuilder
{

    private Integer _offset;
    private Integer _limit;

    public PgSQLSimpleQueryBuilderImpl( SQLProcessorAggregator processor, PostgreSQLVendor vendor )
    {
        super( processor, vendor );
    }

    public PgSQLSimpleQueryBuilder offset( Integer offset )
    {
        this._offset = offset;
        return this;
    }

    public PgSQLSimpleQueryBuilder limit( Integer limit )
    {
        this._limit = limit;
        return this;
    }

    @Override
    protected void processQuerySpecBuilder( QuerySpecificationBuilder origBuilda )
    {
        super.processQuerySpecBuilder( origBuilda );

        // We know this is the correct type builder, because PostgreSQLVendor returns the factory, creating this type.
        PgSQLQuerySpecificationBuilder builda = (PgSQLQuerySpecificationBuilder) origBuilda;
        PgSQLQueryFactory q = ((PostgreSQLVendor) this.getVendor()).getQueryFactory();

        if( this._offset != null )
        {
            builda.offset( q.offset( this._offset ) );
        }

        if( this._limit != null )
        {
            builda.limit( q.limit( this._limit ) );
        }

        builda.trimGroupBy();
        builda.setOrderByToFirstColumnIfOffsetOrLimit();
    }

    @Override
    public PgSQLSimpleQueryBuilder as( String columnAlias )
    {
        super.as( columnAlias );
        return this;
    }

    @Override
    public PgSQLSimpleQueryBuilder from( TableName... tableNames )
    {
        super.from( tableNames );
        return this;
    }

    @Override
    public PgSQLSimpleQueryBuilder groupBy( String... columns )
    {
        super.groupBy( columns );
        return this;
    }

    @Override
    public PgSQLSimpleQueryBuilder having( BooleanExpression groupingCondition )
    {
        super.having( groupingCondition );
        return this;
    }

    @Override
    public PgSQLSimpleQueryBuilder orderByAsc( String... columns )
    {
        super.orderByAsc( columns );
        return this;
    }

    @Override
    public PgSQLSimpleQueryBuilder orderByDesc( String... columns )
    {
        super.orderByDesc( columns );
        return this;
    }

    @Override
    public PgSQLSimpleQueryBuilder select( String... columnNames )
    {
        super.select( columnNames );
        return this;
    }

    @Override
    public PgSQLSimpleQueryBuilder where( BooleanExpression searchCondition )
    {
        super.where( searchCondition );
        return this;
    }

    @Override
    public PgSQLSimpleQueryBuilder selectAllColumns()
    {
        super.selectAllColumns();
        return this;
    }

}

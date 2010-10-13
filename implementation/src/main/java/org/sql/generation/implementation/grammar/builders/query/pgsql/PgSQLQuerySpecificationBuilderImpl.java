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

import java.util.Iterator;

import org.sql.generation.api.grammar.builders.booleans.BooleanBuilder;
import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.builders.query.FromBuilder;
import org.sql.generation.api.grammar.builders.query.GroupByBuilder;
import org.sql.generation.api.grammar.builders.query.OrderByBuilder;
import org.sql.generation.api.grammar.builders.query.pgsql.PgSQLQuerySpecificationBuilder;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.query.ColumnReferences.ColumnReferenceInfo;
import org.sql.generation.api.grammar.query.Ordering;
import org.sql.generation.api.grammar.query.pgsql.LimitClause;
import org.sql.generation.api.grammar.query.pgsql.OffsetClause;
import org.sql.generation.api.grammar.query.pgsql.PgSQLQuerySpecification;
import org.sql.generation.implementation.grammar.builders.query.QuerySpecificationBuilderImpl;
import org.sql.generation.implementation.grammar.query.pgsql.PgSQLQuerySpecificationImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class PgSQLQuerySpecificationBuilderImpl extends QuerySpecificationBuilderImpl
    implements PgSQLQuerySpecificationBuilder
{

    private OffsetClause _offset;

    private LimitClause _limit;

    public PgSQLQuerySpecificationBuilderImpl( QueryFactory q, ColumnsBuilder select, FromBuilder from,
        BooleanBuilder where, GroupByBuilder groupBy, BooleanBuilder having, OrderByBuilder orderBy )
    {
        super( q, select, from, where, groupBy, having, orderBy );
    }

    @Override
    public PgSQLQuerySpecification createExpression()
    {
        return new PgSQLQuerySpecificationImpl( this.getSelect().createExpression(), this.getFrom().createExpression(),
            this.getWhere().createExpression(), this.getGroupBy().createExpression(), this.getHaving()
                .createExpression(), this.getOrderBy().createExpression(), this._limit, this._offset );
    }

    public PgSQLQuerySpecificationBuilder offset( OffsetClause offset )
    {
        this._offset = offset;
        return this;
    }

    public PgSQLQuerySpecificationBuilder limit( LimitClause limit )
    {
        this._limit = limit;
        return this;
    }

    public PgSQLQuerySpecificationBuilder setOrderByToFirstColumnIfOffsetOrLimit()
    {
        if( (this._offset != null || this._limit != null) && this.getOrderBy().getSortSpecs().isEmpty()
            && !this.getSelect().getColumns().isEmpty() )
        {
            // No ORDER BY specified, but we need it anyway since offset or limit was used
            // Solution: sort by the only column that we select, in ascending order.
            Iterator<ColumnReferenceInfo> columnIter = this.getSelect().getColumns().iterator();
            if( columnIter.hasNext() )
            {
                this.getOrderBy().addSortSpecs(
                    this.getQueryFactory().sortSpec( columnIter.next().getReference(), Ordering.ASCENDING ) );
            }
        }

        return this;
    }

    @Override
    public PgSQLQuerySpecificationBuilder trimGroupBy()
    {
        return (PgSQLQuerySpecificationBuilder) super.trimGroupBy();
    }

    @Override
    public PgSQLQuerySpecificationBuilder setFrom( FromBuilder builder )
    {
        return (PgSQLQuerySpecificationBuilder) super.setFrom( builder );
    }

    @Override
    public PgSQLQuerySpecificationBuilder setGroupBy( GroupByBuilder builder )
    {
        return (PgSQLQuerySpecificationBuilder) super.setGroupBy( builder );
    }

    @Override
    public PgSQLQuerySpecificationBuilder setHaving( BooleanBuilder builder )
    {
        return (PgSQLQuerySpecificationBuilder) super.setHaving( builder );
    }

    @Override
    public PgSQLQuerySpecificationBuilder setOrderBy( OrderByBuilder builder )
    {
        return (PgSQLQuerySpecificationBuilder) super.setOrderBy( builder );
    }

    @Override
    public PgSQLQuerySpecificationBuilder setSelect( ColumnsBuilder builder )
    {
        return (PgSQLQuerySpecificationBuilder) super.setSelect( builder );
    }

    @Override
    public PgSQLQuerySpecificationBuilder setWhere( BooleanBuilder builder )
    {
        return (PgSQLQuerySpecificationBuilder) super.setWhere( builder );
    }
}

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

package org.sql.generation.implementation.grammar.builders.query;

import java.util.ArrayList;
import java.util.List;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.BooleanBuilder;
import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.builders.query.FromBuilder;
import org.sql.generation.api.grammar.builders.query.GroupByBuilder;
import org.sql.generation.api.grammar.builders.query.OrderByBuilder;
import org.sql.generation.api.grammar.builders.query.QuerySpecificationBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.query.ColumnReference;
import org.sql.generation.api.grammar.query.ColumnReferences.ColumnReferenceInfo;
import org.sql.generation.api.grammar.query.GroupingElement;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.api.grammar.query.QuerySpecification;
import org.sql.generation.implementation.grammar.query.QuerySpecificationImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class QuerySpecificationBuilderImpl
    implements QuerySpecificationBuilder
{

    private ColumnsBuilder _select;

    private FromBuilder _from;

    private BooleanBuilder _where;

    private GroupByBuilder _groupBy;

    private BooleanBuilder _having;

    private OrderByBuilder _orderBy;

    public QuerySpecificationBuilderImpl( ColumnsBuilder select, FromBuilder from, BooleanBuilder where,
        GroupByBuilder groupBy, BooleanBuilder having, OrderByBuilder orderBy )
    {
        NullArgumentException.validateNotNull( "select", select );
        NullArgumentException.validateNotNull( "from", from );
        NullArgumentException.validateNotNull( "where", where );
        NullArgumentException.validateNotNull( "group by", groupBy );
        NullArgumentException.validateNotNull( "having", having );
        NullArgumentException.validateNotNull( "order by", orderBy );

        this._select = select;
        this._from = from;
        this._where = where;
        this._groupBy = groupBy;
        this._having = having;
        this._orderBy = orderBy;
    }

    public FromBuilder getFrom()
    {
        return this._from;
    }

    public ColumnsBuilder getSelect()
    {
        return this._select;
    }

    public BooleanBuilder getWhere()
    {
        return this._where;
    }

    public GroupByBuilder getGroupBy()
    {
        return this._groupBy;
    }

    public BooleanBuilder getHaving()
    {
        return this._having;
    }

    public OrderByBuilder getOrderBy()
    {
        return this._orderBy;
    }

    public QuerySpecificationBuilder trimGroupBy( QueryFactory q )
    {
        if( this._having.createExpression() != org.sql.generation.api.grammar.booleans.Predicate.EmptyPredicate.INSTANCE )
        {
            List<ColumnReference> groupByColumns = new ArrayList<ColumnReference>();
            for( GroupingElement element : this._groupBy.getGroupingElements() )
            {
                if( element instanceof OrdinaryGroupingSet )
                {
                    for( NonBooleanExpression exp : ((OrdinaryGroupingSet) element).getColumns() )
                    {
                        if( exp instanceof ColumnReference )
                        {
                            groupByColumns.add( (ColumnReference) exp );
                        }
                    }
                }
            }
            for( ColumnReferenceInfo column : this._select.getColumns() )
            {
                Boolean noColumn = true;
                for( ColumnReference groupByColumn : groupByColumns )
                {
                    if( column.getReference().equals( groupByColumn ) )
                    {
                        noColumn = false;
                        break;
                    }
                }

                if( noColumn )
                {
                    this._groupBy.addGroupingElements( q.groupingElement( column.getReference() ) );
                }
            }

        }

        return this;
    }

    public QuerySpecification createExpression()
    {
        return new QuerySpecificationImpl( this._select.createExpression(), this._from.createExpression(),
            this._where.createExpression(), this._groupBy.createExpression(), this._having.createExpression(),
            this._orderBy.createExpression() );
    }

    public QuerySpecificationBuilder setSelect( ColumnsBuilder builder )
    {
        NullArgumentException.validateNotNull( "builder", builder );
        this._select = builder;
        return this;
    }

    public QuerySpecificationBuilder setFrom( FromBuilder builder )
    {
        NullArgumentException.validateNotNull( "builder", builder );
        this._from = builder;
        return this;
    }

    public QuerySpecificationBuilder setWhere( BooleanBuilder builder )
    {
        NullArgumentException.validateNotNull( "builder", builder );
        this._where = builder;
        return this;
    }

    public QuerySpecificationBuilder setGroupBy( GroupByBuilder builder )
    {
        NullArgumentException.validateNotNull( "builder", builder );
        this._groupBy = builder;
        return this;
    }

    public QuerySpecificationBuilder setHaving( BooleanBuilder builder )
    {
        NullArgumentException.validateNotNull( "builder", builder );
        this._having = builder;
        return this;
    }

    public QuerySpecificationBuilder setOrderBy( OrderByBuilder builder )
    {
        NullArgumentException.validateNotNull( "builder", builder );
        this._orderBy = builder;
        return this;
    }
}

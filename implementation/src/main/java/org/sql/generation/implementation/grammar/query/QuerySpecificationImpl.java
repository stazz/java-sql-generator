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

package org.sql.generation.implementation.grammar.query;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.query.FromClause;
import org.sql.generation.api.grammar.query.GroupByClause;
import org.sql.generation.api.grammar.query.OrderByClause;
import org.sql.generation.api.grammar.query.QuerySpecification;
import org.sql.generation.api.grammar.query.SelectColumnClause;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class QuerySpecificationImpl extends QueryExpressionBodyImpl<QuerySpecification>
    implements QuerySpecification
{

    private final SelectColumnClause _select;

    private final FromClause _from;

    private final BooleanExpression _where;

    private final GroupByClause _groupBy;

    private final BooleanExpression _having;

    private final OrderByClause _orderBy;

    public QuerySpecificationImpl( SQLProcessorAggregator processor, SelectColumnClause select, FromClause from,
        BooleanExpression where, GroupByClause groupBy, BooleanExpression having, OrderByClause orderBy )
    {
        this( processor, QuerySpecification.class, select, from, where, groupBy, having, orderBy );
    }

    protected QuerySpecificationImpl( SQLProcessorAggregator processor, Class<? extends QuerySpecification> queryClass,
        SelectColumnClause select, FromClause from, BooleanExpression where, GroupByClause groupBy,
        BooleanExpression having, OrderByClause orderBy )
    {
        super( processor, queryClass );

        NullArgumentException.validateNotNull( "select", select );

        this._select = select;
        this._from = from;
        this._where = where;
        this._groupBy = groupBy;
        this._having = having;
        this._orderBy = orderBy;
    }

    public SelectColumnClause getColumns()
    {
        return this._select;
    }

    public FromClause getFrom()
    {
        return this._from;
    }

    public BooleanExpression getWhere()
    {
        return this._where;
    }

    public GroupByClause getGroupBy()
    {
        return this._groupBy;
    }

    public BooleanExpression getHaving()
    {
        return this._having;
    }

    public OrderByClause getOrderBy()
    {
        return this._orderBy;
    }

    @Override
    protected boolean doesEqual( QuerySpecification another )
    {
        return this._select.equals( another.getColumns() )
            && TypeableImpl.bothNullOrEquals( this._from, another.getFrom() )
            && TypeableImpl.bothNullOrEquals( this._where, another.getWhere() )
            && TypeableImpl.bothNullOrEquals( this._groupBy, another.getGroupBy() )
            && TypeableImpl.bothNullOrEquals( this._having, another.getHaving() )
            && TypeableImpl.bothNullOrEquals( this._orderBy, another.getOrderBy() );
    }
}

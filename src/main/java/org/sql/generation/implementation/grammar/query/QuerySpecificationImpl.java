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
import org.sql.generation.api.grammar.query.*;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class QuerySpecificationImpl extends QueryExpressionBodyImpl<QuerySpecification>
        implements QuerySpecification {

    private final SelectColumnClause _select;

    private final FromClause _from;

    private final BooleanExpression _where;

    private final GroupByClause _groupBy;

    private final BooleanExpression _having;

    private final OrderByClause _orderBy;

    private final OffsetSpecification _offset;

    private final LimitSpecification _limit;

    public QuerySpecificationImpl(final SQLProcessorAggregator processor, final SelectColumnClause select, final FromClause from,
                                  final BooleanExpression where, final GroupByClause groupBy, final BooleanExpression having, final OrderByClause orderBy,
                                  final OffsetSpecification offset, final LimitSpecification limit) {
        this(processor, QuerySpecification.class, select, from, where, groupBy, having, orderBy, offset, limit);
    }

    protected QuerySpecificationImpl(final SQLProcessorAggregator processor, final Class<? extends QuerySpecification> queryClass,
                                     final SelectColumnClause select, final FromClause from, final BooleanExpression where, final GroupByClause groupBy,
                                     final BooleanExpression having, final OrderByClause orderBy, final OffsetSpecification offset, final LimitSpecification limit) {
        super(processor, queryClass);

        NullArgumentException.validateNotNull("select", select);

        this._select = select;
        this._from = from;
        this._where = where;
        this._groupBy = groupBy;
        this._having = having;
        this._orderBy = orderBy;
        this._offset = offset;
        this._limit = limit;
    }

    @Override
    public SelectColumnClause getColumns() {
        return this._select;
    }

    @Override
    public FromClause getFrom() {
        return this._from;
    }

    @Override
    public BooleanExpression getWhere() {
        return this._where;
    }

    @Override
    public GroupByClause getGroupBy() {
        return this._groupBy;
    }

    @Override
    public BooleanExpression getHaving() {
        return this._having;
    }

    @Override
    public OrderByClause getOrderBy() {
        return this._orderBy;
    }

    @Override
    public LimitSpecification getLimitSpecification() {
        return this._limit;
    }

    @Override
    public OffsetSpecification getOffsetSpecification() {
        return this._offset;
    }

    @Override
    protected boolean doesEqual(final QuerySpecification another) {
        return this._select.equals(another.getColumns())
                && TypeableImpl.bothNullOrEquals(this._from, another.getFrom())
                && TypeableImpl.bothNullOrEquals(this._where, another.getWhere())
                && TypeableImpl.bothNullOrEquals(this._groupBy, another.getGroupBy())
                && TypeableImpl.bothNullOrEquals(this._having, another.getHaving())
                && TypeableImpl.bothNullOrEquals(this._orderBy, another.getOrderBy());
    }
}

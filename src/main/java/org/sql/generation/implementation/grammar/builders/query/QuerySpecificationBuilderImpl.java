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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.booleans.BooleanBuilder;
import org.sql.generation.api.grammar.builders.query.*;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.query.ColumnReference;
import org.sql.generation.api.grammar.query.ColumnReferences.ColumnReferenceInfo;
import org.sql.generation.api.grammar.query.GroupingElement;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.api.grammar.query.QuerySpecification;
import org.sql.generation.implementation.grammar.query.QuerySpecificationImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class QuerySpecificationBuilderImpl extends AbstractQueryFactoryImpl<QuerySpecification>
        implements QuerySpecificationBuilder {

    private ColumnsBuilder _select;

    private FromBuilder _from;

    private BooleanBuilder _where;

    private GroupByBuilder _groupBy;

    private BooleanBuilder _having;

    private OrderByBuilder _orderBy;

    private final QueryFactory _queryFactory;

    public QuerySpecificationBuilderImpl(final SQLProcessorAggregator processor, final QueryFactory q, final ColumnsBuilder select,
                                         final FromBuilder from, final BooleanBuilder where, final GroupByBuilder groupBy, final BooleanBuilder having, final OrderByBuilder orderBy) {
        super(processor);

        NullArgumentException.validateNotNull("Query factory", q);
        NullArgumentException.validateNotNull("select", select);
        NullArgumentException.validateNotNull("from", from);
        NullArgumentException.validateNotNull("where", where);
        NullArgumentException.validateNotNull("group by", groupBy);
        NullArgumentException.validateNotNull("having", having);
        NullArgumentException.validateNotNull("order by", orderBy);

        this._queryFactory = q;
        this._select = select;
        this._from = from;
        this._where = where;
        this._groupBy = groupBy;
        this._having = having;
        this._orderBy = orderBy;
    }

    @Override
    public FromBuilder getFrom() {
        return this._from;
    }

    @Override
    public ColumnsBuilder getSelect() {
        return this._select;
    }

    @Override
    public BooleanBuilder getWhere() {
        return this._where;
    }

    @Override
    public GroupByBuilder getGroupBy() {
        return this._groupBy;
    }

    @Override
    public BooleanBuilder getHaving() {
        return this._having;
    }

    @Override
    public OrderByBuilder getOrderBy() {
        return this._orderBy;
    }

    @Override
    public QuerySpecificationBuilder trimGroupBy() {
        if (this._having.createExpression() != org.sql.generation.api.grammar.booleans.Predicate.EmptyPredicate.INSTANCE) {
            final List<ColumnReference> groupByColumns = new ArrayList<>();
            for (final GroupingElement element : this._groupBy.getGroupingElements()) {
                if (element instanceof OrdinaryGroupingSet) {
                    for (final NonBooleanExpression exp : ((OrdinaryGroupingSet) element).getColumns()) {
                        if (exp instanceof ColumnReference) {
                            groupByColumns.add((ColumnReference) exp);
                        }
                    }
                }
            }
            for (final ColumnReferenceInfo column : this._select.getColumns()) {
                Boolean noColumn = true;
                for (final ColumnReference groupByColumn : groupByColumns) {
                    if (column.getReference().equals(groupByColumn)) {
                        noColumn = false;
                        break;
                    }
                }

                if (noColumn) {
                    this._groupBy.addGroupingElements(this._queryFactory.groupingElement(column.getReference()));
                }
            }

        }

        return this;
    }

    @Override
    public QuerySpecification createExpression() {
        return new QuerySpecificationImpl(this.getProcessor(), this._select.createExpression(),
                this._from.createExpression(), this._where.createExpression(), this._groupBy.createExpression(),
                this._having.createExpression(), this._orderBy.createExpression(), this.getOffset(), this.getLimit());
    }

    @Override
    public QuerySpecificationBuilder setSelect(final ColumnsBuilder builder) {
        NullArgumentException.validateNotNull("builder", builder);
        this._select = builder;
        return this;
    }

    @Override
    public QuerySpecificationBuilder setFrom(final FromBuilder builder) {
        NullArgumentException.validateNotNull("builder", builder);
        this._from = builder;
        return this;
    }

    @Override
    public QuerySpecificationBuilder setWhere(final BooleanBuilder builder) {
        NullArgumentException.validateNotNull("builder", builder);
        this._where = builder;
        return this;
    }

    @Override
    public QuerySpecificationBuilder setGroupBy(final GroupByBuilder builder) {
        NullArgumentException.validateNotNull("builder", builder);
        this._groupBy = builder;
        return this;
    }

    @Override
    public QuerySpecificationBuilder setHaving(final BooleanBuilder builder) {
        NullArgumentException.validateNotNull("builder", builder);
        this._having = builder;
        return this;
    }

    @Override
    public QuerySpecificationBuilder setOrderBy(final OrderByBuilder builder) {
        NullArgumentException.validateNotNull("builder", builder);
        this._orderBy = builder;
        return this;
    }

    protected QueryFactory getQueryFactory() {
        return this._queryFactory;
    }

    @Override
    public QuerySpecificationBuilder limit() {
        return (QuerySpecificationBuilder) super.limit();
    }

    @Override
    public QuerySpecificationBuilder limit(final Integer max) {
        return (QuerySpecificationBuilder) super.limit(max);
    }

    @Override
    public QuerySpecificationBuilder limit(final NonBooleanExpression max) {
        return (QuerySpecificationBuilder) super.limit(max);
    }

    @Override
    public QuerySpecificationBuilder offset(final Integer skip) {
        return (QuerySpecificationBuilder) super.offset(skip);
    }

    @Override
    public QuerySpecificationBuilder offset(final NonBooleanExpression skip) {
        return (QuerySpecificationBuilder) super.offset(skip);
    }
}

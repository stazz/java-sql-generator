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

package org.sql.generation.implementation.grammar.factories;

import org.sql.generation.api.grammar.builders.query.*;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.literals.SQLFunctionLiteral;
import org.sql.generation.api.grammar.query.*;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.builders.query.*;
import org.sql.generation.implementation.grammar.query.*;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.Arrays;

/**
 * @author Stanislav Muhametsin
 */
public class DefaultQueryFactory extends AbstractQueryFactory {

    public DefaultQueryFactory(final SQLVendor vendor, final SQLProcessorAggregator processor) {
        super(vendor, processor);
    }

    @Override
    public QueryExpression createQuery(final QueryExpressionBody body) {
        return new QueryExpressionImpl(this.getProcessor(), body);
    }

    @Override
    public QuerySpecificationBuilder querySpecificationBuilder() {
        return new QuerySpecificationBuilderImpl(this.getProcessor(), this, this.columnsBuilder(), this.fromBuilder(),
                this.getVendor().getBooleanFactory().booleanBuilder(), this.groupByBuilder(), this.getVendor()
                .getBooleanFactory().booleanBuilder(), this.orderByBuilder());
    }

    @Override
    public ColumnsBuilder columnsBuilder(final SetQuantifier setQuantifier) {
        return new ColumnsBuilderImpl(this.getProcessor(), setQuantifier);
    }

    @Override
    public QueryBuilder queryBuilder(final QueryExpressionBody query) {
        return new QueryBuilderImpl(this.getProcessor(), query);
    }

    @Override
    public GroupByBuilder groupByBuilder() {
        return new GroupByBuilderImpl(this.getProcessor());
    }

    @Override
    public OrdinaryGroupingSet groupingElement(final NonBooleanExpression... expressions) {
        return new OrdinaryGroupingSetImpl(this.getProcessor(), expressions);
    }

    @Override
    public FromBuilder fromBuilder() {
        return new FromBuilderImpl(this.getProcessor());
    }

    @Override
    public OrderByBuilder orderByBuilder() {
        return new OrderByBuilderImpl(this.getProcessor());
    }

    @Override
    public SortSpecification sortSpec(final ValueExpression expression, final Ordering ordering) {
        return new SortSpecificationImpl(this.getProcessor(), expression, ordering);
    }

    @Override
    public SimpleQueryBuilder simpleQueryBuilder() {
        return new SimpleQueryBuilderImpl(this.getProcessor(), this.getVendor());
    }

    @Override
    public TableValueConstructor values(final RowValueConstructor... rows) {
        return new TableValueConstructorImpl(this.getProcessor(), Arrays.asList(rows));
    }

    @Override
    public RowSubQuery rowSubQuery(final QueryExpression subQuery) {
        return new RowSubQueryImpl(this.getProcessor(), subQuery);
    }

    @Override
    public RowDefinition row(final ValueExpression... elements) {
        return new RowDefinitionImpl(this.getProcessor(), Arrays.asList(elements));
    }

    @Override
    public QueryExpression callFunction(final String schemaName, final SQLFunctionLiteral function) {
        return this.simpleQueryBuilder().selectAllColumns()
                .from(this.getVendor().getTableReferenceFactory().tableName(schemaName, function)).createExpression();
    }

    @Override
    public LimitSpecification limit(final NonBooleanExpression count) {
        return new LimitSpecificationImpl(this.getProcessor(), count);
    }

    @Override
    public OffsetSpecification offset(final NonBooleanExpression offset) {
        return new OffsetSpecificationImpl(this.getProcessor(), offset);
    }

}

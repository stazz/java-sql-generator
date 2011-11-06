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

import java.util.Arrays;

import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.builders.query.FromBuilder;
import org.sql.generation.api.grammar.builders.query.GroupByBuilder;
import org.sql.generation.api.grammar.builders.query.OrderByBuilder;
import org.sql.generation.api.grammar.builders.query.QueryBuilder;
import org.sql.generation.api.grammar.builders.query.QuerySpecificationBuilder;
import org.sql.generation.api.grammar.builders.query.SimpleQueryBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.literals.SQLFunctionLiteral;
import org.sql.generation.api.grammar.query.LimitSpecification;
import org.sql.generation.api.grammar.query.OffsetSpecification;
import org.sql.generation.api.grammar.query.Ordering;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.QueryExpressionBody;
import org.sql.generation.api.grammar.query.RowDefinition;
import org.sql.generation.api.grammar.query.RowSubQuery;
import org.sql.generation.api.grammar.query.RowValueConstructor;
import org.sql.generation.api.grammar.query.SortSpecification;
import org.sql.generation.api.grammar.query.TableValueConstructor;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.builders.query.ColumnsBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.FromBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.GroupByBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.OrderByBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.QueryBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.QuerySpecificationBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.SimpleQueryBuilderImpl;
import org.sql.generation.implementation.grammar.query.LimitSpecificationImpl;
import org.sql.generation.implementation.grammar.query.OffsetSpecificationImpl;
import org.sql.generation.implementation.grammar.query.OrdinaryGroupingSetImpl;
import org.sql.generation.implementation.grammar.query.QueryExpressionImpl;
import org.sql.generation.implementation.grammar.query.RowDefinitionImpl;
import org.sql.generation.implementation.grammar.query.RowSubQueryImpl;
import org.sql.generation.implementation.grammar.query.SortSpecificationImpl;
import org.sql.generation.implementation.grammar.query.TableValueConstructorImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultQueryFactory extends AbstractQueryFactory
{

    public DefaultQueryFactory( SQLVendor vendor, SQLProcessorAggregator processor )
    {
        super( vendor, processor );
    }

    public QueryExpression createQuery( QueryExpressionBody body )
    {
        return new QueryExpressionImpl( this.getProcessor(), body );
    }

    public QuerySpecificationBuilder querySpecificationBuilder()
    {
        return new QuerySpecificationBuilderImpl( this.getProcessor(), this, this.columnsBuilder(), this.fromBuilder(),
            this.getVendor().getBooleanFactory().booleanBuilder(), this.groupByBuilder(), this.getVendor()
                .getBooleanFactory().booleanBuilder(), this.orderByBuilder() );
    }

    public ColumnsBuilder columnsBuilder( SetQuantifier setQuantifier )
    {
        return new ColumnsBuilderImpl( this.getProcessor(), setQuantifier );
    }

    public QueryBuilder queryBuilder( QueryExpressionBody query )
    {
        return new QueryBuilderImpl( this.getProcessor(), query );
    }

    public GroupByBuilder groupByBuilder()
    {
        return new GroupByBuilderImpl( this.getProcessor() );
    }

    public OrdinaryGroupingSet groupingElement( NonBooleanExpression... expressions )
    {
        return new OrdinaryGroupingSetImpl( this.getProcessor(), expressions );
    }

    public FromBuilder fromBuilder()
    {
        return new FromBuilderImpl( this.getProcessor() );
    }

    public OrderByBuilder orderByBuilder()
    {
        return new OrderByBuilderImpl( this.getProcessor() );
    }

    public SortSpecification sortSpec( ValueExpression expression, Ordering ordering )
    {
        return new SortSpecificationImpl( this.getProcessor(), expression, ordering );
    }

    public SimpleQueryBuilder simpleQueryBuilder()
    {
        return new SimpleQueryBuilderImpl( this.getProcessor(), this.getVendor() );
    }

    public TableValueConstructor values( RowValueConstructor... rows )
    {
        return new TableValueConstructorImpl( this.getProcessor(), Arrays.asList( rows ) );
    }

    public RowSubQuery rowSubQuery( QueryExpression subQuery )
    {
        return new RowSubQueryImpl( this.getProcessor(), subQuery );
    }

    public RowDefinition row( ValueExpression... elements )
    {
        return new RowDefinitionImpl( this.getProcessor(), Arrays.asList( elements ) );
    }

    public QueryExpression callFunction( String schemaName, SQLFunctionLiteral function )
    {
        return this.simpleQueryBuilder().selectAllColumns()
            .from( this.getVendor().getTableReferenceFactory().tableName( schemaName, function ) ).createExpression();
    }

    public LimitSpecification limit( NonBooleanExpression count )
    {
        return new LimitSpecificationImpl( this.getProcessor(), count );
    }

    public OffsetSpecification offset( NonBooleanExpression offset )
    {
        return new OffsetSpecificationImpl( this.getProcessor(), offset );
    }

}

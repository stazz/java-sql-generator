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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.builders.query.FromBuilder;
import org.sql.generation.api.grammar.builders.query.GroupByBuilder;
import org.sql.generation.api.grammar.builders.query.OrderByBuilder;
import org.sql.generation.api.grammar.builders.query.QueryBuilder;
import org.sql.generation.api.grammar.builders.query.QuerySpecificationBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.query.Ordering;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.QueryExpressionBody;
import org.sql.generation.api.grammar.query.SortSpecification;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.builders.query.ColumnsBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.FromBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.GroupByBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.OrderByBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.QueryBuilderImpl;
import org.sql.generation.implementation.grammar.builders.query.QuerySpecificationBuilderImpl;
import org.sql.generation.implementation.grammar.query.OrdinaryGroupingSetImpl;
import org.sql.generation.implementation.grammar.query.QueryExpressionImpl;
import org.sql.generation.implementation.grammar.query.SortSpecificationImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultQueryFactory extends AbstractQueryFactory
{

    private final SQLVendor _vendor;

    public DefaultQueryFactory( SQLVendor vendor )
    {
        NullArgumentException.validateNotNull( "vendor", vendor );
        this._vendor = vendor;
    }

    protected SQLVendor getVendor()
    {
        return this._vendor;
    }

    public QueryExpression createQuery( QueryExpressionBody body )
    {
        return new QueryExpressionImpl( body );
    }

    public QuerySpecificationBuilder querySpecificationBuilder()
    {
        return new QuerySpecificationBuilderImpl( this, this.columnsBuilder(), this.fromBuilder(), this._vendor
            .getBooleanFactory().booleanBuilder(), this.groupByBuilder(), this._vendor.getBooleanFactory()
            .booleanBuilder(), this.orderByBuilder() );
    }

    public ColumnsBuilder columnsBuilder( SetQuantifier setQuantifier )
    {
        return new ColumnsBuilderImpl( setQuantifier );
    }

    public QueryBuilder queryBuilder( QueryExpressionBody query )
    {
        return new QueryBuilderImpl( query );
    }

    public GroupByBuilder groupByBuilder()
    {
        return new GroupByBuilderImpl();
    }

    public OrdinaryGroupingSet groupingElement( NonBooleanExpression... expressions )
    {
        return new OrdinaryGroupingSetImpl( expressions );
    }

    public FromBuilder fromBuilder()
    {
        return new FromBuilderImpl();
    }

    public OrderByBuilder orderByBuilder()
    {
        return new OrderByBuilderImpl();
    }

    public SortSpecification sortSpec( ValueExpression expression, Ordering ordering )
    {
        return new SortSpecificationImpl( expression, ordering );
    }

}

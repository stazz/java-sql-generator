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

package org.sql.generation.api.grammar.factories;

import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.builders.query.FromBuilder;
import org.sql.generation.api.grammar.builders.query.GroupByBuilder;
import org.sql.generation.api.grammar.builders.query.OrderByBuilder;
import org.sql.generation.api.grammar.builders.query.QueryBuilder;
import org.sql.generation.api.grammar.builders.query.QuerySpecificationBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.query.AsteriskSelect;
import org.sql.generation.api.grammar.query.Ordering;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.QueryExpressionBody;
import org.sql.generation.api.grammar.query.SortSpecification;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface QueryFactory
{

    public QueryExpression createQuery( QueryExpressionBody body );

    public QuerySpecificationBuilder querySpecificationBuilder();

    public AsteriskSelect selectAll();

    public AsteriskSelect selectAll( SetQuantifier quantifier );

    public ColumnsBuilder columnsBuilder();

    public ColumnsBuilder columnsBuilder( SetQuantifier setQuantifier );

    public QueryBuilder queryBuilder();

    public QueryBuilder queryBuilder( QueryExpressionBody query );

    public GroupByBuilder groupByBuilder();

    public FromBuilder fromBuilder();

    public OrdinaryGroupingSet groupingElement( NonBooleanExpression... expressions );

    public SortSpecification sortSpec( ValueExpression expression, Ordering ordering );

    public OrderByBuilder orderByBuilder();
}

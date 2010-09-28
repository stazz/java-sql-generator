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

package org.sql.generation.api.grammar.builders.query;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.builders.BooleanBuilder;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.query.QuerySpecification;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface QuerySpecificationBuilder
    extends AbstractBuilder<QuerySpecification>
{

    public ColumnsBuilder getSelect();

    public FromBuilder getFrom();

    public BooleanBuilder getWhere();

    public GroupByBuilder getGroupBy();

    public BooleanBuilder getHaving();

    public OrderByBuilder getOrderBy();

    public QuerySpecificationBuilder trimGroupBy( QueryFactory q );

    public QuerySpecificationBuilder setSelect( ColumnsBuilder builder );

    public QuerySpecificationBuilder setFrom( FromBuilder builder );

    public QuerySpecificationBuilder setWhere( BooleanBuilder builder );

    public QuerySpecificationBuilder setGroupBy( GroupByBuilder builder );

    public QuerySpecificationBuilder setHaving( BooleanBuilder builder );

    public QuerySpecificationBuilder setOrderBy( OrderByBuilder builder );
}

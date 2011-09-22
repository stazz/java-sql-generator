/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.RowSubQuery;
import org.sql.generation.api.grammar.query.RowValueConstructor;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class RowSubQueryImpl extends SQLSyntaxElementBase<RowValueConstructor, RowSubQuery>
    implements RowSubQuery
{

    private final QueryExpression _queryExpression;

    public RowSubQueryImpl( SQLProcessorAggregator processor, QueryExpression queryExpression )
    {
        this( processor, RowSubQuery.class, queryExpression );
    }

    protected RowSubQueryImpl( SQLProcessorAggregator processor, Class<? extends RowSubQuery> realImplementingType,
        QueryExpression queryExpression )
    {
        super( processor, realImplementingType );

        NullArgumentException.validateNotNull( "query expression", queryExpression );

        this._queryExpression = queryExpression;
    }

    public QueryExpression getQueryExpression()
    {
        return this._queryExpression;
    }

    @Override
    protected boolean doesEqual( RowSubQuery another )
    {
        return this._queryExpression.equals( another.getQueryExpression() );
    }

}

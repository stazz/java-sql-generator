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

import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.builders.query.QueryBuilder;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.query.AsteriskSelect;
import org.sql.generation.api.grammar.query.QueryExpressionBody;
import org.sql.generation.implementation.grammar.builders.query.ColumnsBuilderImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class AbstractQueryFactory
    implements QueryFactory
{

    public AsteriskSelect selectAll()
    {
        return this.selectAll( SetQuantifier.ALL );
    }

    public QueryBuilder queryBuilder()
    {
        return this.queryBuilder( QueryExpressionBody.EmptyQueryExpressionBody.INSTANCE );
    }

    public ColumnsBuilder columnsBuilder()
    {
        return new ColumnsBuilderImpl( SetQuantifier.ALL );
    }

}

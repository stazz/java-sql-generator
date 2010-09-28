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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.query.ColumnReferenceByExpression;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class ColumnReferenceByExpressionImpl extends ColumnReferenceImpl<ColumnReferenceByExpression>
    implements ColumnReferenceByExpression
{

    private final ValueExpression _expression;

    public ColumnReferenceByExpressionImpl( ValueExpression expression )
    {
        this( ColumnReferenceByExpression.class, expression );
    }

    protected ColumnReferenceByExpressionImpl( Class<? extends ColumnReferenceByExpression> implClass,
        ValueExpression expression )
    {
        super( implClass );
        NullArgumentException.validateNotNull( "expression", expression );

        this._expression = expression;
    }

    public ValueExpression getExpression()
    {
        return this._expression;
    }
}

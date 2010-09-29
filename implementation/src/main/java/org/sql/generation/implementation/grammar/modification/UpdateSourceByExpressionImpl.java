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

package org.sql.generation.implementation.grammar.modification;

import org.lwdci.spi.context.single.skeletons.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.modification.UpdateSource;
import org.sql.generation.api.grammar.modification.UpdateSourceByExpression;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class UpdateSourceByExpressionImpl extends TypeableImpl<UpdateSource, UpdateSourceByExpression>
    implements UpdateSourceByExpression
{

    private final ValueExpression _valueExpression;

    public UpdateSourceByExpressionImpl( ValueExpression valueExpression )
    {
        this( UpdateSourceByExpression.class, valueExpression );
    }

    protected UpdateSourceByExpressionImpl( Class<? extends UpdateSourceByExpression> expressionClass,
        ValueExpression valueExpression )
    {
        super( expressionClass );
        NullArgumentException.validateNotNull( "expression", valueExpression );

        this._valueExpression = valueExpression;
    }

    public ValueExpression getValueExpression()
    {
        return this._valueExpression;
    }

    @Override
    protected boolean doesEqual( UpdateSourceByExpression another )
    {
        return this._valueExpression.equals( another.getValueExpression() );
    }
}

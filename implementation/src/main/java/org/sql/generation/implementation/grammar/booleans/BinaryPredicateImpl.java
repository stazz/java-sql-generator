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

package org.sql.generation.implementation.grammar.booleans;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BinaryPredicate;
import org.sql.generation.api.grammar.common.NonBooleanExpression;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class BinaryPredicateImpl<ExpressionType extends BinaryPredicate> extends
    AbstractBooleanExpression<ExpressionType>
    implements BinaryPredicate
{

    private final NonBooleanExpression _left;

    private final NonBooleanExpression _right;

    protected BinaryPredicateImpl( Class<? extends ExpressionType> expressionClass, NonBooleanExpression left,
        NonBooleanExpression right )
    {
        super( expressionClass );
        NullArgumentException.validateNotNull( "left", left );
        NullArgumentException.validateNotNull( "right", right );

        this._left = left;
        this._right = right;
    }

    public NonBooleanExpression getLeft()
    {
        return this._left;
    }

    public NonBooleanExpression getRight()
    {
        return this._right;
    }

}

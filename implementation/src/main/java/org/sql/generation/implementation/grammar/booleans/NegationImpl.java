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

import java.util.Arrays;
import java.util.Iterator;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.booleans.Negation;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class NegationImpl extends ComposedBooleanExpressionImpl<Negation>
    implements Negation
{

    private final BooleanExpression _negated;

    public NegationImpl( BooleanExpression negated )
    {
        this( Negation.class, negated );
    }

    protected NegationImpl( Class<? extends Negation> negationClass, BooleanExpression negated )
    {
        super( negationClass );
        NullArgumentException.validateNotNull( "negated", negated );

        this._negated = negated;
    }

    public BooleanExpression getNegated()
    {
        return this._negated;
    }

    public Iterator<BooleanExpression> iterator()
    {
        return Arrays.asList( this._negated ).iterator();
    }
}

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

package org.sql.generation.implementation.grammar.builders.booleans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.InPredicate;
import org.sql.generation.api.grammar.builders.booleans.InBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.implementation.grammar.booleans.InPredicateImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class InBuilderImpl
    implements InBuilder
{

    private final NonBooleanExpression _left;

    private final List<NonBooleanExpression> _expressions;

    public InBuilderImpl( NonBooleanExpression left )
    {
        NullArgumentException.validateNotNull( "left", left );

        this._left = left;
        this._expressions = new ArrayList<NonBooleanExpression>();
    }

    public InBuilder addValues( NonBooleanExpression... expressions )
    {
        NullArgumentException.validateNotNull( "expressions", expressions );
        for( NonBooleanExpression exp : expressions )
        {
            NullArgumentException.validateNotNull( "expression", exp );
        }

        this._expressions.addAll( Arrays.asList( expressions ) );
        return this;
    }

    public InPredicate createExpression()
    {
        return new InPredicateImpl( this._left, this._expressions );
    }
}

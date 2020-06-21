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
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.booleans.Conjunction;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Stanislav Muhametsin
 */
public class ConjunctionImpl extends ComposedBooleanExpressionImpl<Conjunction>
        implements Conjunction {

    private final BooleanExpression _left;

    private final BooleanExpression _right;

    public ConjunctionImpl(final SQLProcessorAggregator processor, final BooleanExpression left, final BooleanExpression right) {
        this(processor, Conjunction.class, left, right);
    }

    protected ConjunctionImpl(final SQLProcessorAggregator processor, final Class<? extends Conjunction> expressionClass,
                              final BooleanExpression left, final BooleanExpression right) {
        super(processor, expressionClass);
        NullArgumentException.validateNotNull("left", left);
        NullArgumentException.validateNotNull("right", right);

        this._left = left;
        this._right = right;
    }

    @Override
    public BooleanExpression getLeft() {
        return this._left;
    }

    @Override
    public BooleanExpression getRight() {
        return this._right;
    }

    @Override
    public Iterator<BooleanExpression> iterator() {
        return Arrays.asList(this._left, this._right).iterator();
    }
}

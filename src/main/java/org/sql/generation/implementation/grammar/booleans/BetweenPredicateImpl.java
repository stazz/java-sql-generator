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

import org.sql.generation.api.grammar.booleans.BetweenPredicate;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class BetweenPredicateImpl extends MultiPredicateImpl<BetweenPredicate>
        implements BetweenPredicate {
    public BetweenPredicateImpl(final SQLProcessorAggregator processor, final NonBooleanExpression left,
                                final NonBooleanExpression minimum, final NonBooleanExpression maximum) {
        this(processor, BetweenPredicate.class, left, minimum, maximum);
    }

    protected BetweenPredicateImpl(final SQLProcessorAggregator processor, final Class<? extends BetweenPredicate> predicateClass,
                                   final NonBooleanExpression left, final NonBooleanExpression minimum, final NonBooleanExpression maximum) {
        super(processor, predicateClass, left, minimum, maximum);
    }

    @Override
    public NonBooleanExpression getMaximum() {
        return this.getRights().get(1);
    }

    @Override
    public NonBooleanExpression getMinimum() {
        return this.getRights().get(0);
    }

}

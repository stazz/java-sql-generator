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

import org.sql.generation.api.grammar.booleans.InPredicate;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class InPredicateImpl extends MultiPredicateImpl<InPredicate>
        implements InPredicate {

    public InPredicateImpl(final SQLProcessorAggregator processor, final NonBooleanExpression left,
                           final List<NonBooleanExpression> rights) {
        this(processor, InPredicate.class, left, rights);
    }

    protected InPredicateImpl(final SQLProcessorAggregator processor, final Class<? extends InPredicate> predicateClass,
                              final NonBooleanExpression left, final List<NonBooleanExpression> rights) {
        super(processor, predicateClass, left, rights);
    }

    public InPredicateImpl(final SQLProcessorAggregator processor, final NonBooleanExpression left, final NonBooleanExpression... rights) {
        this(processor, InPredicate.class, left, rights);
    }

    protected InPredicateImpl(final SQLProcessorAggregator processor, final Class<? extends InPredicate> predicateClass,
                              final NonBooleanExpression left, final NonBooleanExpression... rights) {
        super(processor, predicateClass, left, rights);
    }

}

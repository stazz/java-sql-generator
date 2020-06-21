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

import org.sql.generation.api.grammar.booleans.NotEqualsPredicate;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class NotEqualsPredicateImpl extends BinaryPredicateImpl<NotEqualsPredicate>
        implements NotEqualsPredicate {

    public NotEqualsPredicateImpl(final SQLProcessorAggregator processor, final NonBooleanExpression left,
                                  final NonBooleanExpression right) {
        this(processor, NotEqualsPredicate.class, left, right);
    }

    protected NotEqualsPredicateImpl(final SQLProcessorAggregator processor,
                                     final Class<? extends NotEqualsPredicate> predicateClass, final NonBooleanExpression left, final NonBooleanExpression right) {
        super(processor, predicateClass, left, right);
    }

}

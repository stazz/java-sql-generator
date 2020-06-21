/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
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

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.query.LimitSpecification;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author 2011 Stanislav Muhametsin
 */
public class LimitSpecificationImpl extends SQLSyntaxElementBase<LimitSpecification, LimitSpecification>
        implements LimitSpecification {

    private final NonBooleanExpression _count;

    public LimitSpecificationImpl(final SQLProcessorAggregator processor, final NonBooleanExpression count) {
        this(processor, LimitSpecification.class, count);
    }

    protected LimitSpecificationImpl(final SQLProcessorAggregator processor,
                                     final Class<? extends LimitSpecification> realImplementingType, final NonBooleanExpression count) {
        super(processor, realImplementingType);

        this._count = count;
    }

    @Override
    public NonBooleanExpression getCount() {
        return this._count;
    }

    @Override
    protected boolean doesEqual(final LimitSpecification another) {
        return TypeableImpl.bothNullOrEquals(this._count, another.getCount());
    }

}

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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.query.OffsetSpecification;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author 2011 Stanislav Muhametsin
 */
public class OffsetSpecificationImpl extends SQLSyntaxElementBase<OffsetSpecification, OffsetSpecification>
        implements OffsetSpecification {

    private final NonBooleanExpression _skip;

    public OffsetSpecificationImpl(final SQLProcessorAggregator processor, final NonBooleanExpression skip) {
        this(processor, OffsetSpecification.class, skip);
    }

    protected OffsetSpecificationImpl(final SQLProcessorAggregator processor,
                                      final Class<? extends OffsetSpecification> realImplementingType, final NonBooleanExpression skip) {
        super(processor, realImplementingType);

        NullArgumentException.validateNotNull("Skip", skip);

        this._skip = skip;
    }

    @Override
    public NonBooleanExpression getSkip() {
        return this._skip;
    }

    @Override
    protected boolean doesEqual(final OffsetSpecification another) {
        return this._skip.equals(another.getSkip());
    }

}

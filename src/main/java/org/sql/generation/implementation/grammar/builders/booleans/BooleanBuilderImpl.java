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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.booleans.Predicate;
import org.sql.generation.api.grammar.builders.booleans.BooleanBuilder;
import org.sql.generation.api.grammar.factories.BooleanFactory;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class BooleanBuilderImpl extends SQLBuilderBase
        implements BooleanBuilder {

    private BooleanExpression _topLevelExpression;

    private final BooleanFactory _factory;

    public BooleanBuilderImpl(final SQLProcessorAggregator processor, final BooleanFactory factory) {
        this(processor, factory, Predicate.EmptyPredicate.INSTANCE);
    }

    public BooleanBuilderImpl(final SQLProcessorAggregator processor, final BooleanFactory factory, final BooleanExpression expression) {
        super(processor);
        NullArgumentException.validateNotNull("boolean expression factory", factory);

        this._factory = factory;
        this._topLevelExpression = expression;
    }

    @Override
    public BooleanBuilder and(final BooleanExpression next) {
        this._topLevelExpression = this._factory.and(this._topLevelExpression, next);
        return this;
    }

    @Override
    public BooleanBuilder or(final BooleanExpression next) {
        this._topLevelExpression = this._factory.or(this._topLevelExpression, next);
        return this;
    }

    @Override
    public BooleanBuilder not() {
        this._topLevelExpression = this._factory.not(this._topLevelExpression);
        return this;
    }

    @Override
    public BooleanBuilder reset(final BooleanExpression newExpression) {
        this._topLevelExpression = newExpression;
        return this;
    }

    @Override
    public BooleanExpression createExpression() {
        return this._topLevelExpression == null ? Predicate.EmptyPredicate.INSTANCE : this._topLevelExpression;
    }
}

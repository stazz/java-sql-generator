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

package org.sql.generation.implementation.grammar.factories;

import org.sql.generation.api.grammar.booleans.*;
import org.sql.generation.api.grammar.booleans.BooleanTest.TestType;
import org.sql.generation.api.grammar.booleans.BooleanTest.TruthValue;
import org.sql.generation.api.grammar.builders.booleans.BooleanBuilder;
import org.sql.generation.api.grammar.builders.booleans.InBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.booleans.*;
import org.sql.generation.implementation.grammar.builders.booleans.BooleanBuilderImpl;
import org.sql.generation.implementation.grammar.builders.booleans.InBuilderImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class DefaultBooleanFactory extends AbstractBooleanFactory {

    public DefaultBooleanFactory(final SQLVendor vendor, final SQLProcessorAggregator processor) {
        super(vendor, processor);
    }

    @Override
    public EqualsPredicate eq(final NonBooleanExpression left, final NonBooleanExpression right) {
        return new EqualsPredicateImpl(this.getProcessor(), left, right);
    }

    @Override
    public NotEqualsPredicate neq(final NonBooleanExpression left, final NonBooleanExpression right) {
        return new NotEqualsPredicateImpl(this.getProcessor(), left, right);
    }

    @Override
    public LessThanPredicate lt(final NonBooleanExpression left, final NonBooleanExpression right) {
        return new LessThanPredicateImpl(this.getProcessor(), left, right);
    }

    @Override
    public LessOrEqualPredicate leq(final NonBooleanExpression left, final NonBooleanExpression right) {
        return new LessOrEqualPredicateImpl(this.getProcessor(), left, right);
    }

    @Override
    public GreaterThanPredicate gt(final NonBooleanExpression left, final NonBooleanExpression right) {
        return new GreaterThanPredicateImpl(this.getProcessor(), left, right);
    }

    @Override
    public GreaterOrEqualPredicate geq(final NonBooleanExpression left, final NonBooleanExpression right) {
        return new GreaterOrEqualPredicateImpl(this.getProcessor(), left, right);
    }

    @Override
    public IsNullPredicate isNull(final NonBooleanExpression what) {
        return new IsNullPredicateImpl(this.getProcessor(), what);
    }

    @Override
    public IsNotNullPredicate isNotNull(final NonBooleanExpression what) {
        return new IsNotNullPredicateImpl(this.getProcessor(), what);
    }

    @Override
    public Negation not(final BooleanExpression what) {
        return new NegationImpl(this.getProcessor(), what);
    }

    @Override
    public Conjunction and(final BooleanExpression left, final BooleanExpression right) {
        return new ConjunctionImpl(this.getProcessor(), left, right);
    }

    @Override
    public Disjunction or(final BooleanExpression left, final BooleanExpression right) {
        return new DisjunctionImpl(this.getProcessor(), left, right);
    }

    @Override
    public BetweenPredicate between(final NonBooleanExpression left, final NonBooleanExpression minimum,
                                    final NonBooleanExpression maximum) {
        return new BetweenPredicateImpl(this.getProcessor(), left, minimum, maximum);
    }

    @Override
    public NotBetweenPredicate notBetween(final NonBooleanExpression left, final NonBooleanExpression minimum,
                                          final NonBooleanExpression maximum) {
        return new NotBetweenPredicateImpl(this.getProcessor(), left, minimum, maximum);
    }

    @Override
    public InBuilder inBuilder(final NonBooleanExpression what) {
        return new InBuilderImpl(this.getProcessor(), what);
    }

    @Override
    public NotInPredicate notIn(final NonBooleanExpression what, final NonBooleanExpression... values) {
        return new NotInPredicateImpl(this.getProcessor(), what, values);
    }

    @Override
    public LikePredicate like(final NonBooleanExpression what, final NonBooleanExpression pattern) {
        return new LikePredicateImpl(this.getProcessor(), what, pattern);
    }

    @Override
    public NotLikePredicate notLike(final NonBooleanExpression what, final NonBooleanExpression pattern) {
        return new NotLikePredicateImpl(this.getProcessor(), what, pattern);
    }

    @Override
    public RegexpPredicate regexp(final NonBooleanExpression what, final NonBooleanExpression pattern) {
        return new RegexpPredicateImpl(this.getProcessor(), what, pattern);
    }

    @Override
    public NotRegexpPredicate notRegexp(final NonBooleanExpression what, final NonBooleanExpression pattern) {
        return new NotRegexpPredicateImpl(this.getProcessor(), what, pattern);
    }

    @Override
    public ExistsPredicate exists(final QueryExpression query) {
        return new ExistsPredicateImpl(this.getProcessor(), query);
    }

    @Override
    public UniquePredicate unique(final QueryExpression query) {
        return new UniquePredicateImpl(this.getProcessor(), query);
    }

    @Override
    public BooleanTest test(final BooleanExpression expression, final TestType testType, final TruthValue truthValue) {
        return new BooleanTestImpl(this.getProcessor(), expression, testType, truthValue);
    }

    @Override
    public BooleanBuilder booleanBuilder(final BooleanExpression first) {
        return new BooleanBuilderImpl(this.getProcessor(), this, this.transformNullToEmpty(first));
    }

    private BooleanExpression transformNullToEmpty(final BooleanExpression expression) {
        return expression == null ? Predicate.EmptyPredicate.INSTANCE : expression;
    }
}

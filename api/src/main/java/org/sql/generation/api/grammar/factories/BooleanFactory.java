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

package org.sql.generation.api.grammar.factories;

import org.sql.generation.api.grammar.booleans.BetweenPredicate;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.booleans.BooleanTest;
import org.sql.generation.api.grammar.booleans.BooleanTest.TestType;
import org.sql.generation.api.grammar.booleans.BooleanTest.TruthValue;
import org.sql.generation.api.grammar.booleans.Conjunction;
import org.sql.generation.api.grammar.booleans.Disjunction;
import org.sql.generation.api.grammar.booleans.EqualsPredicate;
import org.sql.generation.api.grammar.booleans.ExistsPredicate;
import org.sql.generation.api.grammar.booleans.GreaterOrEqualPredicate;
import org.sql.generation.api.grammar.booleans.GreaterThanPredicate;
import org.sql.generation.api.grammar.booleans.InPredicate;
import org.sql.generation.api.grammar.booleans.IsNotNullPredicate;
import org.sql.generation.api.grammar.booleans.IsNullPredicate;
import org.sql.generation.api.grammar.booleans.LessOrEqualPredicate;
import org.sql.generation.api.grammar.booleans.LessThanPredicate;
import org.sql.generation.api.grammar.booleans.LikePredicate;
import org.sql.generation.api.grammar.booleans.Negation;
import org.sql.generation.api.grammar.booleans.NotBetweenPredicate;
import org.sql.generation.api.grammar.booleans.NotEqualsPredicate;
import org.sql.generation.api.grammar.booleans.NotInPredicate;
import org.sql.generation.api.grammar.booleans.NotLikePredicate;
import org.sql.generation.api.grammar.booleans.NotRegexpPredicate;
import org.sql.generation.api.grammar.booleans.Predicate;
import org.sql.generation.api.grammar.booleans.RegexpPredicate;
import org.sql.generation.api.grammar.booleans.UniquePredicate;
import org.sql.generation.api.grammar.builders.booleans.BooleanBuilder;
import org.sql.generation.api.grammar.builders.booleans.InBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.vendor.SQLVendor;

/**
 * A factory for creating various {@link BooleanExpression}s. This factory is obtainable from {@link SQLVendor}.
 * 
 * @author Stanislav Muhametsin
 * @see SQLVendor
 */
public interface BooleanFactory
{

    /**
     * Creates new {@link EqualsPredicate}.
     * 
     * @param left The left-side expression.
     * @param right The right-side expression.
     * @return The new {@link EqualsPredicate}.
     */
    public EqualsPredicate eq( NonBooleanExpression left, NonBooleanExpression right );

    /**
     * Creates new {@link NotEqualsPredicate}.
     * 
     * @param left The left-side expression.
     * @param right The right-side expression.
     * @return The new {@link NotEqualsPredicate}.
     */
    public NotEqualsPredicate neq( NonBooleanExpression left, NonBooleanExpression right );

    /**
     * Creates new {@link LessThanPredicate}.
     * 
     * @param left The left-side expression.
     * @param right The right-side expression.
     * @return The new {@link LessThanPredicate}.
     */
    public LessThanPredicate lt( NonBooleanExpression left, NonBooleanExpression right );

    /**
     * Creates new {@link LessOrEqualPredicate}.
     * 
     * @param left The left-side expression.
     * @param right The right-side expression.
     * @return The new {@link LessOrEqualPredicate}.
     */
    public LessOrEqualPredicate leq( NonBooleanExpression left, NonBooleanExpression right );

    /**
     * Creates new {@link GreaterThanPredicate}.
     * 
     * @param left The left-side expression.
     * @param right The right-side expression.
     * @return The new {@link GreaterThanPredicate}.
     */
    public GreaterThanPredicate gt( NonBooleanExpression left, NonBooleanExpression right );

    /**
     * Creates new {@link GreaterOrEqualPredicate}.
     * 
     * @param left The left-side expression.
     * @param right The right-side expression.
     * @return The new {@link GreaterOrEqualPredicate}.
     */
    public GreaterOrEqualPredicate geq( NonBooleanExpression left, NonBooleanExpression right );

    /**
     * Creates new {@link IsNullPredicate}.
     * 
     * @param what The expression for the predicate.
     * @return The new {@link IsNullPredicate}.
     */
    public IsNullPredicate isNull( NonBooleanExpression what );

    /**
     * Creates new {@link IsNotNullPredicate}.
     * 
     * @param what The expression for the predicate.
     * @return The new {@link IsNotNullPredicate}.
     */
    public IsNotNullPredicate isNotNull( NonBooleanExpression what );

    /**
     * Creates new {@link Negation}.
     * 
     * @param what The expression to be negated.
     * @return The new {@link Negation}.
     */
    public Negation not( BooleanExpression what );

    /**
     * Creates new {@link Conjunction}.
     * 
     * @param left The left-side expression.
     * @param right The right-side expression.
     * @return The new {@link Conjunction}.
     */
    public Conjunction and( BooleanExpression left, BooleanExpression right );

    /**
     * Creates new {@link Disjunction}.
     * 
     * @param left The left-side expression.
     * @param right The right-side expression.
     * @return The new {@link Disjunction}.
     */
    public Disjunction or( BooleanExpression left, BooleanExpression right );

    /**
     * Creates new {@link BetweenPredicate}.
     * 
     * @param left What to be between.
     * @param minimum The minimum value.
     * @param maximum The maximum value.
     * @return The new {@link BetweenPredicate}.
     */
    public BetweenPredicate between( NonBooleanExpression left, NonBooleanExpression minimum,
        NonBooleanExpression maximum );

    /**
     * Creates new {@link NotBetweenPredicate}.
     * 
     * @param left What not to be between.
     * @param minimum The minimum value
     * @param maximum The maximum value.
     * @return The new {@link NotBetweenPredicate}.
     */
    public NotBetweenPredicate notBetween( NonBooleanExpression left, NonBooleanExpression minimum,
        NonBooleanExpression maximum );

    /**
     * Creates new {@link InPredicate}.
     * 
     * @param what What to be in accepted values.
     * @param values The accepted values.
     * @return The new {@link InPredicate}.
     */
    public InPredicate in( NonBooleanExpression what, NonBooleanExpression... values );

    /**
     * Returns a builder for {@link InPredicate}.
     * 
     * @param what What to be in accepted values.
     * @return The builder for {@link InPredicate}.
     */
    public InBuilder inBuilder( NonBooleanExpression what );

    /**
     * Creates new {@link NotInPredicate}.
     * 
     * @param what What not to be in values.
     * @param values The values.
     * @return The new {@link NotInPredicate}.
     */
    public NotInPredicate notIn( NonBooleanExpression what, NonBooleanExpression... values );

    /**
     * Creates new {@link LikePredicate}.
     * 
     * @param what What to be like something.
     * @param pattern The pattern to match.
     * @return The new {@link LikePredicate}
     */
    public LikePredicate like( NonBooleanExpression what, NonBooleanExpression pattern );

    /**
     * Creates new {@link NotLikePredicate}.
     * 
     * @param what What not to be like something.
     * @param pattern The pattern.
     * @return The new {@link NotLikePredicate}.
     */
    public NotLikePredicate notLike( NonBooleanExpression what, NonBooleanExpression pattern );

    /**
     * Creates new {@link RegexpPredicate}.
     * 
     * @param what What to match.
     * @param pattern The pattern to match.
     * @return The new {@link NotRegexpPredicate}.
     */
    public RegexpPredicate regexp( NonBooleanExpression what, NonBooleanExpression pattern );

    /**
     * Creates new {@link NotRegexpPredicate}.
     * 
     * @param what What would be not matching the pattern.
     * @param pattern The pattern to use.
     * @return The new {@link NotRegexpPredicate}.
     */
    public NotRegexpPredicate notRegexp( NonBooleanExpression what, NonBooleanExpression pattern );

    /**
     * Creates new {@link ExistsPredicate}.
     * 
     * @param query A query to use.
     * @return The new {@link ExistsPredicate}.
     */
    public ExistsPredicate exists( QueryExpression query );

    /**
     * Creates new {@link UniquePredicate}.
     * 
     * @param query A query to use.
     * @return The new {@link UniquePredicate}.
     */
    public UniquePredicate unique( QueryExpression query );

    /**
     * Creates new {@link BooleanTest}.
     * 
     * @param expression The expresssion to test.
     * @param testType The test type to use.
     * @param truthValue The truth value to use.
     * @return The new {@link BooleanTest}.
     */
    public BooleanTest test( BooleanExpression expression, TestType testType, TruthValue truthValue );

    /**
     * Returns new {@link BooleanBuilder} with {@link Predicate.EmptyPredicate} as initial value.
     * 
     * @return The new {@link BooleanBuilder}.
     */
    public BooleanBuilder booleanBuilder();

    /**
     * Returns new {@link BooleanBuilder} with given boolean expression as initial value.
     * 
     * @param first The initial value for boolean expression.
     * @return The new {@link BooleanBuilder}.
     */
    public BooleanBuilder booleanBuilder( BooleanExpression first );

}

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
import org.sql.generation.implementation.grammar.booleans.BetweenPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.BooleanTestImpl;
import org.sql.generation.implementation.grammar.booleans.ConjunctionImpl;
import org.sql.generation.implementation.grammar.booleans.DisjunctionImpl;
import org.sql.generation.implementation.grammar.booleans.EqualsPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.ExistsPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.GreaterOrEqualPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.GreaterThanPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.IsNotNullPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.IsNullPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.LessOrEqualPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.LessThanPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.LikePredicateImpl;
import org.sql.generation.implementation.grammar.booleans.NegationImpl;
import org.sql.generation.implementation.grammar.booleans.NotBetweenPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.NotEqualsPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.NotInPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.NotLikePredicateImpl;
import org.sql.generation.implementation.grammar.booleans.NotRegexpPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.RegexpPredicateImpl;
import org.sql.generation.implementation.grammar.booleans.UniquePredicateImpl;
import org.sql.generation.implementation.grammar.builders.booleans.BooleanBuilderImpl;
import org.sql.generation.implementation.grammar.builders.booleans.InBuilderImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultBooleanFactory extends AbstractBooleanFactory
{

    public DefaultBooleanFactory( SQLVendor vendor, SQLProcessorAggregator processor )
    {
        super( vendor, processor );
    }

    public EqualsPredicate eq( NonBooleanExpression left, NonBooleanExpression right )
    {
        return new EqualsPredicateImpl( this.getProcessor(), left, right );
    }

    public NotEqualsPredicate neq( NonBooleanExpression left, NonBooleanExpression right )
    {
        return new NotEqualsPredicateImpl( this.getProcessor(), left, right );
    }

    public LessThanPredicate lt( NonBooleanExpression left, NonBooleanExpression right )
    {
        return new LessThanPredicateImpl( this.getProcessor(), left, right );
    }

    public LessOrEqualPredicate leq( NonBooleanExpression left, NonBooleanExpression right )
    {
        return new LessOrEqualPredicateImpl( this.getProcessor(), left, right );
    }

    public GreaterThanPredicate gt( NonBooleanExpression left, NonBooleanExpression right )
    {
        return new GreaterThanPredicateImpl( this.getProcessor(), left, right );
    }

    public GreaterOrEqualPredicate geq( NonBooleanExpression left, NonBooleanExpression right )
    {
        return new GreaterOrEqualPredicateImpl( this.getProcessor(), left, right );
    }

    public IsNullPredicate isNull( NonBooleanExpression what )
    {
        return new IsNullPredicateImpl( this.getProcessor(), what );
    }

    public IsNotNullPredicate isNotNull( NonBooleanExpression what )
    {
        return new IsNotNullPredicateImpl( this.getProcessor(), what );
    }

    public Negation not( BooleanExpression what )
    {
        return new NegationImpl( this.getProcessor(), what );
    }

    public Conjunction and( BooleanExpression left, BooleanExpression right )
    {
        return new ConjunctionImpl( this.getProcessor(), left, right );
    }

    public Disjunction or( BooleanExpression left, BooleanExpression right )
    {
        return new DisjunctionImpl( this.getProcessor(), left, right );
    }

    public BetweenPredicate between( NonBooleanExpression left, NonBooleanExpression minimum,
        NonBooleanExpression maximum )
    {
        return new BetweenPredicateImpl( this.getProcessor(), left, minimum, maximum );
    }

    public NotBetweenPredicate notBetween( NonBooleanExpression left, NonBooleanExpression minimum,
        NonBooleanExpression maximum )
    {
        return new NotBetweenPredicateImpl( this.getProcessor(), left, minimum, maximum );
    }

    public InBuilder inBuilder( NonBooleanExpression what )
    {
        return new InBuilderImpl( this.getProcessor(), what );
    }

    public NotInPredicate notIn( NonBooleanExpression what, NonBooleanExpression... values )
    {
        return new NotInPredicateImpl( this.getProcessor(), what, values );
    }

    public LikePredicate like( NonBooleanExpression what, NonBooleanExpression pattern )
    {
        return new LikePredicateImpl( this.getProcessor(), what, pattern );
    }

    public NotLikePredicate notLike( NonBooleanExpression what, NonBooleanExpression pattern )
    {
        return new NotLikePredicateImpl( this.getProcessor(), what, pattern );
    }

    public RegexpPredicate regexp( NonBooleanExpression what, NonBooleanExpression pattern )
    {
        return new RegexpPredicateImpl( this.getProcessor(), what, pattern );
    }

    public NotRegexpPredicate notRegexp( NonBooleanExpression what, NonBooleanExpression pattern )
    {
        return new NotRegexpPredicateImpl( this.getProcessor(), what, pattern );
    }

    public ExistsPredicate exists( QueryExpression query )
    {
        return new ExistsPredicateImpl( this.getProcessor(), query );
    }

    public UniquePredicate unique( QueryExpression query )
    {
        return new UniquePredicateImpl( this.getProcessor(), query );
    }

    public BooleanTest test( BooleanExpression expression, TestType testType, TruthValue truthValue )
    {
        return new BooleanTestImpl( this.getProcessor(), expression, testType, truthValue );
    }

    public BooleanBuilder booleanBuilder( BooleanExpression first )
    {
        return new BooleanBuilderImpl( this.getProcessor(), this, this.transformNullToEmpty( first ) );
    }

    private final BooleanExpression transformNullToEmpty( BooleanExpression expression )
    {
        return expression == null ? Predicate.EmptyPredicate.INSTANCE : expression;
    }
}

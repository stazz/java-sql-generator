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
import org.sql.generation.api.grammar.booleans.RegexpPredicate;
import org.sql.generation.api.grammar.booleans.UniquePredicate;
import org.sql.generation.api.grammar.builders.BooleanBuilder;
import org.sql.generation.api.grammar.builders.InBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.query.QueryExpression;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface BooleanFactory
{

    public EqualsPredicate eq( NonBooleanExpression left, NonBooleanExpression right );

    public NotEqualsPredicate neq( NonBooleanExpression left, NonBooleanExpression right );

    public LessThanPredicate lt( NonBooleanExpression left, NonBooleanExpression right );

    public LessOrEqualPredicate leq( NonBooleanExpression left, NonBooleanExpression right );

    public GreaterThanPredicate gt( NonBooleanExpression left, NonBooleanExpression right );

    public GreaterOrEqualPredicate geq( NonBooleanExpression left, NonBooleanExpression right );

    public IsNullPredicate isNull( NonBooleanExpression what );

    public IsNotNullPredicate isNotNull( NonBooleanExpression what );

    public Negation not( BooleanExpression what );

    public Conjunction and( BooleanExpression left, BooleanExpression right );

    public Disjunction or( BooleanExpression left, BooleanExpression right );

    public BetweenPredicate between( NonBooleanExpression left, NonBooleanExpression minimum,
        NonBooleanExpression maximum );

    public NotBetweenPredicate notBetween( NonBooleanExpression left, NonBooleanExpression minimum,
        NonBooleanExpression maximum );

    public InPredicate in( NonBooleanExpression what, NonBooleanExpression... values );

    public InBuilder inBuilder( NonBooleanExpression what );

    public NotInPredicate notIn( NonBooleanExpression what, NonBooleanExpression... values );

    public LikePredicate like( NonBooleanExpression what, NonBooleanExpression pattern );

    public NotLikePredicate notLike( NonBooleanExpression what, NonBooleanExpression pattern );

    public RegexpPredicate regexp( NonBooleanExpression what, NonBooleanExpression pattern );

    public NotRegexpPredicate notRegexp( NonBooleanExpression what, NonBooleanExpression pattern );

    public ExistsPredicate exists( QueryExpression query );

    public UniquePredicate unique( QueryExpression query );

    public BooleanTest test( BooleanExpression expression, TestType testType, TruthValue truthValue );

    public BooleanBuilder booleanBuilder();

    public BooleanBuilder booleanBuilder( BooleanExpression first );

}

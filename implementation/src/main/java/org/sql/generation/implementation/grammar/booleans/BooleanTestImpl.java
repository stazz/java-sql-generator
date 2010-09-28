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

import java.util.Arrays;
import java.util.Iterator;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.booleans.BooleanTest;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class BooleanTestImpl extends AbstractBooleanExpression<BooleanTest>
    implements BooleanTest
{

    private final BooleanExpression _booleanExpression;
    private final TestType _testType;
    private final TruthValue _truthValue;

    public BooleanTestImpl( BooleanExpression expression, TestType testType, TruthValue truthValue )
    {
        this( BooleanTest.class, expression, testType, truthValue );
    }

    protected BooleanTestImpl( Class<? extends BooleanTest> expressionClass, BooleanExpression expression,
        TestType testType, TruthValue truthValue )
    {
        super( expressionClass );

        NullArgumentException.validateNotNull( "expression", expression );

        this._booleanExpression = expression;
        this._testType = testType;
        this._truthValue = truthValue;
    }

    public BooleanExpression getBooleanExpression()
    {
        return this._booleanExpression;
    }

    public TestType getTestType()
    {
        return this._testType;
    }

    public TruthValue getTruthValue()
    {
        return this._truthValue;
    }

    public Iterator<BooleanExpression> iterator()
    {
        return Arrays.asList( this._booleanExpression ).iterator();
    }
}

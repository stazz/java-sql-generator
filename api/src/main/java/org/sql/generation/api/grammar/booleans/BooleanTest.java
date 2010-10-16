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

package org.sql.generation.api.grammar.booleans;


/**
 * The interface for syntax element representing {@code <X> IS [NOT] (TRUE | FALSE | UNKNOWN)} expression (boolean
 * test), where {@code <X>} is some boolean expression.
 * 
 * @author Stanislav Muhametsin
 */
public interface BooleanTest
    extends ComposedBooleanExpression
{
    /**
     * The type of the test.
     * 
     * @author Stanislav Muhametsin
     */
    public final class TestType
    {

        /**
         * The test which tests the expression against the truth value.
         */
        public static final TestType IS = new TestType();

        /**
         * The test which tests the expression against the negation of the truth value.
         */
        public static final TestType IS_NOT = new TestType();
    }

    /**
     * The tested truth value.
     * 
     * @author Stanislav Muhametsin
     */
    public final class TruthValue
    {

        /**
         * The {@code TRUE} truth value.
         */
        public static final TruthValue TRUE = new TruthValue();

        /**
         * The {@code FALSE} truth value.
         */
        public static final TruthValue FALSE = new TruthValue();

        /**
         * The {@code UNKNOWN} truth value.
         */
        public static final TruthValue UNKNOWN = new TruthValue();
    }

    /**
     * Returns the boolean expression to be tested.
     * 
     * @return The boolean expression to be tested.
     */
    public BooleanExpression getBooleanExpression();

    /**
     * Returns the test type - whether it should, or should not, be something.
     * 
     * @return The test type.
     */
    public TestType getTestType();

    /**
     * The truth value which must evaluate from the expression.
     * 
     * @return The truth value which must evaluate from the expression.
     */
    public TruthValue getTruthValue();
}

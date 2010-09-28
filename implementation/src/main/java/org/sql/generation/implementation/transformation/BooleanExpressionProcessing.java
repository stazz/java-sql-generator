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

package org.sql.generation.implementation.transformation;

import java.util.Iterator;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BinaryPredicate;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.booleans.BooleanTest;
import org.sql.generation.api.grammar.booleans.BooleanTest.TestType;
import org.sql.generation.api.grammar.booleans.ComposedBooleanExpression;
import org.sql.generation.api.grammar.booleans.Conjunction;
import org.sql.generation.api.grammar.booleans.Disjunction;
import org.sql.generation.api.grammar.booleans.MultiPredicate;
import org.sql.generation.api.grammar.booleans.Negation;
import org.sql.generation.api.grammar.booleans.UnaryPredicate;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * Currently not thread-safe.
 * 
 * @author Stanislav Muhametsin
 */
public class BooleanExpressionProcessing
{

    public static abstract class AbstractBooleanProcessor<RealType extends BooleanExpression> extends
        AbstractProcessor<BooleanExpression, RealType>
    {
        public AbstractBooleanProcessor( Class<RealType> realType )
        {
            super( realType );
        }

    }

    public static class UnaryPredicateProcessor extends AbstractBooleanProcessor<UnaryPredicate>
    {

        public static enum UnaryOperatorOrientation
        {
            BEFORE, // After expression
            AFTER
            // Before expression
        }

        public static final UnaryOperatorOrientation DEFAULT_ORIENTATION = UnaryOperatorOrientation.AFTER;

        private final UnaryOperatorOrientation _orientation;
        private final String _operator;

        public UnaryPredicateProcessor( String operator )
        {
            this( DEFAULT_ORIENTATION, operator );
        }

        public UnaryPredicateProcessor( UnaryOperatorOrientation unaryOrientation, String unaryOperator )
        {
            super( UnaryPredicate.class );
            NullArgumentException.validateNotNull( "unary operator orientation", unaryOrientation );
            NullArgumentException.validateNotNull( "unary operator", unaryOperator );

            this._orientation = unaryOrientation;
            this._operator = unaryOperator;
        }

        protected void doProcess( SQLProcessorAggregator processor, UnaryPredicate predicate, StringBuilder builder )
        {
            UnaryOperatorOrientation orientation = this._orientation;
            if( orientation == UnaryOperatorOrientation.BEFORE )
            {
                builder.append( this._operator ).append( SQLConstants.TOKEN_SEPARATOR );
            }

            NonBooleanExpression exp = predicate.getValueExpression();
            Boolean isQuery = exp instanceof QueryExpression;
            if( isQuery )
            {
                builder.append( SQLConstants.OPEN_PARENTHESIS );
            }
            processor.process( exp, builder );
            if( isQuery )
            {
                builder.append( SQLConstants.CLOSE_PARENTHESIS );
            }

            if( orientation == UnaryOperatorOrientation.AFTER )
            {
                builder.append( SQLConstants.TOKEN_SEPARATOR ).append( this._operator );
            }

        }
    }

    public static class BinaryPredicateProcessor extends AbstractBooleanProcessor<BinaryPredicate>
    {
        private final String _operator;

        public BinaryPredicateProcessor( String binaryOperator )
        {
            super( BinaryPredicate.class );
            NullArgumentException.validateNotNull( "binary operator", binaryOperator );

            this._operator = binaryOperator;
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, BinaryPredicate predicate, StringBuilder builder )
        {
            processor.process( predicate.getLeft(), builder );
            builder.append( SQLConstants.TOKEN_SEPARATOR ).append( this._operator )
                .append( SQLConstants.TOKEN_SEPARATOR );
            processor.process( predicate.getRight(), builder );

        }
    }

    public static class MultiPredicateProcessor extends AbstractBooleanProcessor<MultiPredicate>
    {
        private final String _operator;
        private final String _separator;
        private final Boolean _needParenthesis;

        public MultiPredicateProcessor( String multiOperator, String multiSeparator, Boolean needParenthesis )
        {
            super( MultiPredicate.class );
            NullArgumentException.validateNotNull( "multi-operator", multiOperator );
            NullArgumentException.validateNotNull( "multi separator", multiSeparator );
            NullArgumentException.validateNotNull( "need parenthesis", needParenthesis );

            this._operator = multiOperator;
            this._separator = multiSeparator;
            this._needParenthesis = needParenthesis;
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, MultiPredicate predicate, StringBuilder builder )
        {
            processor.process( predicate.getLeft(), builder );
            builder.append( SQLConstants.TOKEN_SEPARATOR ).append( this._operator )
                .append( SQLConstants.TOKEN_SEPARATOR );
            if( this._needParenthesis )
            {
                builder.append( SQLConstants.OPEN_PARENTHESIS );
            }

            Iterator<NonBooleanExpression> iter = predicate.getRights().iterator();
            while( iter.hasNext() )
            {
                NonBooleanExpression next = iter.next();
                Boolean isQuery = next instanceof QueryExpression;

                if( isQuery )
                {
                    builder.append( SQLConstants.OPEN_PARENTHESIS );
                }

                processor.process( next, builder );

                if( isQuery )
                {
                    builder.append( SQLConstants.CLOSE_PARENTHESIS );
                }

                if( iter.hasNext() )
                {
                    builder.append( this._separator );
                }
            }

            if( this._needParenthesis )
            {
                builder.append( ")" );
            }
        }
    }

    public static class ComposedExpressionProcessor extends AbstractBooleanProcessor<ComposedBooleanExpression>
    {
        public ComposedExpressionProcessor()
        {
            super( ComposedBooleanExpression.class );
        }

        @Override
        public void doProcess( SQLProcessorAggregator processor, ComposedBooleanExpression expression,
            StringBuilder builder )
        {
            if( expression instanceof Negation )
            {
                Negation negation = (Negation) expression;
                StringBuilder negationBuilder = new StringBuilder();
                processor.process( negation.getNegated(), negationBuilder );
                String negationString = negationBuilder.toString();
                if( negationString.trim().length() > 0 )
                {
                    builder.append( SQLConstants.NOT ).append( SQLConstants.TOKEN_SEPARATOR )
                        .append( SQLConstants.OPEN_PARENTHESIS ).append( negationString )
                        .append( SQLConstants.CLOSE_PARENTHESIS );
                }
            }
            else if( expression instanceof BooleanTest )
            {
                BooleanTest test = (BooleanTest) expression;
                StringBuilder testBuilder = new StringBuilder();
                processor.process( test.getBooleanExpression(), testBuilder );
                String testString = testBuilder.toString();
                if( testString.trim().length() > 0 )
                {
                    TestType testType = test.getTestType();
                    Boolean hasTest = testType != null;
                    if( hasTest )
                    {
                        builder.append( SQLConstants.OPEN_PARENTHESIS );
                    }
                    builder.append( testString );
                    if( hasTest )
                    {
                        builder.append( SQLConstants.CLOSE_PARENTHESIS ).append( "IS" )
                            .append( SQLConstants.TOKEN_SEPARATOR );
                        if( testType == TestType.IS_NOT )
                        {
                            builder.append( "NOT" ).append( SQLConstants.TOKEN_SEPARATOR );
                        }

                        builder.append( test.getTruthValue().toString() );
                    }
                }
            }
            else
            {
                if( expression instanceof Conjunction )
                {
                    Conjunction conjunction = (Conjunction) expression;
                    this.processBinaryExpression( processor, conjunction.getLeft(), conjunction.getRight(), builder,
                        SQLConstants.AND );
                }
                else if( expression instanceof Disjunction )
                {
                    Disjunction disjunction = (Disjunction) expression;
                    this.processBinaryExpression( processor, disjunction.getLeft(), disjunction.getRight(), builder,
                        SQLConstants.OR );
                }
            }
        }

        protected void processBinaryExpression( SQLProcessorAggregator processor, BooleanExpression left,
            BooleanExpression right, StringBuilder builder, String operator )
        {
            StringBuilder leftBuilder = new StringBuilder();
            StringBuilder rightBuilder = new StringBuilder();
            processor.process( left, leftBuilder );
            processor.process( right, rightBuilder );
            String leftStr = leftBuilder.toString();
            String rightStr = rightBuilder.toString();
            Boolean leftOK = ProcessorUtils.notNullAndNotEmpty( leftStr );
            Boolean rightOK = ProcessorUtils.notNullAndNotEmpty( rightStr );
            Boolean leftNegation = left instanceof Negation;
            Boolean rightNegation = right instanceof Negation;

            if( leftOK && rightOK )
            {
                builder.append( SQLConstants.OPEN_PARENTHESIS );
            }
            if( leftOK )
            {
                if( leftNegation )
                {
                    builder.append( SQLConstants.OPEN_PARENTHESIS );
                }
                builder.append( leftStr );
                if( leftNegation )
                {
                    builder.append( SQLConstants.CLOSE_PARENTHESIS );
                }
            }
            if( leftOK && rightOK )
            {
                builder.append( SQLConstants.TOKEN_SEPARATOR ).append( operator ).append( SQLConstants.TOKEN_SEPARATOR );
            }
            if( rightOK )
            {
                if( rightNegation )
                {
                    builder.append( SQLConstants.OPEN_PARENTHESIS );
                }
                builder.append( rightStr );
                if( rightNegation )
                {
                    builder.append( SQLConstants.CLOSE_PARENTHESIS );
                }
            }

            if( leftOK && rightOK )
            {
                builder.append( SQLConstants.CLOSE_PARENTHESIS );
            }
        }
    }

}

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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.*;
import org.sql.generation.api.grammar.booleans.BooleanTest.TestType;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.implementation.grammar.booleans.BooleanUtils;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.Iterator;

/**
 * Currently not thread-safe.
 *
 * @author Stanislav Muhametsin
 */
public class BooleanExpressionProcessing {

    public static class UnaryPredicateProcessor extends AbstractProcessor<UnaryPredicate> {

        public enum UnaryOperatorOrientation {
            BEFORE_EXPRESSION, // After expression
            AFTER_EXPRESSION
            // Before expression
        }

        public static final UnaryOperatorOrientation DEFAULT_ORIENTATION = UnaryOperatorOrientation.AFTER_EXPRESSION;

        private final UnaryOperatorOrientation _orientation;
        private final String _operator;

        public UnaryPredicateProcessor(final String operator) {
            this(UnaryPredicateProcessor.DEFAULT_ORIENTATION, operator);
        }

        public UnaryPredicateProcessor(final UnaryOperatorOrientation unaryOrientation, final String unaryOperator) {
            super(UnaryPredicate.class);
            NullArgumentException.validateNotNull("unary operator orientation", unaryOrientation);
            NullArgumentException.validateNotNull("unary operator", unaryOperator);

            this._orientation = unaryOrientation;
            this._operator = unaryOperator;
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final UnaryPredicate predicate, final StringBuilder builder) {
            final UnaryOperatorOrientation orientation = this._orientation;
            if (orientation == UnaryOperatorOrientation.BEFORE_EXPRESSION) {
                builder.append(this._operator).append(SQLConstants.TOKEN_SEPARATOR);
            }

            final NonBooleanExpression exp = predicate.getValueExpression();
            final Boolean isQuery = exp instanceof QueryExpression;
            if (isQuery) {
                builder.append(SQLConstants.OPEN_PARENTHESIS);
            }
            processor.process(exp, builder);
            if (isQuery) {
                builder.append(SQLConstants.CLOSE_PARENTHESIS);
            }

            if (orientation == UnaryOperatorOrientation.AFTER_EXPRESSION) {
                builder.append(SQLConstants.TOKEN_SEPARATOR).append(this._operator);
            }

        }
    }

    public static class BinaryPredicateProcessor extends AbstractProcessor<BinaryPredicate> {
        private final String _operator;

        public BinaryPredicateProcessor(final String binaryOperator) {
            super(BinaryPredicate.class);
            NullArgumentException.validateNotNull("binary operator", binaryOperator);

            this._operator = binaryOperator;
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final BinaryPredicate predicate, final StringBuilder builder) {
            processor.process(predicate.getLeft(), builder);
            builder.append(SQLConstants.TOKEN_SEPARATOR).append(this._operator)
                    .append(SQLConstants.TOKEN_SEPARATOR);
            processor.process(predicate.getRight(), builder);

        }
    }

    public static class MultiPredicateProcessor extends AbstractProcessor<MultiPredicate> {
        private final String _operator;
        private final String _separator;
        private final Boolean _needParenthesis;

        public MultiPredicateProcessor(final String multiOperator, final String multiSeparator, final Boolean needParenthesis) {
            super(MultiPredicate.class);
            NullArgumentException.validateNotNull("multi-operator", multiOperator);
            NullArgumentException.validateNotNull("multi separator", multiSeparator);
            NullArgumentException.validateNotNull("need parenthesis", needParenthesis);

            this._operator = multiOperator;
            this._separator = multiSeparator;
            this._needParenthesis = needParenthesis;
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final MultiPredicate predicate, final StringBuilder builder) {
            processor.process(predicate.getLeft(), builder);
            builder.append(SQLConstants.TOKEN_SEPARATOR).append(this._operator)
                    .append(SQLConstants.TOKEN_SEPARATOR);
            if (this._needParenthesis) {
                builder.append(SQLConstants.OPEN_PARENTHESIS);
            }

            final Iterator<NonBooleanExpression> iter = predicate.getRights().iterator();
            while (iter.hasNext()) {
                final NonBooleanExpression next = iter.next();
                final Boolean isQuery = next instanceof QueryExpression;

                if (isQuery) {
                    builder.append(SQLConstants.OPEN_PARENTHESIS);
                }

                processor.process(next, builder);

                if (isQuery) {
                    builder.append(SQLConstants.CLOSE_PARENTHESIS);
                }

                if (iter.hasNext()) {
                    builder.append(this._separator);
                }
            }

            if (this._needParenthesis) {
                builder.append(SQLConstants.CLOSE_PARENTHESIS);
            }
        }
    }

    public static class NegationProcessor extends AbstractProcessor<Negation> {
        public NegationProcessor() {
            super(Negation.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final Negation object, final StringBuilder builder) {
            final BooleanExpression negated = object.getNegated();
            if (!BooleanUtils.isEmpty(negated)) {
                builder.append(SQLConstants.NOT).append(SQLConstants.TOKEN_SEPARATOR);
                aggregator.process(negated, builder);
            }
        }
    }

    public static void processBinaryComposedObject(final SQLProcessorAggregator aggregator, final BooleanExpression left,
                                                   final BooleanExpression right, final StringBuilder builder, final String operator) {
        final Boolean leftEmpty = BooleanUtils.isEmpty(left);
        final Boolean rightEmpty = BooleanUtils.isEmpty(right);
        if (!leftEmpty || !rightEmpty) {
            final Boolean oneEmpty = leftEmpty || rightEmpty;
            if (!oneEmpty) {
                builder.append(SQLConstants.OPEN_PARENTHESIS);
            }
            aggregator.process(left, builder);

            if (!oneEmpty) {
                if (!leftEmpty) {
                    builder.append(SQLConstants.TOKEN_SEPARATOR);
                }
                builder.append(operator);
                if (!rightEmpty) {
                    builder.append(SQLConstants.TOKEN_SEPARATOR);
                }
            }

            aggregator.process(right, builder);
            if (!oneEmpty) {
                builder.append(SQLConstants.CLOSE_PARENTHESIS);
            }
        }
    }

    public static class ConjunctionProcessor extends AbstractProcessor<Conjunction> {

        public ConjunctionProcessor() {
            super(Conjunction.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final Conjunction object, final StringBuilder builder) {
            BooleanExpressionProcessing.processBinaryComposedObject(aggregator, object.getLeft(), object.getRight(), builder, SQLConstants.AND);
        }
    }

    public static class DisjunctionProcessor extends AbstractProcessor<Disjunction> {
        public DisjunctionProcessor() {
            super(Disjunction.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final Disjunction object, final StringBuilder builder) {
            BooleanExpressionProcessing.processBinaryComposedObject(aggregator, object.getLeft(), object.getRight(), builder, SQLConstants.OR);
        }
    }

    public static class BooleanTestProcessor extends AbstractProcessor<BooleanTest> {
        public BooleanTestProcessor() {
            super(BooleanTest.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final BooleanTest object, final StringBuilder builder) {
            final BooleanExpression testable = object.getBooleanExpression();
            builder.append(SQLConstants.OPEN_PARENTHESIS);
            aggregator.process(testable, builder);
            builder.append(SQLConstants.CLOSE_PARENTHESIS).append(SQLConstants.IS)
                    .append(SQLConstants.TOKEN_SEPARATOR);
            if (object.getTestType() == TestType.IS_NOT) {
                builder.append(SQLConstants.NOT).append(SQLConstants.TOKEN_SEPARATOR);
            }

            builder.append(object.getTruthValue().toString());
        }
    }

}

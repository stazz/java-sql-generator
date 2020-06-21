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

import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.common.datatypes.*;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Stanislav Muhametsin
 */
public class DataTypeProcessing {

    public static class UserDefinedDataTypeProcessor extends AbstractProcessor<UserDefinedType> {

        public UserDefinedDataTypeProcessor() {
            super(UserDefinedType.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final UserDefinedType object, final StringBuilder builder) {
            builder.append(object.getTextualRepresentation());
        }
    }

    public static class DecimalProcessor extends AbstractProcessor<Decimal> {

        public DecimalProcessor() {
            super(Decimal.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final Decimal object, final StringBuilder builder) {
            builder.append("DECIMAL");
            if (object.getPrecision() != null) {
                builder.append(SQLConstants.OPEN_PARENTHESIS).append(object.getPrecision());
                if (object.getScale() != null) {
                    builder.append(object.getScale());
                }
                builder.append(SQLConstants.CLOSE_PARENTHESIS);
            }
        }
    }

    public static class NumericProcessor extends AbstractProcessor<Numeric> {

        public NumericProcessor() {
            super(Numeric.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final Numeric object, final StringBuilder builder) {
            builder.append("NUMERIC");
            if (object.getPrecision() != null) {
                builder.append(SQLConstants.OPEN_PARENTHESIS).append(object.getPrecision());
                if (object.getScale() != null) {
                    builder.append(object.getScale());
                }
                builder.append(SQLConstants.CLOSE_PARENTHESIS);
            }
        }
    }

    public static class SQLCharProcessor extends AbstractProcessor<SQLChar> {
        public SQLCharProcessor() {
            super(SQLChar.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final SQLChar object, final StringBuilder builder) {
            builder.append("CHARACTER");
            if (object.isVarying()) {
                builder.append(SQLConstants.TOKEN_SEPARATOR).append("VARYING");
            }

            if (object.getLength() != null) {
                builder.append(SQLConstants.OPEN_PARENTHESIS).append(object.getLength())
                        .append(SQLConstants.CLOSE_PARENTHESIS);
            }
        }
    }

    public static class SQLFloatProcessor extends AbstractProcessor<SQLFloat> {

        public SQLFloatProcessor() {
            super(SQLFloat.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final SQLFloat object, final StringBuilder builder) {
            builder.append("FLOAT");
            if (object.getPrecision() != null) {
                builder.append(SQLConstants.OPEN_PARENTHESIS).append(object.getPrecision())
                        .append(SQLConstants.CLOSE_PARENTHESIS);
            }
        }
    }

    public static class SQLIntervalProcessor extends AbstractProcessor<SQLInterval> {

        private final static Map<IntervalDataType, String> _defaultIntervalDataTypes;

        static {
            final Map<IntervalDataType, String> map = new HashMap<>();
            map.put(IntervalDataType.YEAR, "YEAR");
            map.put(IntervalDataType.MONTH, "MONTH");
            map.put(IntervalDataType.DAY, "DAY");
            map.put(IntervalDataType.HOUR, "HOUR");
            map.put(IntervalDataType.MINUTE, "MINUTE");
            map.put(IntervalDataType.SECOND, "SECOND");

            _defaultIntervalDataTypes = map;
        }

        private final Map<IntervalDataType, String> _intervalDataTypes;

        public SQLIntervalProcessor() {
            this(SQLIntervalProcessor._defaultIntervalDataTypes);
        }

        public SQLIntervalProcessor(final Map<IntervalDataType, String> intervalDataTypes) {
            super(SQLInterval.class);

            this._intervalDataTypes = intervalDataTypes;
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final SQLInterval object, final StringBuilder builder) {
            builder.append("INTERVAL").append(SQLConstants.TOKEN_SEPARATOR)
                    .append(this._intervalDataTypes.get(object.getStartField()));

            if (object.getStartFieldPrecision() != null) {
                builder.append(SQLConstants.OPEN_PARENTHESIS).append(object.getStartFieldPrecision());

                if (object.getEndField() == null && object.getSecondFracs() != null) {
                    builder.append(SQLConstants.COMMA).append(SQLConstants.TOKEN_SEPARATOR)
                            .append(object.getSecondFracs());
                }

                builder.append(SQLConstants.CLOSE_PARENTHESIS);
            }

            if (object.getEndField() != null) {
                builder.append(SQLConstants.TOKEN_SEPARATOR).append("TO").append(SQLConstants.TOKEN_SEPARATOR)
                        .append(this._intervalDataTypes.get(object.getEndField()));

                if (object.getSecondFracs() != null) {
                    builder.append(SQLConstants.OPEN_PARENTHESIS).append(object.getSecondFracs())
                            .append(SQLConstants.CLOSE_PARENTHESIS);
                }
            }
        }
    }

    public static class SQLTimeProcessor extends AbstractProcessor<SQLTime> {

        public SQLTimeProcessor() {
            super(SQLTime.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final SQLTime object, final StringBuilder builder) {
            builder.append("TIME");
            if (object.getPrecision() != null) {
                builder.append(SQLConstants.OPEN_PARENTHESIS).append(object.getPrecision())
                        .append(SQLConstants.CLOSE_PARENTHESIS);
            }

            if (object.isWithTimeZone() != null) {
                builder.append(SQLConstants.TOKEN_SEPARATOR).append("WITH");
                if (!object.isWithTimeZone()) {
                    builder.append("OUT");
                }

                builder.append(SQLConstants.TOKEN_SEPARATOR).append("TIME ZONE");
            }
        }
    }

    public static class SQLTimeStampProcessor extends AbstractProcessor<SQLTimeStamp> {

        public SQLTimeStampProcessor() {
            super(SQLTimeStamp.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final SQLTimeStamp object, final StringBuilder builder) {
            builder.append("TIMESTAMP");
            if (object.getPrecision() != null) {
                builder.append(SQLConstants.OPEN_PARENTHESIS).append(object.getPrecision())
                        .append(SQLConstants.CLOSE_PARENTHESIS);
            }

            if (object.isWithTimeZone() != null) {
                builder.append(SQLConstants.TOKEN_SEPARATOR).append("WITH");
                if (!object.isWithTimeZone()) {
                    builder.append("OUT");
                }

                builder.append(SQLConstants.TOKEN_SEPARATOR).append("TIME ZONE");
            }
        }
    }
}

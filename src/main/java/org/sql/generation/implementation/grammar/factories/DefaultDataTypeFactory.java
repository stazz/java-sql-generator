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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.datatypes.*;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.common.datatypes.*;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class DefaultDataTypeFactory extends AbstractDataTypeFactory {

    public DefaultDataTypeFactory(final SQLVendor vendor, final SQLProcessorAggregator processor) {
        super(vendor, processor);
    }

    @Override
    public BigInt bigInt() {
        return BigIntImpl.INSTANCE;
    }

    @Override
    public Decimal decimal(final Integer precision, final Integer scale) {
        return precision == null ? DecimalImpl.PLAIN_DECIMAL : new DecimalImpl(precision, scale);
    }

    @Override
    public DoublePrecision doublePrecision() {
        return DoublePrecisionImpl.INSTANCE;
    }

    @Override
    public Numeric numeric(final Integer precision, final Integer scale) {
        return precision == null ? NumericImpl.PLAIN_NUMERIC : new NumericImpl(precision, scale);
    }

    @Override
    public Real real() {
        return RealImpl.INSTANCE;
    }

    @Override
    public SmallInt smallInt() {
        return SmallIntImpl.INSTANCE;
    }

    @Override
    public SQLBoolean sqlBoolean() {
        return SQLBooleanImpl.INSTANCE;
    }

    @Override
    public SQLChar sqlChar(final Integer length) {
        return length == null ? SQLCharImpl.PLAIN_FIXED : new SQLCharImpl(false, length);
    }

    @Override
    public SQLChar sqlVarChar(final Integer length) {
        return length == null ? SQLCharImpl.PLAIN_VARYING : new SQLCharImpl(true, length);
    }

    @Override
    public SQLDate date() {
        return SQLDateImpl.INSTANCE;
    }

    @Override
    public SQLFloat sqlFloat(final Integer precision) {
        return precision == null ? SQLFloatImpl.PLAIN_FLOAT : new SQLFloatImpl(precision);
    }

    @Override
    public SQLInteger integer() {
        return SQLIntegerImpl.INSTANCE;
    }

    @Override
    public SQLInterval yearMonthInterval(final IntervalDataType startField, final Integer startFieldPrecision,
                                         final IntervalDataType endField) {
        NullArgumentException.validateNotNull("Start field", startField);

        SQLInterval result = null;
        if ((startField == IntervalDataType.YEAR || startField == IntervalDataType.MONTH)
                && (endField == null || endField == IntervalDataType.YEAR || endField == IntervalDataType.MONTH)) {
            result = new SQLIntervalImpl(startField, startFieldPrecision, endField, null);
        } else {
            throw new IllegalArgumentException("The interval data types must be either YEAR or MONTH.");
        }

        return result;
    }

    @Override
    public SQLInterval dayTimeInterval(final IntervalDataType startField, final Integer startFieldPrecision,
                                       final IntervalDataType endField, Integer secondFracs) {
        NullArgumentException.validateNotNull("Start field", startField);
        SQLInterval result = null;
        if (startField != IntervalDataType.YEAR
                && startField != IntervalDataType.MONTH
                && (endField == null || (endField != IntervalDataType.YEAR && endField != IntervalDataType.MONTH && startField != IntervalDataType.SECOND))) {
            if (secondFracs != null
                    && (startField != IntervalDataType.SECOND || (endField != null && endField != IntervalDataType.SECOND))) {
                // Trying to set second fractionals, even when not needed
                secondFracs = null;
            }

            if (endField == null && secondFracs != null && startFieldPrecision == null) {
                throw new IllegalArgumentException(
                        "When specifying second fracs for single day-time intervals, the start field precision must be specified also.");
            }

            result = new SQLIntervalImpl(startField, startFieldPrecision, endField, secondFracs);
        } else {
            throw new IllegalArgumentException(
                    "The interval data types must be either DAY, HOUR, MINUTE, or SECOND. For single day-time intervals, the start field must not be SECOND if end field is non-null.");
        }
        return result;
    }

    @Override
    public SQLTime time(final Integer precision, final Boolean withTimeZone) {
        SQLTime result = null;
        if (precision == null) {
            if (withTimeZone == null) {
                result = SQLTimeImpl.PLAIN_TIME;
            } else if (withTimeZone) {
                result = SQLTimeImpl.PLAIN_TIME_WITH_TZ;
            } else {
                result = SQLTimeImpl.PLAIN_TIME_WITHOUT_TZ;
            }
        } else {
            result = new SQLTimeImpl(precision, withTimeZone);
        }

        return result;
    }

    @Override
    public SQLTimeStamp timeStamp(final Integer precision, final Boolean withTimeZone) {
        SQLTimeStamp result = null;
        if (precision == null) {
            if (withTimeZone == null) {
                result = SQLTimeStampImpl.PLAIN_TIMESTAMP;
            } else if (withTimeZone) {
                result = SQLTimeStampImpl.PLAIN_TIMESTAMP_WITH_TZ;
            } else {
                result = SQLTimeStampImpl.PLAIN_TIMESTAMP_WITHOUT_TZ;
            }
        } else {
            result = new SQLTimeStampImpl(precision, withTimeZone);
        }

        return result;
    }

    @Override
    public UserDefinedType userDefined(final String textualContent) {
        return new UserDefinedTypeImpl(textualContent);
    }
}

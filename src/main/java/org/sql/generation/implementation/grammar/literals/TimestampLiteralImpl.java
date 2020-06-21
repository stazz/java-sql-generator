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

package org.sql.generation.implementation.grammar.literals;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.literals.TimestampTimeLiteral;
import org.sql.generation.implementation.grammar.common.NonBooleanExpressionImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.sql.Timestamp;

/**
 * @author Stanislav Muhametsin
 */
public class TimestampLiteralImpl extends NonBooleanExpressionImpl<TimestampTimeLiteral>
        implements TimestampTimeLiteral {

    private final Timestamp _date;

    public TimestampLiteralImpl(final SQLProcessorAggregator processor, final Timestamp date) {
        this(processor, TimestampTimeLiteral.class, date);
    }

    protected TimestampLiteralImpl(final SQLProcessorAggregator processor, final Class<? extends TimestampTimeLiteral> implClass,
                                   final Timestamp date) {
        super(processor, implClass);

        NullArgumentException.validateNotNull("Timestamp", date);
        this._date = date;
    }

    @Override
    public Timestamp getTimestamp() {
        return this._date;
    }

    @Override
    protected boolean doesEqual(final TimestampTimeLiteral another) {
        return this._date.equals(another.getTimestamp());
    }
}

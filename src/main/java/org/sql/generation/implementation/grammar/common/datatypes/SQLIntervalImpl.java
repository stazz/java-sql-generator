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

package org.sql.generation.implementation.grammar.common.datatypes;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.grammar.common.datatypes.IntervalDataType;
import org.sql.generation.api.grammar.common.datatypes.SQLDataType;
import org.sql.generation.api.grammar.common.datatypes.SQLInterval;

/**
 * @author Stanislav Muhametsin
 */
public class SQLIntervalImpl extends TypeableImpl<SQLDataType, SQLInterval>
        implements SQLInterval {

    private final IntervalDataType _startField;

    private final Integer _startFieldPrecision;

    private final IntervalDataType _endField;

    private final Integer _secondFracs;

    public SQLIntervalImpl(final IntervalDataType startField, final Integer startFieldPrecision, final IntervalDataType endField,
                           final Integer secondFracs) {
        super(SQLInterval.class);
        this._startField = startField;
        this._startFieldPrecision = startFieldPrecision;
        this._endField = endField;
        this._secondFracs = secondFracs;
    }

    @Override
    protected boolean doesEqual(final SQLInterval another) {
        return this._startField.equals(another.getStartField())
                && TypeableImpl.bothNullOrEquals(this._startFieldPrecision, another.getStartFieldPrecision())
                && TypeableImpl.bothNullOrEquals(this._endField, another.getEndField())
                && TypeableImpl.bothNullOrEquals(this._secondFracs, another.getSecondFracs());
    }

    /**
     * Returns the start field type for this {@code INTERVAL}.
     *
     * @return The start field type for this {@code INTERVAL}.
     */
    @Override
    public IntervalDataType getStartField() {
        return this._startField;
    }

    /**
     * Return the start field precision for this {@code INTERVAL}. May be {@code null} if none specified.
     *
     * @return The start field precision for this {@code INTERVAL}.
     */
    @Override
    public Integer getStartFieldPrecision() {
        return this._startFieldPrecision;
    }

    /**
     * Returns the end field precision for this {@code INTERVAL}. Will always be {@code null} for single datetime field
     * intervals.
     *
     * @return The end field precision for this {@code INTERVAL}.
     */
    @Override
    public IntervalDataType getEndField() {
        return this._endField;
    }

    /**
     * Returns the fraction seconds precision for this {@code INTERVAL}. Will always be {@code null} if the end field
     * type is not {@link IntervalDataType#SECOND}, or if this is single datetime field interval, and its start type is
     * not {@link IntervalDataType#SECOND}.
     *
     * @return The fraction seconds precision for this {@code INTERVAL}.
     */
    @Override
    public Integer getSecondFracs() {
        return this._secondFracs;
    }
}

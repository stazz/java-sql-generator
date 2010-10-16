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

package org.sql.generation.api.grammar.common.datatypes;

/**
 * This class represents the {@code INTEGER} type, sometimes abbreviated as {@code INT} (typically 32-bit integer).
 * 
 * @author Stanislav Muhametsin
 */
public interface SQLInterval
    extends SQLDataType, ParametrizableDataType
{

    /**
     * Returns the start field type for this {@code INTERVAL}.
     * 
     * @return The start field type for this {@code INTERVAL}.
     */
    public abstract IntervalDataType getStartField();

    /**
     * Return the start field precision for this {@code INTERVAL}. May be {@code null} if none specified.
     * 
     * @return The start field precision for this {@code INTERVAL}.
     */
    public abstract Integer getStartFieldPrecision();

    /**
     * Returns the end field precision for this {@code INTERVAL}. Will always be {@code null} for single datetime field
     * intervals.
     * 
     * @return The end field precision for this {@code INTERVAL}.
     */
    public abstract IntervalDataType getEndField();

    /**
     * Returns the fraction seconds precision for this {@code INTERVAL}. Will always be {@code null} if the end field
     * type is not {@link IntervalDataType#SECOND}, or if this is single datetime field interval, and its start type is
     * not {@link IntervalDataType#SECOND}.
     * 
     * @return The fraction seconds precision for this {@code INTERVAL}.
     */
    public abstract Integer getSecondFracs();

}
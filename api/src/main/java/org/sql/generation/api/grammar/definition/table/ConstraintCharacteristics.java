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

package org.sql.generation.api.grammar.definition.table;


/**
 * This enum represents three different policies for checking constraints time. These policies are
 * {@link #INITIALLY_IMMEDIATE_DEFERRABLE}, {@link #INITIALLY_DEFERRED_DEFERRABLE}, and {@link #NOT_DEFERRABLE}.
 * 
 * @author Stanislav Muhametsin
 */
public final class ConstraintCharacteristics
{
    /**
     * Represents the {@code INITIALLY IMMEDIATE DEFERRABLE} constraint time check.
     */
    public static final ConstraintCharacteristics INITIALLY_IMMEDIATE_DEFERRABLE = new ConstraintCharacteristics();

    /**
     * Represents the {@code INITIALLY DEFERRED DEFERRABLE} constraint time check.
     */
    public static final ConstraintCharacteristics INITIALLY_DEFERRED_DEFERRABLE = new ConstraintCharacteristics();

    /**
     * Represents the {@code [INITIALLY IMMEDIATE] NOT DEFERRABLE} constraint time check.
     */
    public static final ConstraintCharacteristics NOT_DEFERRABLE = new ConstraintCharacteristics();

}

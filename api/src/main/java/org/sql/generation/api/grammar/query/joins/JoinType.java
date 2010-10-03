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

package org.sql.generation.api.grammar.query.joins;

/**
 * This enum represents the join type, used in {@link QualifiedJoinedTable} and {@link NaturalJoinedTable}. Is one of
 * {@link #INNER}, {@link #LEFT_OUTER}, {@link #RIGHT_OUTER}, or {@link #FULL_OUTER}.
 * 
 * @author Stanislav Muhametsin
 */
public enum JoinType
{
    /**
     * The {@code INNER} join, typically default.
     */
    INNER,

    /**
     * The {@code LEFT OUTER} join.
     */
    LEFT_OUTER,

    /**
     * The {@code RIGHT OUTER} join.
     */
    RIGHT_OUTER,

    /**
     * The {@code FULL OUTER} join.
     */
    FULL_OUTER
}
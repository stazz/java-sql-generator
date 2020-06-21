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

package org.sql.generation.api.grammar.query;

/**
 * This enum represents the possible set operations to combine queries with. These set operations are {@link #UNION},
 * {@link #INTERSECT}, and {@link #EXCEPT}.
 *
 * @author Stanislav Muhametsin
 */
public final class SetOperation {
    /**
     * The {@code UNION} between two queries.
     */
    public static final SetOperation UNION = new SetOperation();

    /**
     * The {@code INTERSECT} between two queries.
     */
    public static final SetOperation INTERSECT = new SetOperation();

    /**
     * The set difference ({@code EXCEPT}) between two queries.
     */
    public static final SetOperation EXCEPT = new SetOperation();
}

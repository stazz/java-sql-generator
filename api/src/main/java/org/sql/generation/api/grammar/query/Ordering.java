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
 * This is enum for what kind of order will be applied to each ordering column. Can be either {@link #ASCENDING} or
 * {@link #DESCENDING}.
 * 
 * @author Stanislav Muhametsin
 * @see SortSpecification
 */
public enum Ordering
{
    /**
     * The ordering will be ascending ({@code ASC}).
     */
    ASCENDING,

    /**
     * The ordering will be descending ({@code DESC}).
     */
    DESCENDING
}

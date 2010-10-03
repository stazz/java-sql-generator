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

import org.sql.generation.api.grammar.common.SetQuantifier;

/**
 * This syntax element represents two queries which have a set operation ({@code UNION}, {@code INTERSECT}, or
 * {@code EXCEPT}) between them.
 * 
 * @author Stanislav Muhametsin
 * @see SetOperation
 */
public interface QueryExpressionBodyBinary
    extends QueryExpressionBodyActual
{
    /**
     * Returns the set operation to put between queries.
     * 
     * @return The set operation to put between queries.
     * @see SetOperation
     */
    public SetOperation getSetOperation();

    /**
     * Returns the correspondence columns.
     * 
     * @return The correspondence columns.
     * @see CorrespondingSpec
     */
    public CorrespondingSpec getCorrespondingColumns();

    /**
     * Returns the query on the left side of the set operation.
     * 
     * @return The query on the left side of the set operation.
     */
    public QueryExpressionBody getLeft();

    /**
     * Returns the query on the right side of the set operation.
     * 
     * @return The query on the right side of the set operation.
     */
    public QueryExpressionBody getRight();

    /**
     * Returns the set quantifier for the set operation.
     * 
     * @return The set quantifier for the set operation.
     * @see SetQuantifier
     */
    public SetQuantifier getSetQuantifier();
}

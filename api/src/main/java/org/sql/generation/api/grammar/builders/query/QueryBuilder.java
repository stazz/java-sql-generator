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

package org.sql.generation.api.grammar.builders.query;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.query.CorrespondingSpec;
import org.sql.generation.api.grammar.query.QueryExpressionBody;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface QueryBuilder
    extends AbstractBuilder<QueryExpressionBody>
{
    public QueryBuilder union( QueryExpressionBody another );

    public QueryBuilder union( SetQuantifier setQuantifier, QueryExpressionBody another );

    public QueryBuilder union( CorrespondingSpec correspondingSpec, QueryExpressionBody another );

    public QueryBuilder union( SetQuantifier setQuantifier, CorrespondingSpec correspondingSpec,
        QueryExpressionBody another );

    public QueryBuilder intersect( QueryExpressionBody another );

    public QueryBuilder intersect( SetQuantifier setQuantifier, QueryExpressionBody another );

    public QueryBuilder intersect( CorrespondingSpec correspondingSpec, QueryExpressionBody another );

    public QueryBuilder intersect( SetQuantifier setQuantifier, CorrespondingSpec correspondingSpec,
        QueryExpressionBody another );

    public QueryBuilder except( SetQuantifier setQuantifier, CorrespondingSpec correspondingSpec,
        QueryExpressionBody another );

    public QueryBuilder except( QueryExpressionBody another );

    public QueryBuilder except( SetQuantifier setQuantifier, QueryExpressionBody another );

    public QueryBuilder except( CorrespondingSpec correspondingSpec, QueryExpressionBody another );
}

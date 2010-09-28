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


package org.sql.generation.implementation.grammar.builders.internal;

import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.api.grammar.query.joins.JoinSpecification;
import org.sql.generation.api.grammar.query.joins.JoinType;

/**
 *
 * @author Stanislav Muhametsin
 */
public class QualifiedJoinInfo extends AbstractJoinInfo
{

    private final JoinSpecification _joinSpec;

    private final JoinType _joinType;

    public QualifiedJoinInfo( TableReference table, JoinType joinType, JoinSpecification joinSpec )
    {
        super( table );

        if( joinSpec == null || joinType == null )
        {
            throw new IllegalArgumentException( "All arguments must be non-null." );
        }
        this._joinType = joinType;
        this._joinSpec = joinSpec;
    }

    public JoinSpecification getJoinSpec()
    {
        return this._joinSpec;
    }

    public JoinType getJoinType()
    {
        return this._joinType;
    }
}

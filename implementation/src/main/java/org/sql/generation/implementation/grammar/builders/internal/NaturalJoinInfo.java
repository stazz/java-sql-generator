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

import org.sql.generation.api.grammar.query.TableReferencePrimary;
import org.sql.generation.api.grammar.query.joins.JoinType;

/**
 *
 * @author Stanislav Muhametsin
 */
public class NaturalJoinInfo extends DefaultJoinInfo
{
    private final TableReferencePrimary _table;

    private final JoinType _joinType;

    public NaturalJoinInfo( TableReferencePrimary table, JoinType joinType )
    {
        super( table );

        if( table == null || joinType == null )
        {
            throw new IllegalArgumentException( "All arguments must be non-null." );
        }

        this._table = table;
        this._joinType = joinType;
    }

    public TableReferencePrimary getTable()
    {
        return this._table;
    }

    public JoinType getJoinType()
    {
        return this._joinType;
    }
}

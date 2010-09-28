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

/**
 *
 * @author Stanislav Muhametsin
 */
public abstract class AbstractJoinInfo
{
    private final TableReference _table;

    public AbstractJoinInfo( TableReference table )
    {
        if( table == null )
        {
            throw new IllegalArgumentException( "All arguments must be non-null." );
        }

        this._table = table;
    }

    public TableReference getTable()
    {
        return this._table;
    }

}

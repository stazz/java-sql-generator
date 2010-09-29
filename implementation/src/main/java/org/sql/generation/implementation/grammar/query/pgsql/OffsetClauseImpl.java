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

package org.sql.generation.implementation.grammar.query.pgsql;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.query.pgsql.OffsetClause;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class OffsetClauseImpl
    implements OffsetClause
{
    private final Integer _offset;

    public OffsetClauseImpl( Integer offset )
    {
        NullArgumentException.validateNotNull( "offset", offset );
        this._offset = offset;
    }

    public Integer getOffset()
    {
        return this._offset;
    }

    @Override
    public boolean equals( Object obj )
    {
        return super.equals( obj ) || obj instanceof OffsetClause
            && this._offset.equals( ((OffsetClause) obj).getOffset() );
    }
}

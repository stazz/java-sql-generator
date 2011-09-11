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

package org.sql.generation.implementation.grammar.definition.table;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.definition.table.LikeClause;
import org.sql.generation.api.grammar.definition.table.TableElement;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class LikeClauseImpl extends TypeableImpl<TableElement, LikeClause>
    implements LikeClause
{

    private final TableNameDirect _tableName;

    public LikeClauseImpl( TableNameDirect tableName )
    {
        this( LikeClause.class, tableName );
    }

    protected LikeClauseImpl( Class<? extends LikeClause> realImplementingType, TableNameDirect tableName )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "Table name", tableName );

        this._tableName = tableName;
    }

    @Override
    protected boolean doesEqual( LikeClause another )
    {
        return this._tableName.equals( another.getTableName() );
    }

    public TableNameDirect getTableName()
    {
        return this._tableName;
    }
}

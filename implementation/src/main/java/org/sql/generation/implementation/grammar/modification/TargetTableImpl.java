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

package org.sql.generation.implementation.grammar.modification;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.modification.TargetTable;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class TargetTableImpl extends TypeableImpl<TargetTable, TargetTable>
    implements TargetTable
{

    private Boolean _isOnly;

    private TableName _tableName;

    public TargetTableImpl( Boolean isOnly, TableName tableName )
    {
        this( TargetTable.class, isOnly, tableName );
    }

    protected TargetTableImpl( Class<? extends TargetTable> expressionClass, Boolean isOnly, TableName tableName )
    {
        super( expressionClass );

        NullArgumentException.validateNotNull( "table name", tableName );
        if( isOnly == null )
        {
            isOnly = false;
        }

        this._tableName = tableName;
        this._isOnly = isOnly;
    }

    public Boolean isOnly()
    {
        return this._isOnly;
    }

    public TableName getTableName()
    {
        return this._tableName;
    }

    @Override
    protected boolean doesEqual( TargetTable another )
    {
        return this._tableName.equals( another.getTableName() ) && this._isOnly.equals( another.isOnly() );
    }

}

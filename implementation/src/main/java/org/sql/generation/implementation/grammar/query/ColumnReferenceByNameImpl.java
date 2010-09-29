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

package org.sql.generation.implementation.grammar.query;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.query.ColumnReferenceByName;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class ColumnReferenceByNameImpl extends ColumnReferenceImpl<ColumnReferenceByName>
    implements ColumnReferenceByName
{

    private final String _tableName;

    private final String _columnName;

    public ColumnReferenceByNameImpl( String tableName, String columnName )
    {
        this( ColumnReferenceByName.class, tableName, columnName );
    }

    protected ColumnReferenceByNameImpl( Class<? extends ColumnReferenceByName> implClass, String tableName,
        String columnName )
    {
        super( implClass );

        NullArgumentException.validateNotNull( "column name", columnName );

        this._tableName = tableName;
        this._columnName = columnName;
    }

    public String getColumnName()
    {
        return this._columnName;
    }

    public String getTableName()
    {
        return this._tableName;
    }

    @Override
    protected boolean doesEqual( ColumnReferenceByName another )
    {
        return this._columnName.equals( another.getColumnName() )
            && TypeableImpl.bothNullOrEquals( this._tableName, another.getTableName() );
    }
}

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
import org.sql.generation.api.grammar.definition.table.ColumnDefinition;
import org.sql.generation.api.grammar.definition.table.TableElement;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class ColumnDefinitionImpl extends TypeableImpl<TableElement, ColumnDefinition>
    implements ColumnDefinition
{

    private final String _name;
    private final String _dataType;
    private final String _default;
    private final Boolean _mayBeNull;

    protected ColumnDefinitionImpl( Class<? extends ColumnDefinition> realImplementingType, String name,
        String dataType, String defaultStr, Boolean mayBeNull )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "Column name", name );
        NullArgumentException.validateNotNull( "Column data type", dataType );
        NullArgumentException.validateNotNull( "Null policy", mayBeNull );

        this._name = name;
        this._dataType = dataType;
        this._default = defaultStr;
        this._mayBeNull = mayBeNull;
    }

    @Override
    protected boolean doesEqual( ColumnDefinition another )
    {
        return this._name.equals( another.getColumnName() ) && this._dataType.equals( another.getDataType() )
            && TypeableImpl.bothNullOrEquals( this._default, another.getDefault() )
            && this._mayBeNull.equals( another.mayBeNull() );
    }

    public String getColumnName()
    {
        return this._name;
    }

    public String getDataType()
    {
        return this._dataType;
    }

    public String getDefault()
    {
        return this._default;
    }

    public Boolean mayBeNull()
    {
        return this._mayBeNull;
    }

}

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

package org.sql.generation.implementation.grammar.manipulation;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;
import org.sql.generation.api.grammar.manipulation.DropBehaviour;
import org.sql.generation.api.grammar.manipulation.DropColumnDefinition;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DropColumnDefinitionImpl extends TypeableImpl<AlterTableAction, DropColumnDefinition>
    implements DropColumnDefinition
{

    private final String _columnName;
    private final DropBehaviour _dropBehaviour;

    public DropColumnDefinitionImpl( String columnName, DropBehaviour dropBehaviour )
    {
        this( DropColumnDefinition.class, columnName, dropBehaviour );
    }

    protected DropColumnDefinitionImpl( Class<? extends DropColumnDefinition> realImplementingType, String columnName,
        DropBehaviour dropBehaviour )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "Column name", columnName );
        NullArgumentException.validateNotNull( "Drop behaviour", dropBehaviour );

        this._columnName = columnName;
        this._dropBehaviour = dropBehaviour;
    }

    @Override
    protected boolean doesEqual( DropColumnDefinition another )
    {
        return this._dropBehaviour.equals( another.getDropBehaviour() )
            && this._columnName.equals( another.getColumnName() );
    }

    public String getColumnName()
    {
        return this._columnName;
    }

    public DropBehaviour getDropBehaviour()
    {
        return this._dropBehaviour;
    }

}

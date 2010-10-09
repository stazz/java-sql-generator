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
import org.sql.generation.api.grammar.manipulation.AlterColumnAction;
import org.sql.generation.api.grammar.manipulation.AlterColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class AlterColumnDefinitionImpl extends TypeableImpl<AlterTableAction, AlterColumnDefinition>
    implements AlterColumnDefinition
{

    private final String _columnName;
    private final AlterColumnAction _action;

    public AlterColumnDefinitionImpl( String columnName, AlterColumnAction action )
    {
        this( AlterColumnDefinition.class, columnName, action );
    }

    protected AlterColumnDefinitionImpl( Class<? extends AlterColumnDefinition> realImplementingType,
        String columnName, AlterColumnAction action )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "Column name", columnName );
        NullArgumentException.validateNotNull( "Alter column action", action );

        this._columnName = columnName;
        this._action = action;
    }

    @Override
    protected boolean doesEqual( AlterColumnDefinition another )
    {
        return this._columnName.equals( another.getColumnName() ) && this._action.equals( another.getAction() );
    }

    public AlterColumnAction getAction()
    {
        return this._action;
    }

    public String getColumnName()
    {
        return this._columnName;
    }

}

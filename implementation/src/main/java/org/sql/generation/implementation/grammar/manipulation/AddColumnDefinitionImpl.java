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
import org.sql.generation.api.grammar.definition.table.ColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AddColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class AddColumnDefinitionImpl extends TypeableImpl<AlterTableAction, AddColumnDefinition>
    implements AddColumnDefinition
{

    private final ColumnDefinition _columnDefinition;

    public AddColumnDefinitionImpl( ColumnDefinition columnDefinition )
    {
        this( AddColumnDefinition.class, columnDefinition );
    }

    protected AddColumnDefinitionImpl( Class<? extends AddColumnDefinition> realImplementingType,
        ColumnDefinition columnDefinition )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "Column definition", columnDefinition );

        this._columnDefinition = columnDefinition;
    }

    @Override
    protected boolean doesEqual( AddColumnDefinition another )
    {
        return this._columnDefinition.equals( another.getColumnDefinition() );
    }

    public ColumnDefinition getColumnDefinition()
    {
        return this._columnDefinition;
    }

}

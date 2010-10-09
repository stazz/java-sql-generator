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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.manipulation.DropBehaviour;
import org.sql.generation.api.grammar.manipulation.DropTableOrViewStatement;
import org.sql.generation.api.grammar.manipulation.ObjectType;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DropTableOrViewStatementImpl extends DropStatementImpl<DropTableOrViewStatement>
    implements DropTableOrViewStatement
{

    private final TableName _name;

    public DropTableOrViewStatementImpl( ObjectType whatToDrop, DropBehaviour dropBehaviour, TableName name )
    {
        this( DropTableOrViewStatement.class, whatToDrop, dropBehaviour, name );
    }

    protected DropTableOrViewStatementImpl( Class<? extends DropTableOrViewStatement> realImplementingType,
        ObjectType whatToDrop, DropBehaviour dropBehaviour, TableName name )
    {
        super( realImplementingType, whatToDrop, dropBehaviour );

        NullArgumentException.validateNotNull( "Table name", name );

        this._name = name;
    }

    @Override
    protected boolean doesEqual( DropTableOrViewStatement another )
    {
        return this._name.equals( another.getTableName() ) && super.doesEqual( another );
    }

    public TableName getTableName()
    {
        return this._name;
    }

}

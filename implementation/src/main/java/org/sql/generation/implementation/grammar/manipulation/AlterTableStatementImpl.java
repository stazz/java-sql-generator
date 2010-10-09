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
import org.sql.generation.api.grammar.common.SchemaStatement;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;
import org.sql.generation.api.grammar.manipulation.AlterTableStatement;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class AlterTableStatementImpl extends TypeableImpl<SchemaStatement, AlterTableStatement>
    implements AlterTableStatement
{

    private final TableName _tableName;
    private final AlterTableAction _action;

    public AlterTableStatementImpl( TableName tableName, AlterTableAction action )
    {
        this( AlterTableStatement.class, tableName, action );
    }

    protected AlterTableStatementImpl( Class<? extends AlterTableStatement> realImplementingType, TableName tableName,
        AlterTableAction action )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "Table name", tableName );
        NullArgumentException.validateNotNull( "Alter table taction", action );

        this._tableName = tableName;
        this._action = action;
    }

    @Override
    protected boolean doesEqual( AlterTableStatement another )
    {
        return this._tableName.equals( another.getTableName() ) && this._action.equals( another.getAction() );
    }

    public AlterTableAction getAction()
    {
        return this._action;
    }

    public TableName getTableName()
    {
        return this._tableName;
    }

}

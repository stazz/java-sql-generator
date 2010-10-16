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

import org.atp.api.Typeable;
import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.SchemaStatement;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.definition.table.TableCommitAction;
import org.sql.generation.api.grammar.definition.table.TableContentsSource;
import org.sql.generation.api.grammar.definition.table.TableDefinition;
import org.sql.generation.api.grammar.definition.table.TableScope;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class TableDefinitionImpl extends TypeableImpl<SchemaStatement, TableDefinition>
    implements TableDefinition
{

    private final TableCommitAction _commitAction;
    private final TableContentsSource _contents;
    private final TableName _name;
    private final TableScope _scope;

    public TableDefinitionImpl( TableCommitAction commitAction, TableContentsSource contents, TableName name,
        TableScope scope )
    {
        this( TableDefinition.class, commitAction, contents, name, scope );
    }

    protected TableDefinitionImpl( Class<? extends TableDefinition> realImplementingType,
        TableCommitAction commitAction, TableContentsSource contents, TableName name, TableScope scope )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "Table name", name );
        NullArgumentException.validateNotNull( "Table contents", contents );

        this._name = name;
        this._contents = contents;
        this._scope = scope;
        this._commitAction = commitAction;
    }

    @Override
    protected boolean doesEqual( TableDefinition another )
    {
        return this._name.equals( another.getTableName() ) && this._contents.equals( another.getContents() )
            && TypeableImpl.bothNullOrEquals( this._scope, another.getTableScope() )
            && TypeableImpl.bothNullOrEquals( this._commitAction, another.getCommitAction() );
    }

    public Typeable<?> asTypeable()
    {
        return this;
    }

    public TableCommitAction getCommitAction()
    {
        return this._commitAction;
    }

    public TableContentsSource getContents()
    {
        return this._contents;
    }

    public TableName getTableName()
    {
        return this._name;
    }

    public TableScope getTableScope()
    {
        return this._scope;
    }

}

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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.TableNameAbstract;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.query.TableAlias;
import org.sql.generation.api.grammar.query.TableReferenceByName;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class TableReferenceByNameImpl extends TableReferencePrimaryImpl<TableReferenceByName>
    implements TableReferenceByName
{

    private final TableNameAbstract _tableName;

    public TableReferenceByNameImpl( TableNameAbstract tableName, TableAlias alias )
    {
        this( TableReferenceByName.class, tableName, alias );
    }

    protected TableReferenceByNameImpl( Class<? extends TableReferenceByName> implClass, TableNameAbstract tableName,
        TableAlias alias )
    {
        super( implClass, alias );

        NullArgumentException.validateNotNull( "table name", tableName );

        this._tableName = tableName;
    }

    public TableNameAbstract getTableName()
    {
        return this._tableName;
    }
}

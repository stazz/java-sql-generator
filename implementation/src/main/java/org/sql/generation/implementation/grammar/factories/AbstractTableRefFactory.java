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

package org.sql.generation.implementation.grammar.factories;

import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.common.TableNameFunction;
import org.sql.generation.api.grammar.factories.TableReferenceFactory;
import org.sql.generation.api.grammar.literals.SQLFunctionLiteral;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.TableAlias;
import org.sql.generation.api.grammar.query.TableReferenceByExpression;
import org.sql.generation.api.grammar.query.TableReferenceByName;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class AbstractTableRefFactory
    implements TableReferenceFactory
{
    public TableReferenceByName table( TableName tableName )
    {
        return this.table( tableName, null );
    }

    public TableNameDirect tableName( String tableName )
    {
        return this.tableName( null, tableName );
    }

    public TableAlias tableAlias( String tableNameAlias )
    {
        return this.tableAliasWithCols( tableNameAlias );
    }

    public TableReferenceByExpression table( QueryExpression query )
    {
        return this.table( query, null );
    }

    public TableNameFunction tableName( SQLFunctionLiteral function )
    {
        return this.tableName( null, function );
    }
}

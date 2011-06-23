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

package org.sql.generation.implementation.grammar.query.joins;

import org.atp.api.Typeable;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.api.grammar.query.joins.JoinedTable;
import org.sql.generation.implementation.grammar.query.QueryExpressionBodyImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class JoinedTableImpl<TableReferenceType extends JoinedTable> extends
    QueryExpressionBodyImpl<TableReferenceType>
    implements JoinedTable
{

    private final TableReference _left;
    private final TableReference _right;

    protected JoinedTableImpl( Class<? extends TableReferenceType> tableReferenceClass, TableReference left,
        TableReference right )
    {
        super( tableReferenceClass );
        NullArgumentException.validateNotNull( "left", left );
        NullArgumentException.validateNotNull( "right", right );

        this._left = left;
        this._right = right;
    }

    public TableReference getLeft()
    {
        return this._left;
    }

    public TableReference getRight()
    {
        return this._right;
    }

    public Typeable<?> asTypeable()
    {
        return this;
    }

    @Override
    protected boolean doesEqual( TableReferenceType another )
    {
        return this._left.equals( another.getLeft() );
    }

}

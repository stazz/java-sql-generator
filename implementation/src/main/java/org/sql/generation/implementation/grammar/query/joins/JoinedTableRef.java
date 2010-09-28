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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.api.grammar.query.joins.JoinedTable;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class JoinedTableRef<TableReferenceType extends JoinedTable> extends JoinedTableImpl<TableReferenceType>
{

    private final TableReference _right;

    protected JoinedTableRef( Class<? extends TableReferenceType> tableReferenceClass, TableReference left,
        TableReference right )
    {
        super( tableReferenceClass, left );

        NullArgumentException.validateNotNull( "right", right );

        this._right = right;
    }

    public TableReference getRight()
    {
        return this._right;
    }
}

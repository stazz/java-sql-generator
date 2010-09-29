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
import org.sql.generation.api.grammar.query.TableReferencePrimary;
import org.sql.generation.api.grammar.query.joins.JoinType;
import org.sql.generation.api.grammar.query.joins.NaturalJoinedTable;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class NaturalJoinedTableImpl extends JoinedTablePrimary<NaturalJoinedTable>
    implements NaturalJoinedTable
{

    private final JoinType _joinType;

    public NaturalJoinedTableImpl( TableReference left, TableReferencePrimary right, JoinType joinType )
    {
        this( NaturalJoinedTable.class, left, right, joinType );
    }

    protected NaturalJoinedTableImpl( Class<? extends NaturalJoinedTable> implClass, TableReference left,
        TableReferencePrimary right, JoinType joinType )
    {
        super( implClass, left, right );

        NullArgumentException.validateNotNull( "join type", joinType );

        this._joinType = joinType;
    }

    public JoinType getJoinType()
    {
        return this._joinType;
    }

    @Override
    protected boolean doesEqual( NaturalJoinedTable another )
    {
        boolean result = this._joinType.equals( another.getJoinType() ) && this.getRight().equals( another.getRight() );
        if( result )
        {
            result = super.doesEqual( another );
        }

        return result;
    }

}

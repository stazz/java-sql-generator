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

package org.sql.generation.implementation.grammar.modification;

import org.lwdci.spi.context.single.skeletons.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.modification.DeleteBySearch;
import org.sql.generation.api.grammar.modification.DeleteStatement;
import org.sql.generation.api.grammar.modification.TargetTable;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DeleteBySearchImpl extends TypeableImpl<DeleteStatement, DeleteBySearch>
    implements DeleteBySearch
{

    private final TargetTable _targetTable;

    private final BooleanExpression _where;

    public DeleteBySearchImpl( TargetTable targetTable, BooleanExpression where )
    {
        this( DeleteBySearch.class, targetTable, where );
    }

    protected DeleteBySearchImpl( Class<? extends DeleteBySearch> expressionClass, TargetTable targetTable,
        BooleanExpression where )
    {
        super( expressionClass );
        NullArgumentException.validateNotNull( "target table", targetTable );

        this._targetTable = targetTable;
        this._where = where;
    }

    public TargetTable getTargetTable()
    {
        return this._targetTable;
    }

    public BooleanExpression getWhere()
    {
        return this._where;
    }

    @Override
    protected boolean doesEqual( DeleteBySearch another )
    {
        return this._targetTable.equals( another.getTargetTable() )
            && TypeableImpl.bothNullOrEquals( this._where, another.getWhere() );
    }
}

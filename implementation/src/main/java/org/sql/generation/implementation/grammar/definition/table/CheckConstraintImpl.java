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

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.definition.table.CheckConstraint;
import org.sql.generation.api.grammar.definition.table.TableConstraint;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class CheckConstraintImpl extends TypeableImpl<TableConstraint, CheckConstraint>
    implements CheckConstraint
{

    private final BooleanExpression _searchCondition;

    public CheckConstraintImpl( BooleanExpression searchCondition )
    {
        this( CheckConstraint.class, searchCondition );
    }

    protected CheckConstraintImpl( Class<? extends CheckConstraint> realImplementingType,
        BooleanExpression searchCondition )
    {
        super( realImplementingType );

        this._searchCondition = searchCondition;
    }

    @Override
    protected boolean doesEqual( CheckConstraint another )
    {
        return TypeableImpl.bothNullOrEquals( this._searchCondition, another.getCheckCondition() );
    }

    public BooleanExpression getCheckCondition()
    {
        return this._searchCondition;
    }

}

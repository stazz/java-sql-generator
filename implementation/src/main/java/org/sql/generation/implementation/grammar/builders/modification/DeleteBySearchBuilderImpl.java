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

package org.sql.generation.implementation.grammar.builders.modification;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.BooleanBuilder;
import org.sql.generation.api.grammar.builders.modification.DeleteBySearchBuilder;
import org.sql.generation.api.grammar.modification.DeleteBySearch;
import org.sql.generation.api.grammar.modification.TargetTable;
import org.sql.generation.implementation.grammar.modification.DeleteBySearchImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DeleteBySearchBuilderImpl
    implements DeleteBySearchBuilder
{

    private final BooleanBuilder _whereBuilder;

    private TargetTable _targetTable;

    public DeleteBySearchBuilderImpl( BooleanBuilder whereBuilder )
    {
        NullArgumentException.validateNotNull( "where builder", whereBuilder );
        this._whereBuilder = whereBuilder;
    }

    public DeleteBySearch createExpression()
    {

        return new DeleteBySearchImpl( this._targetTable, this._whereBuilder.createExpression() );
    }

    public DeleteBySearchBuilder setTargetTable( TargetTable table )
    {
        NullArgumentException.validateNotNull( "table", table );
        this._targetTable = table;
        return this;
    }

    public TargetTable getTargetTable()
    {
        return this._targetTable;
    }

    public BooleanBuilder getWhere()
    {
        return this._whereBuilder;
    }

}

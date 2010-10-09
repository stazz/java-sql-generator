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

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.definition.table.TableConstraintDefinition;
import org.sql.generation.api.grammar.manipulation.AddTableConstraintDefinition;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;

import org.atp.spi.TypeableImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class AddTableConstraintDefinitionImpl extends TypeableImpl<AlterTableAction, AddTableConstraintDefinition>
    implements AddTableConstraintDefinition
{

    private final TableConstraintDefinition _constraint;

    public AddTableConstraintDefinitionImpl( TableConstraintDefinition constraint )
    {
        this( AddTableConstraintDefinition.class, constraint );
    }

    protected AddTableConstraintDefinitionImpl( Class<? extends AddTableConstraintDefinition> realImplementingType,
        TableConstraintDefinition constraint )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "Constraint", constraint );

        this._constraint = constraint;
    }

    @Override
    protected boolean doesEqual( AddTableConstraintDefinition another )
    {
        return this._constraint.equals( another.getConstraint() );
    }

    public TableConstraintDefinition getConstraint()
    {
        return this._constraint;
    }

}

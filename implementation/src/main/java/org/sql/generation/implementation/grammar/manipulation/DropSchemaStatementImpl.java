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
import org.sql.generation.api.grammar.manipulation.DropBehaviour;
import org.sql.generation.api.grammar.manipulation.DropSchemaStatement;
import org.sql.generation.api.grammar.manipulation.ObjectType;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DropSchemaStatementImpl extends DropStatementImpl<DropSchemaStatement>
    implements DropSchemaStatement
{

    private final String _schemaName;

    public DropSchemaStatementImpl( SQLProcessorAggregator processor, DropBehaviour dropBehaviour, String schemaName )
    {
        this( processor, DropSchemaStatement.class, ObjectType.SCHEMA, dropBehaviour, schemaName );
    }

    protected DropSchemaStatementImpl( SQLProcessorAggregator processor,
        Class<? extends DropSchemaStatement> realImplementingType, ObjectType whatToDrop, DropBehaviour dropBehaviour,
        String schemaName )
    {
        super( processor, realImplementingType, whatToDrop, dropBehaviour );

        NullArgumentException.validateNotNull( "Schema name", schemaName );

        this._schemaName = schemaName;
    }

    @Override
    protected boolean doesEqual( DropSchemaStatement another )
    {
        return this._schemaName.equals( another.getSchemaName() ) && super.doesEqual( another );
    }

    public String getSchemaName()
    {
        return this._schemaName;
    }

}

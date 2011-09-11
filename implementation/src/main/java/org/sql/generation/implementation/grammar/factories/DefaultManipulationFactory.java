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

import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.definition.table.ColumnDefinition;
import org.sql.generation.api.grammar.definition.table.TableConstraintDefinition;
import org.sql.generation.api.grammar.factories.ManipulationFactory;
import org.sql.generation.api.grammar.manipulation.AddColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AddTableConstraintDefinition;
import org.sql.generation.api.grammar.manipulation.AlterColumnAction;
import org.sql.generation.api.grammar.manipulation.AlterColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;
import org.sql.generation.api.grammar.manipulation.AlterTableStatement;
import org.sql.generation.api.grammar.manipulation.DropBehaviour;
import org.sql.generation.api.grammar.manipulation.DropColumnDefinition;
import org.sql.generation.api.grammar.manipulation.DropSchemaStatement;
import org.sql.generation.api.grammar.manipulation.DropTableConstraintDefinition;
import org.sql.generation.api.grammar.manipulation.DropTableOrViewStatement;
import org.sql.generation.api.grammar.manipulation.ObjectType;
import org.sql.generation.api.grammar.manipulation.SetColumnDefault;
import org.sql.generation.implementation.grammar.manipulation.AddColumnDefinitionImpl;
import org.sql.generation.implementation.grammar.manipulation.AddTableConstraintDefinitionImpl;
import org.sql.generation.implementation.grammar.manipulation.AlterColumnDefinitionImpl;
import org.sql.generation.implementation.grammar.manipulation.AlterTableStatementImpl;
import org.sql.generation.implementation.grammar.manipulation.DropColumnDefinitionImpl;
import org.sql.generation.implementation.grammar.manipulation.DropSchemaStatementImpl;
import org.sql.generation.implementation.grammar.manipulation.DropTableConstraintDefinitionImpl;
import org.sql.generation.implementation.grammar.manipulation.DropTableOrViewStatementImpl;
import org.sql.generation.implementation.grammar.manipulation.SetColumnDefaultImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultManipulationFactory
    implements ManipulationFactory
{

    public AlterTableStatement createAlterTableStatement( TableNameDirect tableName, AlterTableAction action )
    {
        return new AlterTableStatementImpl( tableName, action );
    }

    public AddColumnDefinition createAddColumnDefinition( ColumnDefinition definition )
    {
        return new AddColumnDefinitionImpl( definition );
    }

    public AddTableConstraintDefinition createAddTableConstraintDefinition(
        TableConstraintDefinition constraintDefinition )
    {
        return new AddTableConstraintDefinitionImpl( constraintDefinition );
    }

    public AlterColumnDefinition createAlterColumnDefinition( String columnName, AlterColumnAction action )
    {
        return new AlterColumnDefinitionImpl( columnName, action );
    }

    public SetColumnDefault createSetColumnDefault( String newDefault )
    {
        return new SetColumnDefaultImpl( newDefault );
    }

    public DropColumnDefinition createDropColumnDefinition( String columnName, DropBehaviour dropBehaviour )
    {
        return new DropColumnDefinitionImpl( columnName, dropBehaviour );
    }

    public DropTableConstraintDefinition createDropTableConstraintDefinition( String constraintName,
        DropBehaviour dropBehaviour )
    {
        return new DropTableConstraintDefinitionImpl( constraintName, dropBehaviour );
    }

    public DropSchemaStatement createDropSchemaStatement( String schemaName, DropBehaviour dropBehaviour )
    {
        return new DropSchemaStatementImpl( dropBehaviour, schemaName );
    }

    public DropTableOrViewStatement createDropTableOrViewStatement( TableNameDirect tableName, ObjectType theType,
        DropBehaviour dropBehaviour )
    {
        DropTableOrViewStatement result = null;
        if( theType == ObjectType.TABLE || theType == ObjectType.VIEW )
        {
            result = new DropTableOrViewStatementImpl( theType, dropBehaviour, tableName );
        }
        else
        {
            throw new IllegalArgumentException( "Object type " + theType
                + " is not a valid type for DROP TABLE or DROP VIEW statement." );
        }

        return result;
    }

}

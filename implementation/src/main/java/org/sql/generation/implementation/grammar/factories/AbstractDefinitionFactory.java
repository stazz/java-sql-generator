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

import org.sql.generation.api.grammar.common.datatypes.SQLDataType;
import org.sql.generation.api.grammar.definition.table.AutoGenerationPolicy;
import org.sql.generation.api.grammar.definition.table.ColumnDefinition;
import org.sql.generation.api.grammar.definition.table.ConstraintCharacteristics;
import org.sql.generation.api.grammar.definition.table.TableConstraint;
import org.sql.generation.api.grammar.definition.table.TableConstraintDefinition;
import org.sql.generation.api.grammar.factories.DefinitionFactory;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.common.SQLFactoryBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class AbstractDefinitionFactory extends SQLFactoryBase
    implements DefinitionFactory
{

    protected AbstractDefinitionFactory( SQLVendor vendor, SQLProcessorAggregator processor )
    {
        super( vendor, processor );
    }

    public ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType )
    {
        return this.createColumnDefinition( columnName, columnDataType, null, true );
    }

    public ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType, Boolean mayBeNull )
    {
        return this.createColumnDefinition( columnName, columnDataType, null, mayBeNull );
    }

    public ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType, String columnDefault )
    {
        return this.createColumnDefinition( columnName, columnDataType, columnDefault, true );
    }

    public ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType,
        String columnDefault, Boolean mayBeNull )
    {
        return this.createColumnDefinition( columnName, columnDataType, columnDefault, mayBeNull, null );
    }

    public ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType, Boolean mayBeNull,
        AutoGenerationPolicy autoGenerationPolicy )
    {
        return this.createColumnDefinition( columnName, columnDataType, null, mayBeNull, autoGenerationPolicy );
    }

    public TableConstraintDefinition createTableConstraintDefinition( String name, TableConstraint constraint )
    {
        return this.createTableConstraintDefinition( name, constraint, null );
    }

    public TableConstraintDefinition createTableConstraintDefinition( TableConstraint constraint )
    {
        return this.createTableConstraintDefinition( null, constraint, null );
    }

    public TableConstraintDefinition createTableConstraintDefinition( TableConstraint constraint,
        ConstraintCharacteristics characteristics )
    {
        return this.createTableConstraintDefinition( null, constraint, characteristics );
    }

    protected abstract ColumnDefinition createColumnDefinition( String columnName, SQLDataType columnDataType,
        String columnDefault, Boolean mayBeNull, AutoGenerationPolicy autoGenerationPolicy );

}

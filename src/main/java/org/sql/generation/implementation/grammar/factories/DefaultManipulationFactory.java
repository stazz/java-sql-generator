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
import org.sql.generation.api.grammar.manipulation.*;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.common.SQLFactoryBase;
import org.sql.generation.implementation.grammar.manipulation.*;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class DefaultManipulationFactory extends SQLFactoryBase
        implements ManipulationFactory {

    public DefaultManipulationFactory(final SQLVendor vendor, final SQLProcessorAggregator processor) {
        super(vendor, processor);
    }

    @Override
    public AlterTableStatement createAlterTableStatement(final TableNameDirect tableName, final AlterTableAction action) {
        return new AlterTableStatementImpl(this.getProcessor(), tableName, action);
    }

    @Override
    public AddColumnDefinition createAddColumnDefinition(final ColumnDefinition definition) {
        return new AddColumnDefinitionImpl(this.getProcessor(), definition);
    }

    @Override
    public AddTableConstraintDefinition createAddTableConstraintDefinition(
            final TableConstraintDefinition constraintDefinition) {
        return new AddTableConstraintDefinitionImpl(this.getProcessor(), constraintDefinition);
    }

    @Override
    public AlterColumnDefinition createAlterColumnDefinition(final String columnName, final AlterColumnAction action) {
        return new AlterColumnDefinitionImpl(this.getProcessor(), columnName, action);
    }

    @Override
    public SetColumnDefault createSetColumnDefault(final String newDefault) {
        return new SetColumnDefaultImpl(this.getProcessor(), newDefault);
    }

    @Override
    public DropColumnDefinition createDropColumnDefinition(final String columnName, final DropBehaviour dropBehaviour) {
        return new DropColumnDefinitionImpl(this.getProcessor(), columnName, dropBehaviour);
    }

    @Override
    public DropTableConstraintDefinition createDropTableConstraintDefinition(final String constraintName,
                                                                             final DropBehaviour dropBehaviour) {
        return new DropTableConstraintDefinitionImpl(this.getProcessor(), constraintName, dropBehaviour);
    }

    @Override
    public DropSchemaStatement createDropSchemaStatement(final String schemaName, final DropBehaviour dropBehaviour) {
        return new DropSchemaStatementImpl(this.getProcessor(), dropBehaviour, schemaName);
    }

    @Override
    public DropTableOrViewStatement createDropTableOrViewStatement(final TableNameDirect tableName, final ObjectType theType,
                                                                   final DropBehaviour dropBehaviour) {
        DropTableOrViewStatement result = null;
        if (theType == ObjectType.TABLE || theType == ObjectType.VIEW) {
            result = new DropTableOrViewStatementImpl(this.getProcessor(), theType, dropBehaviour, tableName);
        } else {
            throw new IllegalArgumentException("Object type " + theType
                    + " is not a valid type for DROP TABLE or DROP VIEW statement.");
        }

        return result;
    }

}

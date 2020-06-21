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

import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.builders.definition.*;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.common.datatypes.SQLDataType;
import org.sql.generation.api.grammar.definition.table.*;
import org.sql.generation.api.grammar.definition.view.RegularViewSpecification;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.builders.definition.*;
import org.sql.generation.implementation.grammar.definition.table.CheckConstraintImpl;
import org.sql.generation.implementation.grammar.definition.table.ColumnDefinitionImpl;
import org.sql.generation.implementation.grammar.definition.table.LikeClauseImpl;
import org.sql.generation.implementation.grammar.definition.table.TableConstraintDefinitionImpl;
import org.sql.generation.implementation.grammar.definition.view.RegularViewSpecificationImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class DefaultDefinitionFactory extends AbstractDefinitionFactory {
    public DefaultDefinitionFactory(final SQLVendor vendor, final SQLProcessorAggregator processor) {
        super(vendor, processor);
    }

    @Override
    public SchemaDefinitionBuilder createSchemaDefinitionBuilder() {
        return new SchemaDefinitionBuilderImpl(this.getProcessor());
    }

    @Override
    public TableDefinitionBuilder createTableDefinitionBuilder() {
        return new TableDefinitionBuilderImpl(this.getProcessor());
    }

    @Override
    public TableElementListBuilder createTableElementListBuilder() {
        return new TableElementListBuilderImpl(this.getProcessor());
    }

    @Override
    protected ColumnDefinition createColumnDefinition(final String columnName, final SQLDataType columnDataType,
                                                      final String columnDefault, final Boolean mayBeNull, final AutoGenerationPolicy autoGenerationPolicy) {
        return new ColumnDefinitionImpl(this.getProcessor(), columnName, columnDataType, columnDefault, mayBeNull,
                autoGenerationPolicy);
    }

    @Override
    public LikeClause createLikeClause(final TableNameDirect tableName) {
        return new LikeClauseImpl(this.getProcessor(), tableName);
    }

    @Override
    public TableConstraintDefinition createTableConstraintDefinition(final String name, final TableConstraint constraint,
                                                                     final ConstraintCharacteristics characteristics) {
        return new TableConstraintDefinitionImpl(this.getProcessor(), name, constraint, characteristics);
    }

    @Override
    public CheckConstraint createCheckConstraint(final BooleanExpression check) {
        return new CheckConstraintImpl(this.getProcessor(), check);
    }

    @Override
    public UniqueConstraintBuilder createUniqueConstraintBuilder() {
        return new UniqueConstraintBuilderImpl(this.getProcessor(), this.getVendor().getColumnsFactory());
    }

    @Override
    public ForeignKeyConstraintBuilder createForeignKeyConstraintBuilder() {
        return new ForeignKeyConstraintBuilderImpl(this.getProcessor(), this.getVendor().getColumnsFactory());
    }

    @Override
    public ViewDefinitionBuilder createViewDefinitionBuilder() {
        return new ViewDefinitionBuilderImpl(this.getProcessor());
    }

    @Override
    public RegularViewSpecification createRegularViewSpecification(final String... columnNames) {
        return new RegularViewSpecificationImpl(this.getProcessor(), this.getVendor().getColumnsFactory()
                .colNames(columnNames));
    }

}

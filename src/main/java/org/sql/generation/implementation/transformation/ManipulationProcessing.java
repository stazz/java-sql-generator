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

package org.sql.generation.implementation.transformation;

import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.manipulation.*;
import org.sql.generation.api.grammar.manipulation.AlterColumnAction.DropDefault;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Stanislav Muhametsin
 */
public class ManipulationProcessing {

    public static class AlterTableStatementProcessor extends AbstractProcessor<AlterTableStatement> {
        public AlterTableStatementProcessor() {
            super(AlterTableStatement.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final AlterTableStatement object, final StringBuilder builder) {
            builder.append("ALTER TABLE").append(SQLConstants.TOKEN_SEPARATOR);
            aggregator.process(object.getTableName(), builder);
            builder.append(SQLConstants.NEWLINE);
            aggregator.process(object.getAction(), builder);
        }
    }

    public static class AddColumnDefinitionProcessor extends AbstractProcessor<AddColumnDefinition> {

        public AddColumnDefinitionProcessor() {
            super(AddColumnDefinition.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final AddColumnDefinition object, final StringBuilder builder) {
            builder.append("ADD COLUMN").append(SQLConstants.TOKEN_SEPARATOR);
            aggregator.process(object.getColumnDefinition(), builder);
        }
    }

    public static class AddTableConstraintDefinitionProcessor extends AbstractProcessor<AddTableConstraintDefinition> {

        public AddTableConstraintDefinitionProcessor() {
            super(AddTableConstraintDefinition.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final AddTableConstraintDefinition object,
                                 final StringBuilder builder) {
            builder.append("ADD").append(SQLConstants.TOKEN_SEPARATOR);
            aggregator.process(object.getConstraint(), builder);
        }
    }

    public static class AlterColumnDefinitionProcessor extends AbstractProcessor<AlterColumnDefinition> {
        public AlterColumnDefinitionProcessor() {
            super(AlterColumnDefinition.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final AlterColumnDefinition object, final StringBuilder builder) {
            builder.append("ALTER COLUMN").append(SQLConstants.TOKEN_SEPARATOR).append(object.getColumnName())
                    .append(SQLConstants.TOKEN_SEPARATOR);
            aggregator.process(object.getAction(), builder);
        }
    }

    public static class DropColumnDefaultProcessor extends AbstractProcessor<DropDefault> {
        public DropColumnDefaultProcessor() {
            super(DropDefault.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final DropDefault object, final StringBuilder builder) {
            builder.append("DROP DEFAULT");
        }
    }

    public static class SetColumnDefaultProcessor extends AbstractProcessor<SetColumnDefault> {
        public SetColumnDefaultProcessor() {
            super(SetColumnDefault.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final SetColumnDefault object, final StringBuilder builder) {
            builder.append("SET").append(SQLConstants.TOKEN_SEPARATOR);
            builder.append(object.getDefault());
        }
    }

    public static class DropColumnDefinitionProcessor extends AbstractProcessor<DropColumnDefinition> {
        public DropColumnDefinitionProcessor() {
            super(DropColumnDefinition.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final DropColumnDefinition object, final StringBuilder builder) {
            builder.append("DROP COLUMN").append(SQLConstants.TOKEN_SEPARATOR).append(object.getColumnName());
            ProcessorUtils.processDropBehaviour(object.getDropBehaviour(), builder);
        }
    }

    public static class DropTableConstraintDefinitionProcessor extends AbstractProcessor<DropTableConstraintDefinition> {
        public DropTableConstraintDefinitionProcessor() {
            super(DropTableConstraintDefinition.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final DropTableConstraintDefinition object,
                                 final StringBuilder builder) {
            builder.append("DROP CONSTRAINT").append(SQLConstants.TOKEN_SEPARATOR)
                    .append(object.getConstraintName());
            ProcessorUtils.processDropBehaviour(object.getDropBehaviour(), builder);
        }
    }

    public static class DropSchemaStatementProcessor extends AbstractProcessor<DropSchemaStatement> {
        public DropSchemaStatementProcessor() {
            super(DropSchemaStatement.class);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final DropSchemaStatement object, final StringBuilder builder) {
            builder.append("DROP SCHEMA").append(SQLConstants.TOKEN_SEPARATOR).append(object.getSchemaName());
            ProcessorUtils.processDropBehaviour(object.getDropBehaviour(), builder);
        }
    }

    public static class DropTableOrViewStatementProcessor extends AbstractProcessor<DropTableOrViewStatement> {
        private static final Map<ObjectType, String> _defaultObjectTypes;

        static {
            final Map<ObjectType, String> map = new HashMap<>();
            map.put(ObjectType.TABLE, "TABLE");
            map.put(ObjectType.VIEW, "VIEW");

            _defaultObjectTypes = map;
        }

        private final Map<ObjectType, String> _objectTypes;

        public DropTableOrViewStatementProcessor() {
            this(DropTableOrViewStatementProcessor._defaultObjectTypes);
        }

        public DropTableOrViewStatementProcessor(final Map<ObjectType, String> objectTypes) {
            super(DropTableOrViewStatement.class);

            this._objectTypes = objectTypes;
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator aggregator, final DropTableOrViewStatement object,
                                 final StringBuilder builder) {
            builder.append("DROP").append(SQLConstants.TOKEN_SEPARATOR)
                    .append(this._objectTypes.get(object.whatToDrop())).append(SQLConstants.TOKEN_SEPARATOR);

            aggregator.process(object.getTableName(), builder);

            ProcessorUtils.processDropBehaviour(object.getDropBehaviour(), builder);
        }

        protected Map<ObjectType, String> getObjectTypes() {
            return this._objectTypes;
        }
    }
}

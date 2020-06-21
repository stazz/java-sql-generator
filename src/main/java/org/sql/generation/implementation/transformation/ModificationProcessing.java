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
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.modification.*;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.Iterator;

/**
 * @author Stanislav Muhametsin
 */
public class ModificationProcessing {

    public static abstract class DynamicColumnSourceProcessor<SourceType extends DynamicColumnSource>
            extends
            AbstractProcessor<SourceType> {
        public DynamicColumnSourceProcessor(final Class<? extends SourceType> realType) {
            super(realType);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final SourceType object,
                                 final StringBuilder builder) {
            if (object.getColumnNames() != null) {
                processor.process(object.getColumnNames(), builder);
            }
            this.doProcessColumnSource(processor, object, builder);
        }

        protected abstract void doProcessColumnSource(SQLProcessorAggregator processor,
                                                      SourceType object,
                                                      StringBuilder builder);

    }

    public static class ColumnSourceByQueryProcessor extends
            DynamicColumnSourceProcessor<ColumnSourceByQuery> {

        public ColumnSourceByQueryProcessor() {
            this(ColumnSourceByQuery.class);
        }

        protected ColumnSourceByQueryProcessor(final Class<? extends ColumnSourceByQuery> realType) {
            super(realType);
        }

        @Override
        protected void doProcessColumnSource(final SQLProcessorAggregator processor,
                                             final ColumnSourceByQuery object,
                                             final StringBuilder builder) {
            builder.append(SQLConstants.NEWLINE);
            processor.process(object.getQuery(), builder);
        }
    }

    public static class ColumnSourceByValuesProcessor extends
            DynamicColumnSourceProcessor<ColumnSourceByValues> {

        public ColumnSourceByValuesProcessor() {
            this(ColumnSourceByValues.class);
        }

        public ColumnSourceByValuesProcessor(final Class<? extends ColumnSourceByValues> realType) {
            super(realType);
        }

        @Override
        protected void doProcessColumnSource(final SQLProcessorAggregator processor,
                                             final ColumnSourceByValues object,
                                             final StringBuilder builder) {
            builder.append(SQLConstants.NEWLINE).append("VALUES")
                    .append(SQLConstants.OPEN_PARENTHESIS);
            final Iterator<ValueExpression> iter = object.getValues().iterator();
            while (iter.hasNext()) {
                final ValueExpression next = iter.next();
                final boolean needParenthesis = next instanceof QueryExpression;
                if (needParenthesis) {
                    builder.append(SQLConstants.OPEN_PARENTHESIS);
                }
                processor.process(next, builder);
                if (needParenthesis) {
                    builder.append(SQLConstants.CLOSE_PARENTHESIS);
                }
                if (iter.hasNext()) {
                    builder.append(SQLConstants.COMMA).append(SQLConstants.TOKEN_SEPARATOR);
                }
            }
            builder.append(SQLConstants.CLOSE_PARENTHESIS);
        }
    }

    public static class DeleteBySearchProcessor extends AbstractProcessor<DeleteBySearch> {
        public DeleteBySearchProcessor() {
            this(DeleteBySearch.class);
        }

        public DeleteBySearchProcessor(final Class<? extends DeleteBySearch> realType) {
            super(realType);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final DeleteBySearch object,
                                 final StringBuilder builder) {
            builder.append("DELETE FROM").append(SQLConstants.TOKEN_SEPARATOR);
            processor.process(object.getTargetTable(), builder);
            QueryProcessing.processOptionalBooleanExpression(processor, builder,
                    object.getWhere(),
                    SQLConstants.NEWLINE, SQLConstants.WHERE);
        }

    }

    public static class InsertStatementProcessor extends AbstractProcessor<InsertStatement> {
        public InsertStatementProcessor() {
            this(InsertStatement.class);
        }

        public InsertStatementProcessor(final Class<? extends InsertStatement> realType) {
            super(realType);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final InsertStatement object,
                                 final StringBuilder builder) {
            builder.append("INSERT INTO").append(SQLConstants.TOKEN_SEPARATOR);
            processor.process(object.getTableName(), builder);
            builder.append(SQLConstants.TOKEN_SEPARATOR);
            processor.process(object.getColumnSource(), builder);
        }
    }

    public static class SetClauseProcessor extends AbstractProcessor<SetClause> {
        public SetClauseProcessor() {
            this(SetClause.class);
        }

        public SetClauseProcessor(final Class<? extends SetClause> realType) {
            super(realType);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final SetClause object,
                                 final StringBuilder builder) {
            builder.append(object.getUpdateTarget()).append(SQLConstants.TOKEN_SEPARATOR)
                    .append("=")
                    .append(SQLConstants.TOKEN_SEPARATOR);
            processor.process(object.getUpdateSource(), builder);
        }
    }

    public static class TargetTableProcessor extends AbstractProcessor<TargetTable> {
        public TargetTableProcessor() {
            this(TargetTable.class);
        }

        protected TargetTableProcessor(final Class<? extends TargetTable> realType) {
            super(realType);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final TargetTable object,
                                 final StringBuilder builder) {
            final Boolean isOnly = object.isOnly();
            if (isOnly) {
                builder.append("ONLY").append(SQLConstants.OPEN_PARENTHESIS);
            }
            processor.process(object.getTableName(), builder);
            if (isOnly) {
                builder.append(SQLConstants.CLOSE_PARENTHESIS);
            }
        }
    }

    public static class UpdateBySearchProcessor extends AbstractProcessor<UpdateBySearch> {
        public UpdateBySearchProcessor() {
            this(UpdateBySearch.class);
        }

        protected UpdateBySearchProcessor(final Class<? extends UpdateBySearch> realType) {
            super(realType);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final UpdateBySearch object,
                                 final StringBuilder builder) {
            builder.append("UPDATE").append(SQLConstants.TOKEN_SEPARATOR);
            processor.process(object.getTargetTable(), builder);
            builder.append(SQLConstants.NEWLINE).append("SET")
                    .append(SQLConstants.TOKEN_SEPARATOR);
            final Iterator<SetClause> iter = object.getSetClauses().iterator();
            while (iter.hasNext()) {
                processor.process(iter.next(), builder);
                if (iter.hasNext()) {
                    builder.append(SQLConstants.COMMA).append(SQLConstants.TOKEN_SEPARATOR);
                }
            }
            QueryProcessing.processOptionalBooleanExpression(processor, builder,
                    object.getWhere(),
                    SQLConstants.NEWLINE, SQLConstants.WHERE);
        }
    }

    public static class UpdateSourceByExpressionProcessor extends
            AbstractProcessor<UpdateSourceByExpression> {
        public UpdateSourceByExpressionProcessor() {
            this(UpdateSourceByExpression.class);
        }

        public UpdateSourceByExpressionProcessor(final Class<? extends UpdateSourceByExpression> realType) {
            super(realType);
        }

        @Override
        protected void doProcess(final SQLProcessorAggregator processor,
                                 final UpdateSourceByExpression object,
                                 final StringBuilder builder) {
            processor.process(object.getValueExpression(), builder);
        }
    }

}

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

package org.sql.generation.implementation.grammar.builders.definition;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.definition.ForeignKeyConstraintBuilder;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.definition.table.ForeignKeyConstraint;
import org.sql.generation.api.grammar.definition.table.MatchType;
import org.sql.generation.api.grammar.definition.table.ReferentialAction;
import org.sql.generation.api.grammar.factories.ColumnsFactory;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.definition.table.ForeignKeyConstraintImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class ForeignKeyConstraintBuilderImpl extends SQLBuilderBase
        implements ForeignKeyConstraintBuilder {

    private final List<String> _sourceColumns;
    private final List<String> _targetColumns;
    private TableNameDirect _targetTable;
    private MatchType _matchType;
    private ReferentialAction _onUpdate;
    private ReferentialAction _onDelete;

    private final ColumnsFactory _c;

    public ForeignKeyConstraintBuilderImpl(final SQLProcessorAggregator processor, final ColumnsFactory c) {
        super(processor);
        NullArgumentException.validateNotNull("Columns factory", c);

        this._c = c;

        this._sourceColumns = new ArrayList<>();
        this._targetColumns = new ArrayList<>();
    }

    @Override
    public ForeignKeyConstraint createExpression() {
        return new ForeignKeyConstraintImpl(this.getProcessor(), this._c.colNames(this._sourceColumns),
                this._targetTable, this._targetColumns.size() == 0 ? null : this._c.colNames(this._targetColumns),
                this._matchType, this._onDelete, this._onUpdate);
    }

    @Override
    public ForeignKeyConstraintBuilder addSourceColumns(final String... columnNames) {
        for (final String name : columnNames) {
            this._sourceColumns.add(name);
        }
        return this;
    }

    @Override
    public ForeignKeyConstraintBuilder addTargetColumns(final String... columnNames) {
        for (final String name : columnNames) {
            this._targetColumns.add(name);
        }
        return this;
    }

    @Override
    public ForeignKeyConstraintBuilder setTargetTableName(final TableNameDirect tableName) {
        this._targetTable = tableName;
        return this;
    }

    @Override
    public ForeignKeyConstraintBuilder setMatchType(final MatchType matchType) {
        this._matchType = matchType;
        return this;
    }

    @Override
    public ForeignKeyConstraintBuilder setOnUpdate(final ReferentialAction action) {
        this._onUpdate = action;
        return this;
    }

    @Override
    public ForeignKeyConstraintBuilder setOnDelete(final ReferentialAction action) {
        this._onDelete = action;
        return this;
    }

    @Override
    public List<String> getSourceColumns() {
        return this._sourceColumns;
    }

    @Override
    public List<String> getTargetColumns() {
        return this._targetColumns;
    }

    @Override
    public TableNameDirect getTableName() {
        return this._targetTable;
    }

    @Override
    public MatchType getMatchType() {
        return this._matchType;
    }

    @Override
    public ReferentialAction getOnUpdate() {
        return this._onUpdate;
    }

    @Override
    public ReferentialAction getOnDelete() {
        return this._onDelete;
    }

}

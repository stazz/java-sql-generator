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

package org.sql.generation.implementation.grammar.builders.query;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.query.ColumnReference;
import org.sql.generation.api.grammar.query.ColumnReferences.ColumnReferenceInfo;
import org.sql.generation.api.grammar.query.SelectColumnClause;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.query.AsteriskSelectImpl;
import org.sql.generation.implementation.grammar.query.ColumnReferencesImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class ColumnsBuilderImpl extends SQLBuilderBase
        implements ColumnsBuilder {
    private final List<ColumnReferenceInfo> _columns;
    private SetQuantifier _quantifier;

    public ColumnsBuilderImpl(final SQLProcessorAggregator processor, final SetQuantifier setQuantifier) {
        super(processor);
        NullArgumentException.validateNotNull("set quantifier", setQuantifier);

        this._quantifier = setQuantifier;
        this._columns = new ArrayList<>();
    }

    @Override
    public ColumnsBuilder addUnnamedColumns(final ColumnReference... columns) {
        for (final ColumnReference col : columns) {
            this.addNamedColumns(new ColumnReferenceInfo(null, col));
        }

        return this;
    }

    @Override
    public ColumnsBuilder addNamedColumns(final ColumnReferenceInfo... namedColumns) {
        for (final ColumnReferenceInfo info : namedColumns) {
            NullArgumentException.validateNotNull("named column", info);
            this._columns.add(info);
        }

        return this;
    }

    @Override
    public ColumnsBuilder setSetQuantifier(final SetQuantifier newSetQuantifier) {
        NullArgumentException.validateNotNull("new set quantifier", newSetQuantifier);
        this._quantifier = newSetQuantifier;

        return this;
    }

    @Override
    public ColumnsBuilder selectAll() {
        this._columns.clear();
        return this;
    }

    @Override
    public List<ColumnReferenceInfo> getColumns() {
        return Collections.unmodifiableList(this._columns);
    }

    @Override
    public SetQuantifier getSetQuantifier() {
        return this._quantifier;
    }

    @Override
    public SelectColumnClause createExpression() {
        SelectColumnClause result = null;
        if (this._columns.isEmpty()) {
            result = new AsteriskSelectImpl(this.getProcessor(), this._quantifier);
        } else {
            result = new ColumnReferencesImpl(this.getProcessor(), this._quantifier, this._columns);
        }

        return result;
    }
}

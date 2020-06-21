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
import org.sql.generation.api.grammar.builders.definition.UniqueConstraintBuilder;
import org.sql.generation.api.grammar.definition.table.UniqueConstraint;
import org.sql.generation.api.grammar.definition.table.UniqueSpecification;
import org.sql.generation.api.grammar.factories.ColumnsFactory;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.definition.table.UniqueConstraintImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class UniqueConstraintBuilderImpl extends SQLBuilderBase
        implements UniqueConstraintBuilder {

    private UniqueSpecification _uniqueness;
    private final List<String> _columns;

    private final ColumnsFactory _c;

    public UniqueConstraintBuilderImpl(final SQLProcessorAggregator processor, final ColumnsFactory c) {
        super(processor);
        NullArgumentException.validateNotNull("Columns factory", c);

        this._c = c;
        this._columns = new ArrayList<>();
    }

    @Override
    public UniqueConstraint createExpression() {
        return new UniqueConstraintImpl(this.getProcessor(), this._c.colNames(this._columns), this._uniqueness);
    }

    @Override
    public UniqueConstraintBuilder setUniqueness(final UniqueSpecification uniqueness) {
        this._uniqueness = uniqueness;
        return this;
    }

    @Override
    public UniqueConstraintBuilder addColumns(final String... columnNames) {
        for (final String col : columnNames) {
            this._columns.add(col);
        }
        return this;
    }

    @Override
    public UniqueSpecification getUniqueness() {
        return this._uniqueness;
    }

    @Override
    public List<String> getColumns() {
        return this._columns;
    }

}

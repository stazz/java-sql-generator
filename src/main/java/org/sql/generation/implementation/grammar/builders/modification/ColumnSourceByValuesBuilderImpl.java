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

package org.sql.generation.implementation.grammar.builders.modification;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.modification.ColumnSourceByValuesBuilder;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.modification.ColumnSourceByValues;
import org.sql.generation.implementation.grammar.common.ColumnNameListImpl;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.modification.ColumnSourceByValuesImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class ColumnSourceByValuesBuilderImpl extends SQLBuilderBase
        implements ColumnSourceByValuesBuilder {

    private final List<ValueExpression> _values;

    private final List<String> _columnNames;

    public ColumnSourceByValuesBuilderImpl(final SQLProcessorAggregator processor) {
        super(processor);
        this._values = new ArrayList<>();
        this._columnNames = new ArrayList<>();
    }

    @Override
    public ColumnSourceByValues createExpression() {
        ColumnNameList list = null;
        if (!this._columnNames.isEmpty()) {
            list = new ColumnNameListImpl(this.getProcessor(), this._columnNames);
        }
        return new ColumnSourceByValuesImpl(this.getProcessor(), list, this._values);
    }

    @Override
    public ColumnSourceByValuesBuilder addValues(final ValueExpression... values) {
        for (final ValueExpression exp : values) {
            NullArgumentException.validateNotNull("value", exp);
            this._values.add(exp);
        }
        return this;
    }

    @Override
    public List<ValueExpression> getValues() {
        return Collections.unmodifiableList(this._values);
    }

    @Override
    public ColumnSourceByValuesBuilder addColumnNames(final String... columnNames) {
        for (final String str : columnNames) {
            NullArgumentException.validateNotNull("column name", str);
            this._columnNames.add(str);
        }

        return this;
    }

    @Override
    public List<String> getColumnNames() {
        return Collections.unmodifiableList(this._columnNames);
    }

}

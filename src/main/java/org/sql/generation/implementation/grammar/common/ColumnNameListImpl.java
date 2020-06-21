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

package org.sql.generation.implementation.grammar.common;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.*;

/**
 * @author Stanislav Muhametsin
 */
public class ColumnNameListImpl extends SQLSyntaxElementBase<ColumnNameList, ColumnNameList>
        implements ColumnNameList {

    private final List<String> _columnNames;

    public ColumnNameListImpl(final SQLProcessorAggregator processor, final Collection<String> columnNames) {
        this(processor, ColumnNameList.class, columnNames);
    }

    protected ColumnNameListImpl(final SQLProcessorAggregator processor,
                                 final Class<? extends ColumnNameList> implClass,
                                 final Collection<String> columnNames) {
        super(processor, implClass);
        NullArgumentException.validateNotNull("column names", columnNames);

        if (columnNames.isEmpty()) {
            throw new IllegalArgumentException("Column name list must have at least one column.");
        }

        for (final String columnName : columnNames) {
            NullArgumentException.validateNotNull("column name", columnName);
        }

        this._columnNames = Collections.unmodifiableList(new ArrayList<>(columnNames));
    }

    public ColumnNameListImpl(final SQLProcessorAggregator processor, final String... columnNames) {
        this(processor, Arrays.asList(columnNames));
    }

    @Override
    public List<String> getColumnNames() {
        return this._columnNames;
    }

    @Override
    protected boolean doesEqual(final ColumnNameList another) {
        return this._columnNames.equals(another.getColumnNames());
    }

}

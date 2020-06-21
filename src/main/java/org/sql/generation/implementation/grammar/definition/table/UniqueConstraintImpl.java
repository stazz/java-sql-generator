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

package org.sql.generation.implementation.grammar.definition.table;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.definition.table.TableConstraint;
import org.sql.generation.api.grammar.definition.table.UniqueConstraint;
import org.sql.generation.api.grammar.definition.table.UniqueSpecification;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class UniqueConstraintImpl extends SQLSyntaxElementBase<TableConstraint, UniqueConstraint>
        implements UniqueConstraint {

    private final ColumnNameList _columns;
    private final UniqueSpecification _uniqueness;

    public UniqueConstraintImpl(final SQLProcessorAggregator processor, final ColumnNameList columns,
                                final UniqueSpecification uniqueness) {
        this(processor, UniqueConstraint.class, columns, uniqueness);
    }

    protected UniqueConstraintImpl(final SQLProcessorAggregator processor,
                                   final Class<? extends UniqueConstraint> realImplementingType, final ColumnNameList columns, final UniqueSpecification uniqueness) {
        super(processor, realImplementingType);

        NullArgumentException.validateNotNull("Columns", columns);
        NullArgumentException.validateNotNull("Uniqueness", uniqueness);

        this._columns = columns;
        this._uniqueness = uniqueness;
    }

    @Override
    protected boolean doesEqual(final UniqueConstraint another) {
        return this._uniqueness.equals(another.getUniquenessKind())
                && this._columns.equals(another.getColumnNameList());
    }

    @Override
    public ColumnNameList getColumnNameList() {
        return this._columns;
    }

    @Override
    public UniqueSpecification getUniquenessKind() {
        return this._uniqueness;
    }

}

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

package org.sql.generation.implementation.grammar.modification;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.modification.TargetTable;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class TargetTableImpl extends SQLSyntaxElementBase<TargetTable, TargetTable>
        implements TargetTable {

    private final Boolean _isOnly;

    private final TableNameDirect _tableName;

    public TargetTableImpl(final SQLProcessorAggregator processor, final Boolean isOnly, final TableNameDirect tableName) {
        this(processor, TargetTable.class, isOnly, tableName);
    }

    protected TargetTableImpl(final SQLProcessorAggregator processor, final Class<? extends TargetTable> expressionClass,
                              Boolean isOnly, final TableNameDirect tableName) {
        super(processor, expressionClass);

        NullArgumentException.validateNotNull("table name", tableName);
        if (isOnly == null) {
            isOnly = false;
        }

        this._tableName = tableName;
        this._isOnly = isOnly;
    }

    @Override
    public Boolean isOnly() {
        return this._isOnly;
    }

    @Override
    public TableNameDirect getTableName() {
        return this._tableName;
    }

    @Override
    protected boolean doesEqual(final TargetTable another) {
        return this._tableName.equals(another.getTableName()) && this._isOnly.equals(another.isOnly());
    }

}

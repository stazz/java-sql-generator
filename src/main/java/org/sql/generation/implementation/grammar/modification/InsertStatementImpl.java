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
import org.sql.generation.api.grammar.modification.ColumnSource;
import org.sql.generation.api.grammar.modification.InsertStatement;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class InsertStatementImpl extends SQLSyntaxElementBase<InsertStatement, InsertStatement>
        implements InsertStatement {

    private final TableNameDirect _tableName;
    private final ColumnSource _columnSource;

    public InsertStatementImpl(final SQLProcessorAggregator processor, final TableNameDirect tableName, final ColumnSource columnSource) {
        this(processor, InsertStatement.class, tableName, columnSource);
    }

    protected InsertStatementImpl(final SQLProcessorAggregator processor, final Class<? extends InsertStatement> expressionClass,
                                  final TableNameDirect tableName, final ColumnSource columnSource) {
        super(processor, expressionClass);

        NullArgumentException.validateNotNull("tableName", tableName);
        NullArgumentException.validateNotNull("column source", columnSource);

        this._tableName = tableName;
        this._columnSource = columnSource;
    }

    @Override
    public TableNameDirect getTableName() {
        return this._tableName;
    }

    @Override
    public ColumnSource getColumnSource() {
        return this._columnSource;
    }

    @Override
    protected boolean doesEqual(final InsertStatement another) {
        return this._tableName.equals(another.getTableName())
                && this._columnSource.equals(another.getColumnSource());
    }
}

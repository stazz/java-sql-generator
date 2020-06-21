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

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.modification.DeleteBySearch;
import org.sql.generation.api.grammar.modification.DeleteStatement;
import org.sql.generation.api.grammar.modification.TargetTable;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class DeleteBySearchImpl extends SQLSyntaxElementBase<DeleteStatement, DeleteBySearch>
        implements DeleteBySearch {

    private final TargetTable _targetTable;

    private final BooleanExpression _where;

    public DeleteBySearchImpl(final SQLProcessorAggregator processor, final TargetTable targetTable, final BooleanExpression where) {
        this(processor, DeleteBySearch.class, targetTable, where);
    }

    protected DeleteBySearchImpl(final SQLProcessorAggregator processor, final Class<? extends DeleteBySearch> expressionClass,
                                 final TargetTable targetTable, final BooleanExpression where) {
        super(processor, expressionClass);
        NullArgumentException.validateNotNull("target table", targetTable);

        this._targetTable = targetTable;
        this._where = where;
    }

    @Override
    public TargetTable getTargetTable() {
        return this._targetTable;
    }

    @Override
    public BooleanExpression getWhere() {
        return this._where;
    }

    @Override
    protected boolean doesEqual(final DeleteBySearch another) {
        return this._targetTable.equals(another.getTargetTable())
                && TypeableImpl.bothNullOrEquals(this._where, another.getWhere());
    }
}

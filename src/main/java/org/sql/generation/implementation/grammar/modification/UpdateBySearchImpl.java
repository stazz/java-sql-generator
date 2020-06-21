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
import org.sql.generation.api.grammar.modification.SetClause;
import org.sql.generation.api.grammar.modification.TargetTable;
import org.sql.generation.api.grammar.modification.UpdateBySearch;
import org.sql.generation.api.grammar.modification.UpdateStatement;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class UpdateBySearchImpl extends SQLSyntaxElementBase<UpdateStatement, UpdateBySearch>
        implements UpdateBySearch {

    private final TargetTable _targetTable;

    private final List<SetClause> _setClauses;

    private final BooleanExpression _where;

    public UpdateBySearchImpl(final SQLProcessorAggregator processor, final TargetTable targetTable,
                              final List<SetClause> setClauses,
                              final BooleanExpression where) {
        this(processor, UpdateBySearch.class, targetTable, setClauses, where);
    }

    protected UpdateBySearchImpl(final SQLProcessorAggregator processor,
                                 final Class<? extends UpdateBySearch> expressionClass,
                                 final TargetTable targetTable, final List<SetClause> setClauses, final BooleanExpression where) {
        super(processor, expressionClass);

        NullArgumentException.validateNotNull("target table", targetTable);
        NullArgumentException.validateNotNull("set clauses", setClauses);
        if (setClauses.isEmpty()) {
            throw new IllegalArgumentException("At least one set clause must be present.");
        }
        for (final SetClause clause : setClauses) {
            NullArgumentException.validateNotNull("set clause", clause);
        }

        this._targetTable = targetTable;
        this._setClauses = Collections.unmodifiableList(new ArrayList<>(setClauses));
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
    public List<SetClause> getSetClauses() {
        return this._setClauses;
    }

    @Override
    protected boolean doesEqual(final UpdateBySearch another) {
        return this._targetTable.equals(another.getTargetTable())
                && this._setClauses.equals(another.getSetClauses())
                && TypeableImpl.bothNullOrEquals(this._where, another.getWhere());
    }

}

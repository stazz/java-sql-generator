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
import org.sql.generation.api.grammar.builders.booleans.BooleanBuilder;
import org.sql.generation.api.grammar.builders.modification.UpdateBySearchBuilder;
import org.sql.generation.api.grammar.modification.SetClause;
import org.sql.generation.api.grammar.modification.TargetTable;
import org.sql.generation.api.grammar.modification.UpdateBySearch;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.modification.UpdateBySearchImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class UpdateBySearchBuilderImpl extends SQLBuilderBase
        implements UpdateBySearchBuilder {

    private TargetTable _targetTable;

    private final List<SetClause> _setClauses;

    private final BooleanBuilder _whereBuilder;

    public UpdateBySearchBuilderImpl(final SQLProcessorAggregator processor, final BooleanBuilder whereBuilder) {
        super(processor);
        NullArgumentException.validateNotNull("where builder", whereBuilder);

        this._setClauses = new ArrayList<>();
        this._whereBuilder = whereBuilder;
    }

    @Override
    public UpdateBySearch createExpression() {
        return new UpdateBySearchImpl(this.getProcessor(), this._targetTable, this._setClauses,
                this._whereBuilder.createExpression());
    }

    @Override
    public UpdateBySearchBuilder setTargetTable(final TargetTable table) {
        this._targetTable = table;
        return this;
    }

    @Override
    public BooleanBuilder getWhereBuilder() {
        return this._whereBuilder;
    }

    @Override
    public UpdateBySearchBuilder addSetClauses(final SetClause... clauses) {
        for (final SetClause clause : clauses) {
            this._setClauses.add(clause);
        }
        return this;
    }

    @Override
    public List<SetClause> getSetClauses() {
        return Collections.unmodifiableList(this._setClauses);
    }

}

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

package org.sql.generation.implementation.grammar.query.joins;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.query.joins.JoinCondition;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class JoinConditionImpl extends JoinSpecificationImpl<JoinCondition>
        implements JoinCondition {

    private final BooleanExpression _searchCondition;

    public JoinConditionImpl(final SQLProcessorAggregator processor, final BooleanExpression searchCondition) {
        this(processor, JoinCondition.class, searchCondition);
    }

    protected JoinConditionImpl(final SQLProcessorAggregator processor, final Class<? extends JoinCondition> implClass,
                                final BooleanExpression searchCondition) {
        super(processor, implClass);
        NullArgumentException.validateNotNull("search condition", searchCondition);

        this._searchCondition = searchCondition;
    }

    @Override
    public BooleanExpression getSearchConidition() {
        return this._searchCondition;
    }

    @Override
    protected boolean doesEqual(final JoinCondition another) {
        return this._searchCondition.equals(another.getSearchConidition());
    }
}

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
import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.api.grammar.query.joins.JoinSpecification;
import org.sql.generation.api.grammar.query.joins.JoinType;
import org.sql.generation.api.grammar.query.joins.QualifiedJoinedTable;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class QualifiedJoinedTableImpl extends JoinedTableImpl<QualifiedJoinedTable>
        implements QualifiedJoinedTable {

    private final JoinType _joinType;

    private final JoinSpecification _joinSpec;

    public QualifiedJoinedTableImpl(final SQLProcessorAggregator processor, final TableReference left, final TableReference right,
                                    final JoinType joinType, final JoinSpecification joinSpec) {
        this(processor, QualifiedJoinedTable.class, left, right, joinType, joinSpec);
    }

    protected QualifiedJoinedTableImpl(final SQLProcessorAggregator processor,
                                       final Class<? extends QualifiedJoinedTable> implClass, final TableReference left, final TableReference right, final JoinType joinType,
                                       final JoinSpecification joinSpec) {
        super(processor, implClass, left, right);

        NullArgumentException.validateNotNull("join type", joinType);
        NullArgumentException.validateNotNull("join specification", joinSpec);

        this._joinType = joinType;
        this._joinSpec = joinSpec;
    }

    @Override
    public JoinType getJoinType() {
        return this._joinType;
    }

    @Override
    public JoinSpecification getJoinSpecification() {
        return this._joinSpec;
    }

    @Override
    protected boolean doesEqual(final QualifiedJoinedTable another) {
        boolean result = this._joinType.equals(another.getJoinType())
                && this._joinSpec.equals(another.getJoinSpecification()) && this.getRight().equals(another.getRight());
        if (result) {
            result = super.doesEqual(another);
        }
        return result;
    }

}

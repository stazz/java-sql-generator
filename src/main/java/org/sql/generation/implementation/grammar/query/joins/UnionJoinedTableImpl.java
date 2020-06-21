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

import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.api.grammar.query.joins.UnionJoinedTable;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class UnionJoinedTableImpl extends JoinedTableImpl<UnionJoinedTable>
        implements UnionJoinedTable {

    public UnionJoinedTableImpl(final SQLProcessorAggregator processor, final TableReference left, final TableReference right) {
        this(processor, UnionJoinedTable.class, left, right);
    }

    protected UnionJoinedTableImpl(final SQLProcessorAggregator processor, final Class<? extends UnionJoinedTable> implClass,
                                   final TableReference left, final TableReference right) {
        super(processor, implClass, left, right);
    }

    @Override
    protected boolean doesEqual(final UnionJoinedTable another) {
        boolean result = this.getRight().equals(another.getRight());
        if (result) {
            result = super.doesEqual(another);
        }

        return result;
    }
}

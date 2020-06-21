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

package org.sql.generation.implementation.grammar.query;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.query.OrderByClause;
import org.sql.generation.api.grammar.query.SortSpecification;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class OrderByClauseImpl extends SQLSyntaxElementBase<OrderByClause, OrderByClause>
        implements OrderByClause {

    private final List<SortSpecification> _sortSpecs;

    public OrderByClauseImpl(final SQLProcessorAggregator processor, final List<SortSpecification> sortSpecs) {
        super(processor, OrderByClause.class);

        NullArgumentException.validateNotNull("sort specifications", sortSpecs);
        for (final SortSpecification sortSpec : sortSpecs) {
            NullArgumentException.validateNotNull("sort specification", sortSpec);
        }

        this._sortSpecs = Collections.unmodifiableList(new ArrayList<>(sortSpecs));
    }

    @Override
    public List<SortSpecification> getOrderingColumns() {
        return this._sortSpecs;
    }

    @Override
    protected boolean doesEqual(final OrderByClause another) {
        return this._sortSpecs.equals(another.getOrderingColumns());
    }

}

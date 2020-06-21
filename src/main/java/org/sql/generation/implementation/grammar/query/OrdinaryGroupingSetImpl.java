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
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.query.GroupingElement;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class OrdinaryGroupingSetImpl extends SQLSyntaxElementBase<GroupingElement, OrdinaryGroupingSet>
        implements OrdinaryGroupingSet {

    private final List<NonBooleanExpression> _columns;

    public OrdinaryGroupingSetImpl(final SQLProcessorAggregator processor, final NonBooleanExpression... columns) {
        this(processor, Arrays.asList(columns));
    }

    public OrdinaryGroupingSetImpl(final SQLProcessorAggregator processor, final List<NonBooleanExpression> columns) {
        this(processor, OrdinaryGroupingSet.class, columns);
    }

    protected OrdinaryGroupingSetImpl(final SQLProcessorAggregator processor,
                                      final Class<? extends OrdinaryGroupingSet> implClass, final List<NonBooleanExpression> columns) {
        super(processor, implClass);

        NullArgumentException.validateNotNull("columns", columns);
        for (final NonBooleanExpression expr : columns) {
            NullArgumentException.validateNotNull("column", expr);
        }

        this._columns = Collections.unmodifiableList(new ArrayList<>(columns));
    }

    @Override
    public List<NonBooleanExpression> getColumns() {
        return this._columns;
    }

    @Override
    protected boolean doesEqual(final OrdinaryGroupingSet another) {
        return this._columns.equals(another.getColumns());
    }
}

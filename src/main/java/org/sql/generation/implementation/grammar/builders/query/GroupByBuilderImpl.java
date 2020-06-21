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

package org.sql.generation.implementation.grammar.builders.query;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.query.GroupByBuilder;
import org.sql.generation.api.grammar.query.GroupByClause;
import org.sql.generation.api.grammar.query.GroupingElement;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.query.GroupByClauseImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class GroupByBuilderImpl extends SQLBuilderBase
        implements GroupByBuilder {

    private final List<GroupingElement> _elements;

    public GroupByBuilderImpl(final SQLProcessorAggregator processor) {
        super(processor);
        this._elements = new ArrayList<>();
    }

    @Override
    public GroupByBuilder addGroupingElements(final GroupingElement... elements) {
        NullArgumentException.validateNotNull("elements", elements);
        for (final GroupingElement element : elements) {
            NullArgumentException.validateNotNull("element", element);
        }

        this._elements.addAll(Arrays.asList(elements));
        return this;
    }

    @Override
    public List<GroupingElement> getGroupingElements() {
        return Collections.unmodifiableList(this._elements);
    }

    @Override
    public GroupByClause createExpression() {
        return new GroupByClauseImpl(this.getProcessor(), this._elements);
    }
}

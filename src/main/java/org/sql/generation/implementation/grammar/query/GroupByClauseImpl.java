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
import org.sql.generation.api.grammar.query.GroupByClause;
import org.sql.generation.api.grammar.query.GroupingElement;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class GroupByClauseImpl extends SQLSyntaxElementBase<GroupByClause, GroupByClause>
        implements GroupByClause {

    private final List<GroupingElement> _groupingElements;

    public GroupByClauseImpl(final SQLProcessorAggregator processor, final List<GroupingElement> groupingElements) {
        super(processor, GroupByClause.class);

        NullArgumentException.validateNotNull("grouping elements", groupingElements);
        for (final GroupingElement element : groupingElements) {
            NullArgumentException.validateNotNull("grouping element", element);
        }

        this._groupingElements = Collections.unmodifiableList(new ArrayList<>(groupingElements));
    }

    @Override
    public List<GroupingElement> getGroupingElements() {
        return this._groupingElements;
    }

    @Override
    protected boolean doesEqual(final GroupByClause another) {
        return this._groupingElements.equals(another.getGroupingElements());
    }

}

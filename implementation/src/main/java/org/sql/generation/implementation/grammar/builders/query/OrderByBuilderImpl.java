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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.query.OrderByBuilder;
import org.sql.generation.api.grammar.query.OrderByClause;
import org.sql.generation.api.grammar.query.SortSpecification;
import org.sql.generation.implementation.grammar.query.OrderByClauseImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class OrderByBuilderImpl
    implements OrderByBuilder
{

    private final List<SortSpecification> _sortSpecs;

    public OrderByBuilderImpl()
    {
        this._sortSpecs = new ArrayList<SortSpecification>();
    }

    public OrderByBuilder addSortSpecs( SortSpecification... specs )
    {
        for( SortSpecification spec : specs )
        {
            NullArgumentException.validateNotNull( "specification", spec );
        }

        this._sortSpecs.addAll( Arrays.asList( specs ) );
        return this;
    }

    public List<SortSpecification> getSortSpecs()
    {
        return Collections.unmodifiableList( this._sortSpecs );
    }

    public OrderByClause createExpression()
    {
        return new OrderByClauseImpl( this._sortSpecs );
    }
}

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.lwdci.spi.context.single.skeletons.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.query.GroupingElement;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class OrdinaryGroupingSetImpl extends TypeableImpl<GroupingElement, OrdinaryGroupingSet>
    implements OrdinaryGroupingSet
{

    private final List<NonBooleanExpression> _columns;

    public OrdinaryGroupingSetImpl( NonBooleanExpression... columns )
    {
        this( Arrays.asList( columns ) );
    }

    public OrdinaryGroupingSetImpl( List<NonBooleanExpression> columns )
    {
        this( OrdinaryGroupingSet.class, columns );
    }

    protected OrdinaryGroupingSetImpl( Class<? extends OrdinaryGroupingSet> implClass,
        List<NonBooleanExpression> columns )
    {
        super( implClass );

        NullArgumentException.validateNotNull( "columns", columns );
        for( NonBooleanExpression expr : columns )
        {
            NullArgumentException.validateNotNull( "column", expr );
        }

        this._columns = Collections.unmodifiableList( new ArrayList<NonBooleanExpression>( columns ) );
    }

    public List<NonBooleanExpression> getColumns()
    {
        return this._columns;
    }
}

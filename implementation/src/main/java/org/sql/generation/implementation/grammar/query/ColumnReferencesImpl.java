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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.query.ColumnReferences;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class ColumnReferencesImpl extends SelectColumnClauseImpl<ColumnReferences>
    implements ColumnReferences
{

    private final List<ColumnReferenceInfo> _columns;

    public ColumnReferencesImpl( SetQuantifier quantifier, ColumnReferenceInfo... columns )
    {
        this( quantifier, Arrays.asList( columns ) );
    }

    public ColumnReferencesImpl( SetQuantifier quantifier, List<ColumnReferenceInfo> columns )
    {
        this( ColumnReferences.class, quantifier, columns );
    }

    public ColumnReferencesImpl( Class<? extends ColumnReferences> type, SetQuantifier quantifier,
        List<ColumnReferenceInfo> columns )
    {
        super( type, quantifier );

        NullArgumentException.validateNotNull( "columns", columns );

        if( columns.isEmpty() )
        {
            throw new IllegalArgumentException( "Must have at least one column in column list." );
        }

        for( ColumnReferenceInfo column : columns )
        {
            NullArgumentException.validateNotNull( "column", column );
        }

        this._columns = Collections.unmodifiableList( columns );
    }

    public List<ColumnReferenceInfo> getColumns()
    {
        return this._columns;
    }

    @Override
    protected boolean doesEqual( ColumnReferences another )
    {
        return super.doesEqual( another ) && this._columns.equals( another.getColumns() );
    }
}

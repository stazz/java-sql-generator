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
import java.util.Collections;
import java.util.List;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.query.ColumnReference;
import org.sql.generation.api.grammar.query.ColumnReferences.ColumnReferenceInfo;
import org.sql.generation.api.grammar.query.SelectColumnClause;
import org.sql.generation.implementation.grammar.query.AsteriskSelectImpl;
import org.sql.generation.implementation.grammar.query.ColumnReferencesImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class ColumnsBuilderImpl
    implements ColumnsBuilder
{
    private final List<ColumnReferenceInfo> _columns;
    private SetQuantifier _quantifier;

    public ColumnsBuilderImpl( SetQuantifier setQuantifier )
    {
        NullArgumentException.validateNotNull( "set quantifier", setQuantifier );

        this._quantifier = setQuantifier;
        this._columns = new ArrayList<ColumnReferenceInfo>();
    }

    public ColumnsBuilder addUnnamedColumns( ColumnReference... columns )
    {
        for( ColumnReference col : columns )
        {
            this.addNamedColumns( new ColumnReferenceInfo( null, col ) );
        }

        return this;
    }

    public ColumnsBuilder addNamedColumns( ColumnReferenceInfo... namedColumns )
    {
        for( ColumnReferenceInfo info : namedColumns )
        {
            NullArgumentException.validateNotNull( "named column", info );
            this._columns.add( info );
        }

        return this;
    }

    public ColumnsBuilder setSetQuantifier( SetQuantifier newSetQuantifier )
    {
        NullArgumentException.validateNotNull( "new set quantifier", newSetQuantifier );
        this._quantifier = newSetQuantifier;

        return this;
    }

    public ColumnsBuilder selectAll()
    {
        this._columns.clear();
        return this;
    }

    public List<ColumnReferenceInfo> getColumns()
    {
        return Collections.unmodifiableList( this._columns );
    }

    public SetQuantifier getSetQuantifier()
    {
        return this._quantifier;
    }

    public SelectColumnClause createExpression()
    {
        SelectColumnClause result = null;
        if( this._columns.isEmpty() )
        {
            result = new AsteriskSelectImpl( this._quantifier );
        }
        else
        {
            result = new ColumnReferencesImpl( this._quantifier, this._columns );
        }

        return result;
    }
}

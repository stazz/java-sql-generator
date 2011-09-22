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
import org.sql.generation.api.grammar.builders.query.FromBuilder;
import org.sql.generation.api.grammar.builders.query.TableReferenceBuilder;
import org.sql.generation.api.grammar.query.FromClause;
import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.query.FromClauseImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class FromBuilderImpl extends SQLBuilderBase
    implements FromBuilder
{

    private final List<TableReferenceBuilder> _tableRefs;

    public FromBuilderImpl( SQLProcessorAggregator processor )
    {
        super( processor );
        this._tableRefs = new ArrayList<TableReferenceBuilder>();
    }

    public FromBuilder addTableReferences( TableReferenceBuilder... tableRefs )
    {
        for( TableReferenceBuilder ref : tableRefs )
        {
            NullArgumentException.validateNotNull( "table reference", ref );
            this._tableRefs.add( ref );
        }

        return this;
    }

    public List<TableReferenceBuilder> getTableReferences()
    {
        return Collections.unmodifiableList( this._tableRefs );
    }

    public FromClause createExpression()
    {
        List<TableReference> refs = new ArrayList<TableReference>( this._tableRefs.size() );
        for( TableReferenceBuilder builder : this._tableRefs )
        {
            refs.add( builder.createExpression() );
        }

        return new FromClauseImpl( this.getProcessor(), refs );
    }
}

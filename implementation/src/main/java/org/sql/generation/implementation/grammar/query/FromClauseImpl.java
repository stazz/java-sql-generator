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
import org.sql.generation.api.grammar.query.FromClause;
import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class FromClauseImpl extends SQLSyntaxElementBase<FromClause, FromClause>
    implements FromClause
{

    private List<TableReference> _tableReferences;

    public FromClauseImpl( SQLProcessorAggregator processor, TableReference... tableReferences )
    {
        this( processor, Arrays.asList( tableReferences ) );
    }

    public FromClauseImpl( SQLProcessorAggregator processor, List<TableReference> tableReferences )
    {
        this( processor, FromClause.class, tableReferences );
    }

    protected FromClauseImpl( SQLProcessorAggregator processor, Class<? extends FromClause> type,
        List<TableReference> tableReferences )
    {
        super( processor, type );

        NullArgumentException.validateNotNull( "table references", tableReferences );

        for( TableReference ref : tableReferences )
        {
            NullArgumentException.validateNotNull( "table reference", ref );
        }

        this._tableReferences = Collections.unmodifiableList( tableReferences );
    }

    public List<TableReference> getTableReferences()
    {
        return this._tableReferences;
    }

    @Override
    protected boolean doesEqual( FromClause another )
    {
        return this._tableReferences.equals( another.getTableReferences() );
    }
}

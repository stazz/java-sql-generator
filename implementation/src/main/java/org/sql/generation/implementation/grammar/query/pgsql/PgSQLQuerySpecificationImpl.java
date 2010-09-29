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

package org.sql.generation.implementation.grammar.query.pgsql;

import org.lwdci.spi.context.single.skeletons.TypeableImpl;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.query.FromClause;
import org.sql.generation.api.grammar.query.GroupByClause;
import org.sql.generation.api.grammar.query.OrderByClause;
import org.sql.generation.api.grammar.query.QuerySpecification;
import org.sql.generation.api.grammar.query.SelectColumnClause;
import org.sql.generation.api.grammar.query.pgsql.LimitClause;
import org.sql.generation.api.grammar.query.pgsql.OffsetClause;
import org.sql.generation.api.grammar.query.pgsql.PgSQLQuerySpecification;
import org.sql.generation.implementation.grammar.query.QuerySpecificationImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class PgSQLQuerySpecificationImpl extends QuerySpecificationImpl
    implements PgSQLQuerySpecification
{

    private final LimitClause _limit;

    private final OffsetClause _offset;

    public PgSQLQuerySpecificationImpl( SelectColumnClause select, FromClause from, BooleanExpression where,
        GroupByClause groupBy, BooleanExpression having, OrderByClause orderBy, LimitClause limit, OffsetClause offset )
    {
        super( PgSQLQuerySpecification.class, select, from, where, groupBy, having, orderBy );

        this._limit = limit;
        this._offset = offset;
    }

    public LimitClause getLimit()
    {
        return this._limit;
    }

    public OffsetClause getOffset()
    {
        return this._offset;
    }

    @Override
    protected boolean doesEqual( QuerySpecification another )
    {
        // We can be certain that here another will be PgSQLQuerySpecification, since Typeable.doesEqual method
        // documentation says that this method is invoked only when implemented type of this object is same as
        // implemented type of another.
        PgSQLQuerySpecification anotherSpec = (PgSQLQuerySpecification) another;
        boolean result = TypeableImpl.bothNullOrEquals( this._limit, anotherSpec.getLimit() )
            && TypeableImpl.bothNullOrEquals( this._offset, anotherSpec.getOffset() );
        if( result )
        {
            result = super.doesEqual( another );
        }

        return result;
    }
}

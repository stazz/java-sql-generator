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

package org.sql.generation.implementation.transformation.processing.pgsql;

import java.util.logging.Logger;

import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.query.QuerySpecification;
import org.sql.generation.api.grammar.query.pgsql.LimitByNumber;
import org.sql.generation.api.grammar.query.pgsql.LimitClause;
import org.sql.generation.api.grammar.query.pgsql.OffsetClause;
import org.sql.generation.api.grammar.query.pgsql.PgSQLQuerySpecification;
import org.sql.generation.implementation.transformation.processing.QueryProcessing.QuerySpecificationProcessor;
import org.sql.generation.implementation.transformation.processing.general.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class QueryProcessing
{

    public static class PgSQLQuerySpecificationProcessor extends QuerySpecificationProcessor
    {

        protected PgSQLQuerySpecificationProcessor()
        {
            super( PgSQLQuerySpecification.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, QuerySpecification query, StringBuilder builder )
        {
            super.doProcess( processor, query, builder );
            PgSQLQuerySpecification pgQuery = (PgSQLQuerySpecification) query;
            this.processLimitClause( pgQuery, builder );
            this.processOffsetClause( pgQuery, builder );
        }

        protected void processLimitClause( PgSQLQuerySpecification query, StringBuilder builder )
        {
            LimitClause limit = query.getLimit();
            if( limit != null )
            {
                builder.append( SQLConstants.NEWLINE ).append( "LIMIT" ).append( SQLConstants.TOKEN_SEPARATOR );
                if( limit == LimitClause.LimitAll.INSTANCE )
                {
                    builder.append( "ALL" );
                }
                else if( limit instanceof LimitByNumber )
                {

                    builder.append( ((LimitByNumber) limit).getLimit() );
                    this.checkOrderBy( query, builder, "LIMIT" );
                }
            }
        }

        protected void processOffsetClause( PgSQLQuerySpecification query, StringBuilder builder )
        {
            OffsetClause offset = query.getOffset();
            if( offset != null )
            {
                builder.append( SQLConstants.NEWLINE ).append( "OFFSET" ).append( SQLConstants.TOKEN_SEPARATOR )
                    .append( offset.getOffset() );
                this.checkOrderBy( query, builder, "OFFSET" );
            }
        }

        protected void checkOrderBy( PgSQLQuerySpecification query, StringBuilder builder, String clauseName )
        {
            if( query.getOrderBy() == null )
            {
                Logger
                    .getLogger( this.getClass().getName() )
                    .warning(
                        "Spotted query with "
                            + clauseName
                            + " clause, but without ORDER BY. See http://www.postgresql.org/docs/9.0/static/queries-limit.html for more information."
                            + "\n" + "Query so far: " + builder.toString() );

            }
        }
    }
}

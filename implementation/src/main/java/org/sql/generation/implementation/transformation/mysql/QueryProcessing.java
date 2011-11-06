/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
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

package org.sql.generation.implementation.transformation.mysql;

import org.atp.api.Typeable;
import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.query.LimitSpecification;
import org.sql.generation.api.grammar.query.OffsetSpecification;
import org.sql.generation.api.vendor.MySQLVendor;
import org.sql.generation.implementation.transformation.QueryProcessing.LimitSpecificationProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.OffsetSpecificationProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.QuerySpecificationProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public class QueryProcessing
{
    private static final String MYSQL_LIMIT_PREFIX = "LIMIT";
    private static final String MYSQL_LIMIT_POSTFIX = null;
    private static final String MYSQL_OFFSET_PREFIX = "OFFSET";
    private static final String MYSQL_OFFSET_POSTFIX = null;

    public static class MySQLQuerySpecificationProcessor extends QuerySpecificationProcessor
    {
        @Override
        protected boolean isOffsetBeforeLimit( SQLProcessorAggregator processor )
        {
            return false;
        }

        @Override
        protected void processLimitAndOffset( SQLProcessorAggregator processor, StringBuilder builder,
            Typeable<?> first, Typeable<?> second )
        {
            MySQLVendor vendor = (MySQLVendor) processor.getVendor();
            if( vendor.legacyLimit() )
            {
                // Just do the processing right away, because of the difference of syntax
                builder.append( SQLConstants.NEWLINE ).append( MYSQL_LIMIT_PREFIX )
                    .append( SQLConstants.TOKEN_SEPARATOR );
                if( second != null )
                {
                    processor.process( ((OffsetSpecification) second).getSkip(), builder );
                    builder.append( SQLConstants.COMMA );
                }
                if( first != null && ((LimitSpecification) first).getCount() != null )
                {
                    processor.process( ((LimitSpecification) first).getCount(), builder );
                }
                else if( second != null )
                {
                    builder.append( Long.MAX_VALUE );
                }
            }
            else
            {
                if( first == null && second != null )
                {
                    first = vendor.getQueryFactory().limit( vendor.getLiteralFactory().n( Long.MAX_VALUE ) );
                }
                super.processLimitAndOffset( processor, builder, first, second );
            }
        }
    }

    public static class MySQLOffsetSpecificationProcessor extends OffsetSpecificationProcessor
    {
        @Override
        protected String getPrefix( SQLProcessorAggregator processor )
        {
            return MYSQL_OFFSET_PREFIX;
        }

        @Override
        protected String getPostfix( SQLProcessorAggregator processor )
        {
            return MYSQL_OFFSET_POSTFIX;
        }
    }

    public static class MySQLLimitSpecificationProcessor extends LimitSpecificationProcessor
    {
        @Override
        protected String getPrefix( SQLProcessorAggregator processor )
        {
            return MYSQL_LIMIT_PREFIX;
        }

        @Override
        protected String getPostfix( SQLProcessorAggregator processor )
        {
            return MYSQL_LIMIT_POSTFIX;
        }
    }
}

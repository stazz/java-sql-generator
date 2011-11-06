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

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

package org.sql.generation.implementation.transformation.pgsql;

import org.sql.generation.api.grammar.literals.DateTimeLiteral;
import org.sql.generation.implementation.transformation.LiteralExpressionProcessing.DateTimeLiteralProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class LiteralExpressionProcessing
{
    public static class PGDateTimeLiteralProcessor extends DateTimeLiteralProcessor
    {
        @Override
        protected void doProcess( SQLProcessorAggregator processor, DateTimeLiteral object, StringBuilder builder )
        {
            builder.append( "timestamp " );
            super.doProcess( processor, object, builder );
        }
    }
}

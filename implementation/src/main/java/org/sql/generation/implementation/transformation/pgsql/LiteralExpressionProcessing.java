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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.literals.DateTimeLiteral;
import org.sql.generation.api.grammar.literals.pgsql.DynamicRow;
import org.sql.generation.implementation.transformation.AbstractProcessor;
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
        private final DateFormat _format;

        public PGDateTimeLiteralProcessor()
        {
            this._format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, DateTimeLiteral object, StringBuilder builder )
        {
            builder.append( "timestamp '" + this._format.format( object.getDate() ) + "'" );
        }
    }

    public static class DynamicRowProcessor extends AbstractProcessor<DynamicRow>
    {
        public DynamicRowProcessor()
        {
            super( DynamicRow.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator aggregator, DynamicRow object, StringBuilder builder )
        {
            builder.append( SQLConstants.OPEN_PARENTHESIS );
            Iterator<ValueExpression> iter = object.getValueExpressions().iterator();
            while( iter.hasNext() )
            {
                aggregator.process( iter.next(), builder );
                if( iter.hasNext() )
                {
                    builder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                }
            }
            builder.append( SQLConstants.CLOSE_PARENTHESIS );
        }

    }
}

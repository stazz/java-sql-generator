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

package org.sql.generation.implementation.transformation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.literals.DirectLiteral;
import org.sql.generation.api.grammar.literals.NumericLiteral;
import org.sql.generation.api.grammar.literals.SQLFunctionLiteral;
import org.sql.generation.api.grammar.literals.StringLiteral;
import org.sql.generation.api.grammar.literals.TimestampTimeLiteral;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * Currently not thread-safe.
 * 
 * @author Stanislav Muhametsin
 */
public class LiteralExpressionProcessing
{

    public static class StringLiteralExpressionProcessor extends AbstractProcessor<StringLiteral>
    {
        public StringLiteralExpressionProcessor()
        {
            super( StringLiteral.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, StringLiteral literal, StringBuilder builder )
        {
            String string = literal.getString();
            builder.append( string == null ? SQLConstants.NULL : "'" + string + "'" );
        }
    }

    public static class DirectLiteralProcessor extends AbstractProcessor<DirectLiteral>
    {
        public DirectLiteralProcessor()
        {
            super( DirectLiteral.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, DirectLiteral literal, StringBuilder builder )
        {
            String string = literal.getDirectLiteral();
            builder.append( string == null ? SQLConstants.NULL : string );
        }
    }

    public static class DateTimeLiteralProcessor extends AbstractProcessor<TimestampTimeLiteral>
    {
        private final DateFormat _format;

        public DateTimeLiteralProcessor()
        {
            super( TimestampTimeLiteral.class );
            this._format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, TimestampTimeLiteral object, StringBuilder builder )
        {
            builder.append( "'" + this._format.format( object.getTimestamp() ) + "'" );
        }
    }

    public static class SQLFunctionLiteralProcessor extends AbstractProcessor<SQLFunctionLiteral>
    {
        public SQLFunctionLiteralProcessor()
        {
            super( SQLFunctionLiteral.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, SQLFunctionLiteral object, StringBuilder builder )
        {
            builder.append( object.getFunctionName() ).append( SQLConstants.OPEN_PARENTHESIS );
            Iterator<ValueExpression> iter = object.getParameters().iterator();
            while( iter.hasNext() )
            {
                processor.process( iter.next(), builder );
                if( iter.hasNext() )
                {
                    builder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                }
            }
            builder.append( SQLConstants.CLOSE_PARENTHESIS );
        }
    }

    public static class NumericLiteralProcessor extends AbstractProcessor<NumericLiteral>
    {

        public NumericLiteralProcessor()
        {
            super( NumericLiteral.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, NumericLiteral object, StringBuilder builder )
        {
            Number numba = object.getNumber();
            builder.append( numba == null ? SQLConstants.NULL : numba.toString() );
        }
    }

}

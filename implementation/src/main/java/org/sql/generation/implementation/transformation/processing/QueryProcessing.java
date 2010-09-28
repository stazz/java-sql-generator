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

package org.sql.generation.implementation.transformation.processing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.query.ColumnReferences;
import org.sql.generation.api.grammar.query.ColumnReferences.ColumnReferenceInfo;
import org.sql.generation.api.grammar.query.CorrespondingSpec;
import org.sql.generation.api.grammar.query.FromClause;
import org.sql.generation.api.grammar.query.GroupByClause;
import org.sql.generation.api.grammar.query.GroupingElement;
import org.sql.generation.api.grammar.query.GroupingElement.GrandTotal;
import org.sql.generation.api.grammar.query.OrderByClause;
import org.sql.generation.api.grammar.query.Ordering;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.QueryExpressionBody;
import org.sql.generation.api.grammar.query.QueryExpressionBodyBinary;
import org.sql.generation.api.grammar.query.QuerySpecification;
import org.sql.generation.api.grammar.query.SelectColumnClause;
import org.sql.generation.api.grammar.query.SetOperation;
import org.sql.generation.api.grammar.query.SortSpecification;
import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.implementation.grammar.booleans.BooleanUtils;
import org.sql.generation.implementation.transformation.processing.general.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class QueryProcessing
{

    public static void processWhere( SQLProcessorAggregator processor, BooleanExpression where, StringBuilder builder,
        String prefix )
    {
        if( where != null && !BooleanUtils.isEmpty( where ) )
        {
            builder.append( prefix ).append( SQLConstants.WHERE ).append( SQLConstants.TOKEN_SEPARATOR );
            processor.process( where, builder );
        }
    }

    public static class QueryExpressionBinaryProcessor extends
        AbstractProcessor<QueryExpressionBody, QueryExpressionBodyBinary>
    {
        private static final Map<SetOperation, String> _defaultSetOperations;

        static
        {
            Map<SetOperation, String> operations = new HashMap<SetOperation, String>();
            operations.put( SetOperation.EXCEPT, "EXCEPT" );
            operations.put( SetOperation.INTERSECT, "INTERSECT" );
            operations.put( SetOperation.UNION, "UNION" );
            _defaultSetOperations = operations;
        }

        private final Map<SetOperation, String> _setOperations;

        public QueryExpressionBinaryProcessor()
        {
            this( _defaultSetOperations );
        }

        public QueryExpressionBinaryProcessor( Map<SetOperation, String> setOperations )
        {
            super( QueryExpressionBodyBinary.class );
            NullArgumentException.validateNotNull( "set operations", setOperations );

            this._setOperations = setOperations;
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, QueryExpressionBodyBinary body,
            StringBuilder builder )
        {
            Boolean leftIsNotEmpty = body.getLeft() != QueryExpressionBody.EmptyQueryExpressionBody.INSTANCE;
            if( leftIsNotEmpty )
            {
                builder.append( SQLConstants.OPEN_PARENTHESIS );
                processor.process( body.getLeft(), builder );
                builder.append( SQLConstants.CLOSE_PARENTHESIS ).append( SQLConstants.NEWLINE );
                this.processSetOperation( body.getSetOperation(), builder );

                builder.append( SQLConstants.TOKEN_SEPARATOR );
                ProcessorUtils.processSetQuantifier( body.getSetQuantifier(), builder );

                CorrespondingSpec correspondingCols = body.getCorrespondingColumns();
                if( correspondingCols != null )
                {
                    builder.append( SQLConstants.TOKEN_SEPARATOR );
                    processor.process( correspondingCols, builder );
                }

                builder.append( SQLConstants.NEWLINE ).append( SQLConstants.OPEN_PARENTHESIS );
            }
            processor.process( body.getRight(), builder );
            if( leftIsNotEmpty )
            {
                builder.append( SQLConstants.CLOSE_PARENTHESIS );
            }
        }

        protected void processSetOperation( SetOperation operation, StringBuilder builder )
        {
            builder.append( this._setOperations.get( operation ) );
        }
    }

    public static class QuerySpecificationProcessor extends AbstractProcessor<QuerySpecification, QuerySpecification>
    {
        public QuerySpecificationProcessor()
        {
            this( QuerySpecification.class );
        }

        public QuerySpecificationProcessor( Class<? extends QuerySpecification> queryClass )
        {
            super( queryClass );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, QuerySpecification query, StringBuilder builder )
        {
            builder.append( SQLConstants.SELECT ).append( SQLConstants.TOKEN_SEPARATOR );
            this.processSelectColumns( processor, query.getColumns(), builder );

            StringBuilder tmp = new StringBuilder();
            this.processFromClause( processor, query.getFrom(), tmp );
            this.appendIfOK( tmp, builder );

            tmp = new StringBuilder();
            QueryProcessing.processWhere( processor, query.getWhere(), builder, SQLConstants.NEWLINE );

            tmp = new StringBuilder();
            this.processGroupByClause( processor, query.getGroupBy(), tmp );
            this.appendIfOK( tmp, builder );

            tmp = new StringBuilder();
            this.processHavingClause( processor, query.getHaving(), tmp );
            this.appendIfOK( tmp, builder );

            tmp = new StringBuilder();
            this.processOrderByClause( processor, query.getOrderBy(), tmp );
            this.appendIfOK( tmp, builder );
        }

        protected void appendIfOK( StringBuilder tmp, StringBuilder builder )
        {
            String str = tmp.toString();
            if( ProcessorUtils.notNullAndNotEmpty( str ) )
            {
                builder.append( SQLConstants.NEWLINE ).append( str );
            }
        }

        protected void processSelectColumns( SQLProcessorAggregator processor, SelectColumnClause select,
            StringBuilder builder )
        {
            ProcessorUtils.processSetQuantifier( select.getSetQuantifier(), builder );
            builder.append( SQLConstants.TOKEN_SEPARATOR );

            if( select instanceof ColumnReferences )
            {
                Iterator<ColumnReferenceInfo> iter = ((ColumnReferences) select).getColumns().iterator();
                while( iter.hasNext() )
                {
                    ColumnReferenceInfo info = iter.next();
                    processor.process( info.getReference(), builder );
                    String alias = info.getAlias();
                    if( ProcessorUtils.notNullAndNotEmpty( alias ) )
                    {
                        builder.append( SQLConstants.TOKEN_SEPARATOR ).append( SQLConstants.ALIAS_DEFINER )
                            .append( SQLConstants.TOKEN_SEPARATOR ).append( alias );
                    }

                    if( iter.hasNext() )
                    {
                        builder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                    }
                }
            }
            else
            {
                builder.append( SQLConstants.ASTERISK );
            }
        }

        protected void processFromClause( SQLProcessorAggregator processor, FromClause from, StringBuilder builder )
        {
            if( from != null && !from.getTableReferences().isEmpty() )
            {
                builder.append( SQLConstants.FROM ).append( SQLConstants.TOKEN_SEPARATOR );
                Iterator<TableReference> iter = from.getTableReferences().iterator();
                while( iter.hasNext() )
                {
                    processor.process( iter.next().asTypeable(), builder );
                    if( iter.hasNext() )
                    {
                        builder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                    }
                }
            }
        }

        protected void processWhereClause( SQLProcessorAggregator processor, BooleanExpression where,
            StringBuilder builder )
        {
            QueryProcessing.processWhere( processor, where, builder, "" );
        }

        protected void processGroupByClause( SQLProcessorAggregator processor, GroupByClause groupBy,
            StringBuilder builder )
        {
            if( groupBy != null )
            {
                StringBuilder groupByBuilder = new StringBuilder();
                Iterator<GroupingElement> iter = groupBy.getGroupingElements().iterator();
                while( iter.hasNext() )
                {
                    processor.process( iter.next(), groupByBuilder );
                    if( iter.hasNext() )
                    {
                        groupByBuilder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                    }
                }
                String groupByString = groupByBuilder.toString();
                if( ProcessorUtils.notNullAndNotEmpty( groupByString ) )
                {
                    builder.append( SQLConstants.GROUP_BY ).append( SQLConstants.TOKEN_SEPARATOR )
                        .append( groupByString );
                }
            }
        }

        protected void processHavingClause( SQLProcessorAggregator processor, BooleanExpression having,
            StringBuilder builder )
        {
            if( having != null )
            {
                StringBuilder havingBuilder = new StringBuilder();
                processor.process( having, havingBuilder );
                String havingString = havingBuilder.toString();
                if( ProcessorUtils.notNullAndNotEmpty( havingString ) )
                {
                    builder.append( SQLConstants.HAVING ).append( SQLConstants.TOKEN_SEPARATOR ).append( havingString );
                }
            }
        }

        protected void processOrderByClause( SQLProcessorAggregator processor, OrderByClause orderBy,
            StringBuilder builder )
        {
            if( orderBy != null )
            {
                StringBuilder orderByBuilder = new StringBuilder();
                Iterator<SortSpecification> iter = orderBy.getOrderingColumns().iterator();
                while( iter.hasNext() )
                {
                    processor.process( iter.next(), orderByBuilder );
                    if( iter.hasNext() )
                    {
                        orderByBuilder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                    }
                }
                String orderByString = orderByBuilder.toString();
                if( ProcessorUtils.notNullAndNotEmpty( orderByString ) )
                {
                    builder.append( SQLConstants.ORDER_BY ).append( SQLConstants.TOKEN_SEPARATOR )
                        .append( orderByString );
                }
            }
        }

    }

    public static class QueryExpressionProcessor extends AbstractProcessor<QueryExpression, QueryExpression>
    {
        public QueryExpressionProcessor()
        {
            super( QueryExpression.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, QueryExpression object, StringBuilder builder )
        {
            processor.process( object.getQueryExpressionBody(), builder );
        }
    }

    public static class CorrespondingSpecProcessor extends AbstractProcessor<CorrespondingSpec, CorrespondingSpec>
    {
        public CorrespondingSpecProcessor()
        {
            super( CorrespondingSpec.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, CorrespondingSpec object, StringBuilder builder )
        {
            builder.append( "CORRESPONDING" );
            if( object.getColumnList() != null )
            {
                builder.append( SQLConstants.OPEN_PARENTHESIS );
                processor.process( object.getColumnList(), builder );
                builder.append( SQLConstants.CLOSE_PARENTHESIS );
            }
        }
    }

    public static class SortSpecificationProcessor extends AbstractProcessor<SortSpecification, SortSpecification>
    {
        private static final Map<Ordering, String> _defaultOrderingStrings;

        static
        {
            Map<Ordering, String> map = new HashMap<Ordering, String>();
            map.put( Ordering.ASCENDING, "ASC" );
            map.put( Ordering.DESCENDING, "DESC" );
            _defaultOrderingStrings = map;
        }

        private final Map<Ordering, String> _orderingStrings;

        public SortSpecificationProcessor()
        {
            this( _defaultOrderingStrings );
        }

        public SortSpecificationProcessor( Map<Ordering, String> orderingStrings )
        {
            super( SortSpecification.class );
            NullArgumentException.validateNotNull( "ordering strings", orderingStrings );
            this._orderingStrings = orderingStrings;
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, SortSpecification object, StringBuilder builder )
        {
            processor.process( object.getValueExpression(), builder );
            builder.append( SQLConstants.TOKEN_SEPARATOR ).append(
                this._orderingStrings.get( object.getOrderingSpecification() ) );
        }
    }

    public static class GrandTotalProcessor extends AbstractProcessor<GroupingElement, GrandTotal>
    {
        public GrandTotalProcessor()
        {
            super( GrandTotal.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, GrandTotal object, StringBuilder builder )
        {
            builder.append( SQLConstants.OPEN_PARENTHESIS ).append( SQLConstants.CLOSE_PARENTHESIS );
        }
    }

    public static class OrdinaryGroupingSetProcessor extends AbstractProcessor<GroupingElement, OrdinaryGroupingSet>
    {
        public OrdinaryGroupingSetProcessor()
        {
            super( OrdinaryGroupingSet.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, OrdinaryGroupingSet object, StringBuilder builder )
        {
            Iterator<NonBooleanExpression> iter = object.getColumns().iterator();
            while( iter.hasNext() )
            {
                processor.process( iter.next(), builder );
                if( iter.hasNext() )
                {
                    builder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                }
            }
        }
    }
}

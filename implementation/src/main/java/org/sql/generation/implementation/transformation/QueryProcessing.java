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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.atp.api.Typeable;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.query.ColumnReferences;
import org.sql.generation.api.grammar.query.ColumnReferences.ColumnReferenceInfo;
import org.sql.generation.api.grammar.query.CorrespondingSpec;
import org.sql.generation.api.grammar.query.FromClause;
import org.sql.generation.api.grammar.query.GroupByClause;
import org.sql.generation.api.grammar.query.GroupingElement;
import org.sql.generation.api.grammar.query.OrderByClause;
import org.sql.generation.api.grammar.query.Ordering;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.QueryExpressionBody;
import org.sql.generation.api.grammar.query.QueryExpressionBodyBinary;
import org.sql.generation.api.grammar.query.QuerySpecification;
import org.sql.generation.api.grammar.query.RowDefinition;
import org.sql.generation.api.grammar.query.RowSubQuery;
import org.sql.generation.api.grammar.query.RowValueConstructor;
import org.sql.generation.api.grammar.query.SelectColumnClause;
import org.sql.generation.api.grammar.query.SetOperation;
import org.sql.generation.api.grammar.query.SortSpecification;
import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.api.grammar.query.TableValueConstructor;
import org.sql.generation.implementation.grammar.booleans.BooleanUtils;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class QueryProcessing
{

    public static void processOptionalBooleanExpression( SQLProcessorAggregator processor, StringBuilder builder,
        BooleanExpression expression, String prefix, String name )
    {
        if( expression != null && !BooleanUtils.isEmpty( expression ) )
        {
            processOptional( processor, builder, expression, prefix, name );
        }
    }

    public static void processOptional( SQLProcessorAggregator processor, StringBuilder builder, Typeable<?> element,
        String prefix, String name )
    {
        if( element != null )
        {
            builder.append( prefix ).append( name ).append( SQLConstants.TOKEN_SEPARATOR );
            processor.process( element, builder );
        }
    }

    public static class QueryExpressionBinaryProcessor extends AbstractProcessor<QueryExpressionBodyBinary>
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

    public static class QuerySpecificationProcessor extends AbstractProcessor<QuerySpecification>
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
            processor.process( query.getColumns(), builder );
            processor.process( query.getFrom(), builder );
            QueryProcessing.processOptionalBooleanExpression( processor, builder, query.getWhere(),
                SQLConstants.NEWLINE, SQLConstants.WHERE );
            processor.process( query.getGroupBy(), builder );
            QueryProcessing.processOptionalBooleanExpression( processor, builder, query.getHaving(),
                SQLConstants.NEWLINE, SQLConstants.HAVING );
            processor.process( query.getOrderBy(), builder );
        }

    }

    public static class SelectColumnsProcessor extends AbstractProcessor<SelectColumnClause>
    {
        public SelectColumnsProcessor()
        {
            super( SelectColumnClause.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator aggregator, SelectColumnClause select, StringBuilder builder )
        {
            builder.append( SQLConstants.SELECT ).append( SQLConstants.TOKEN_SEPARATOR );
            ProcessorUtils.processSetQuantifier( select.getSetQuantifier(), builder );
            builder.append( SQLConstants.TOKEN_SEPARATOR );

            if( select instanceof ColumnReferences )
            {
                Iterator<ColumnReferenceInfo> iter = ((ColumnReferences) select).getColumns().iterator();
                while( iter.hasNext() )
                {
                    ColumnReferenceInfo info = iter.next();
                    aggregator.process( info.getReference(), builder );
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
    }

    public static class FromProcessor extends AbstractProcessor<FromClause>
    {
        public FromProcessor()
        {
            super( FromClause.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator aggregator, FromClause from, StringBuilder builder )
        {
            if( !from.getTableReferences().isEmpty() )
            {
                builder.append( SQLConstants.NEWLINE ).append( SQLConstants.FROM )
                    .append( SQLConstants.TOKEN_SEPARATOR );
                Iterator<TableReference> iter = from.getTableReferences().iterator();
                while( iter.hasNext() )
                {
                    aggregator.process( iter.next().asTypeable(), builder );
                    if( iter.hasNext() )
                    {
                        builder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                    }
                }
            }
        }
    }

    public static class QueryExpressionProcessor extends AbstractProcessor<QueryExpression>
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

    public static class CorrespondingSpecProcessor extends AbstractProcessor<CorrespondingSpec>
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
                builder.append( SQLConstants.TOKEN_SEPARATOR ).append( "BY" ).append( SQLConstants.TOKEN_SEPARATOR );
                builder.append( SQLConstants.OPEN_PARENTHESIS );
                processor.process( object.getColumnList(), builder );
                builder.append( SQLConstants.CLOSE_PARENTHESIS );
            }
        }
    }

    public static class SortSpecificationProcessor extends AbstractProcessor<SortSpecification>
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

    public static class OrdinaryGroupingSetProcessor extends AbstractProcessor<OrdinaryGroupingSet>
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

    public static class GroupByProcessor extends AbstractProcessor<GroupByClause>
    {
        public GroupByProcessor()
        {
            super( GroupByClause.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator aggregator, GroupByClause groupBy, StringBuilder builder )
        {
            if( !groupBy.getGroupingElements().isEmpty() )
            {
                builder.append( SQLConstants.NEWLINE ).append( SQLConstants.GROUP_BY )
                    .append( SQLConstants.TOKEN_SEPARATOR );
                Iterator<GroupingElement> iter = groupBy.getGroupingElements().iterator();
                while( iter.hasNext() )
                {
                    aggregator.process( iter.next(), builder );
                    if( iter.hasNext() )
                    {
                        builder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                    }
                }
            }
        }
    }

    public static class OrderByProcessor extends AbstractProcessor<OrderByClause>
    {
        public OrderByProcessor()
        {
            super( OrderByClause.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator aggregator, OrderByClause orderBy, StringBuilder builder )
        {
            if( !orderBy.getOrderingColumns().isEmpty() )
            {
                builder.append( SQLConstants.NEWLINE ).append( SQLConstants.ORDER_BY )
                    .append( SQLConstants.TOKEN_SEPARATOR );
                Iterator<SortSpecification> iter = orderBy.getOrderingColumns().iterator();
                while( iter.hasNext() )
                {
                    aggregator.process( iter.next(), builder );
                    if( iter.hasNext() )
                    {
                        builder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                    }
                }
            }
        }
    }

    public static class TableValueConstructorProcessor extends AbstractProcessor<TableValueConstructor>
    {
        public TableValueConstructorProcessor()
        {
            super( TableValueConstructor.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator aggregator, TableValueConstructor object, StringBuilder builder )
        {
            builder.append( "VALUES" ).append( SQLConstants.TOKEN_SEPARATOR );
            for( RowValueConstructor row : object.getRows() )
            {
                aggregator.process( row, builder );
            }
        }
    }

    public static class RowSubQueryProcessor extends AbstractProcessor<RowSubQuery>
    {
        public RowSubQueryProcessor()
        {
            super( RowSubQuery.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator aggregator, RowSubQuery object, StringBuilder builder )
        {
            builder.append( SQLConstants.OPEN_PARENTHESIS );
            aggregator.process( object.getQueryExpression(), builder );
            builder.append( SQLConstants.CLOSE_PARENTHESIS );
        }
    }

    public static class RowDefinitionProcessor extends AbstractProcessor<RowDefinition>
    {
        public RowDefinitionProcessor()
        {
            super( RowDefinition.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator aggregator, RowDefinition object, StringBuilder builder )
        {
            builder.append( SQLConstants.OPEN_PARENTHESIS );
            Iterator<ValueExpression> vals = object.getRowElements().iterator();
            while( vals.hasNext() )
            {
                aggregator.process( vals.next(), builder );
                if( vals.hasNext() )
                {
                    builder.append( SQLConstants.COMMA ).append( SQLConstants.TOKEN_SEPARATOR );
                }
            }
            builder.append( SQLConstants.CLOSE_PARENTHESIS );
        }
    }
}

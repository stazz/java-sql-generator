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

import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.query.TableAlias;
import org.sql.generation.api.grammar.query.TableReferenceByExpression;
import org.sql.generation.api.grammar.query.TableReferenceByName;
import org.sql.generation.api.grammar.query.TableReferencePrimary;
import org.sql.generation.api.grammar.query.joins.CrossJoinedTable;
import org.sql.generation.api.grammar.query.joins.JoinCondition;
import org.sql.generation.api.grammar.query.joins.JoinType;
import org.sql.generation.api.grammar.query.joins.JoinedTable;
import org.sql.generation.api.grammar.query.joins.NamedColumnsJoin;
import org.sql.generation.api.grammar.query.joins.NaturalJoinedTable;
import org.sql.generation.api.grammar.query.joins.QualifiedJoinedTable;
import org.sql.generation.api.grammar.query.joins.UnionJoinedTable;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class TableReferenceProcessing
{

    public static class TableNameProcessor extends AbstractProcessor<TableName>
    {

        public TableNameProcessor()
        {
            this( TableName.class );
        }

        protected TableNameProcessor( Class<? extends TableName> realType )
        {
            super( realType );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, TableName object, StringBuilder builder )
        {
            String schemaName = object.getSchemaName();
            if( ProcessorUtils.notNullAndNotEmpty( schemaName ) )
            {
                builder.append( schemaName ).append( SQLConstants.SCHEMA_TABLE_SEPARATOR );
            }
            builder.append( object.getTableName() );
        }

    }

    public static abstract class TableReferencePrimaryProcessor<TableReferenceType extends TableReferencePrimary>
        extends AbstractProcessor<TableReferenceType>
    {
        public TableReferencePrimaryProcessor( Class<TableReferenceType> realType )
        {
            super( realType );
        }

        protected void doProcess( SQLProcessorAggregator processor, TableReferenceType object, StringBuilder builder )
        {
            this.doProcessTablePrimary( processor, object, builder );
            if( object.getTableAlias() != null )
            {
                this.processTableAlias( processor, object.getTableAlias(), builder );
            }
        }

        protected abstract void doProcessTablePrimary( SQLProcessorAggregator processor, TableReferenceType object,
            StringBuilder builder );

        protected void processTableAlias( SQLProcessorAggregator processor, TableAlias tableAlias, StringBuilder builder )
        {
            String alias = tableAlias.getTableAlias();
            if( ProcessorUtils.notNullAndNotEmpty( alias ) )
            {
                builder.append( SQLConstants.TOKEN_SEPARATOR ).append( SQLConstants.ALIAS_DEFINER )
                    .append( SQLConstants.TOKEN_SEPARATOR ).append( alias );

                if( tableAlias.getColumnAliases() != null )
                {
                    processor.process( tableAlias.getColumnAliases(), builder );
                }
            }
        }
    }

    public static class TableReferenceByNameProcessor extends TableReferencePrimaryProcessor<TableReferenceByName>
    {
        public TableReferenceByNameProcessor()
        {
            super( TableReferenceByName.class );
        }

        @Override
        protected void doProcessTablePrimary( SQLProcessorAggregator processor, TableReferenceByName object,
            StringBuilder builder )
        {
            processor.process( object.getTableName(), builder );
        }

        protected void processTableName( TableName tableName, StringBuilder builder )
        {
            String schemaName = tableName.getSchemaName();
            if( ProcessorUtils.notNullAndNotEmpty( schemaName ) )
            {
                builder.append( schemaName ).append( SQLConstants.SCHEMA_TABLE_SEPARATOR );
            }
            builder.append( tableName.getTableName() );
        }
    }

    public static class TableReferenceByExpressionProcessor extends
        TableReferencePrimaryProcessor<TableReferenceByExpression>
    {
        public TableReferenceByExpressionProcessor()
        {
            super( TableReferenceByExpression.class );
        }

        @Override
        protected void doProcessTablePrimary( SQLProcessorAggregator processor, TableReferenceByExpression tableRef,
            StringBuilder builder )
        {
            builder.append( SQLConstants.OPEN_PARENTHESIS );
            processor.process( tableRef.getQuery(), builder );
            builder.append( SQLConstants.CLOSE_PARENTHESIS );
        }
    }

    public static abstract class JoinedTableProcessor<JoinedTableType extends JoinedTable> extends
        AbstractProcessor<JoinedTableType>
    {

        public JoinedTableProcessor( Class<JoinedTableType> realType )
        {
            super( realType );
        }

        protected void doProcess( SQLProcessorAggregator processor, JoinedTableType table, StringBuilder builder )
        {
            processor.process( table.getLeft().asTypeable(), builder );
            builder.append( SQLConstants.NEWLINE );
            this.doProcessJoinedTable( processor, table, builder );
        }

        protected abstract void doProcessJoinedTable( SQLProcessorAggregator processor, JoinedTableType table,
            StringBuilder builder );

        protected void processJoinType( JoinType joinType, StringBuilder builder )
        {
            if( joinType != null )
            {
                if( joinType == JoinType.INNER )
                {
                    builder.append( "INNER " );
                }
                else
                {
                    if( joinType == JoinType.FULL_OUTER )
                    {
                        builder.append( "FULL " );
                    }
                    if( joinType == JoinType.LEFT_OUTER )
                    {
                        builder.append( "LEFT " );
                    }
                    else
                    // if (joinType == JoinType.RIGHT_OUTER)
                    {
                        builder.append( "RIGHT " );
                    }
                }
                builder.append( "JOIN" ).append( SQLConstants.TOKEN_SEPARATOR );
            }
        }
    }

    public static class CrossJoinedTableProcessor extends JoinedTableProcessor<CrossJoinedTable>
    {
        public CrossJoinedTableProcessor()
        {
            super( CrossJoinedTable.class );
        }

        @Override
        protected void doProcessJoinedTable( SQLProcessorAggregator processor, CrossJoinedTable table,
            StringBuilder builder )
        {
            builder.append( SQLConstants.TOKEN_SEPARATOR ).append( "CROSS JOIN" ).append( SQLConstants.TOKEN_SEPARATOR );
            processor.process( table.getRight().asTypeable(), builder );
        }
    }

    public static class NaturalJoinedTableProcessor extends JoinedTableProcessor<NaturalJoinedTable>
    {
        public NaturalJoinedTableProcessor()
        {
            super( NaturalJoinedTable.class );
        }

        @Override
        protected void doProcessJoinedTable( SQLProcessorAggregator processor, NaturalJoinedTable table,
            StringBuilder builder )
        {
            builder.append( SQLConstants.TOKEN_SEPARATOR ).append( "NATURAL" ).append( SQLConstants.TOKEN_SEPARATOR );
            this.processJoinType( table.getJoinType(), builder );
            builder.append( SQLConstants.TOKEN_SEPARATOR );
            processor.process( table.getRight().asTypeable(), builder );
        }
    }

    public static class QualifiedJoinedTableProcessor extends JoinedTableProcessor<QualifiedJoinedTable>
    {
        public QualifiedJoinedTableProcessor()
        {
            super( QualifiedJoinedTable.class );
        }

        @Override
        protected void doProcessJoinedTable( SQLProcessorAggregator processor, QualifiedJoinedTable table,
            StringBuilder builder )
        {
            this.processJoinType( table.getJoinType(), builder );
            processor.process( table.getRight().asTypeable(), builder );
            processor.process( table.getJoinSpecification(), builder );
        }
    }

    public static class UnionJoinedTableProcessor extends JoinedTableProcessor<UnionJoinedTable>
    {
        public UnionJoinedTableProcessor()
        {
            super( UnionJoinedTable.class );
        }

        @Override
        protected void doProcessJoinedTable( SQLProcessorAggregator processor, UnionJoinedTable table,
            StringBuilder builder )
        {
            builder.append( SQLConstants.TOKEN_SEPARATOR ).append( "UNION JOIN" ).append( SQLConstants.TOKEN_SEPARATOR );
            processor.process( table.getRight().asTypeable(), builder );
        }
    }

    public static class JoinConditionProcessor extends AbstractProcessor<JoinCondition>
    {
        public JoinConditionProcessor()
        {
            super( JoinCondition.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, JoinCondition condition, StringBuilder builder )
        {
            builder.append( SQLConstants.TOKEN_SEPARATOR ).append( "ON" ).append( SQLConstants.TOKEN_SEPARATOR );
            processor.process( condition.getSearchConidition(), builder );
        }
    }

    public static class NamedColumnsJoinProcessor extends AbstractProcessor<NamedColumnsJoin>
    {
        public NamedColumnsJoinProcessor()
        {
            super( NamedColumnsJoin.class );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, NamedColumnsJoin join, StringBuilder builder )
        {
            builder.append( SQLConstants.TOKEN_SEPARATOR ).append( "USING" ).append( SQLConstants.TOKEN_SEPARATOR );

            processor.process( join.getColumnNames(), builder );
        }
    }

}

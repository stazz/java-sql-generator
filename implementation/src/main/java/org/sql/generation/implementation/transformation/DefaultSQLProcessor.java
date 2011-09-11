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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.atp.api.Typeable;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.BetweenPredicate;
import org.sql.generation.api.grammar.booleans.BinaryPredicate;
import org.sql.generation.api.grammar.booleans.BooleanExpression.False;
import org.sql.generation.api.grammar.booleans.BooleanExpression.True;
import org.sql.generation.api.grammar.booleans.BooleanTest;
import org.sql.generation.api.grammar.booleans.Conjunction;
import org.sql.generation.api.grammar.booleans.Disjunction;
import org.sql.generation.api.grammar.booleans.EqualsPredicate;
import org.sql.generation.api.grammar.booleans.ExistsPredicate;
import org.sql.generation.api.grammar.booleans.GreaterOrEqualPredicate;
import org.sql.generation.api.grammar.booleans.GreaterThanPredicate;
import org.sql.generation.api.grammar.booleans.InPredicate;
import org.sql.generation.api.grammar.booleans.IsNotNullPredicate;
import org.sql.generation.api.grammar.booleans.IsNullPredicate;
import org.sql.generation.api.grammar.booleans.LessOrEqualPredicate;
import org.sql.generation.api.grammar.booleans.LessThanPredicate;
import org.sql.generation.api.grammar.booleans.LikePredicate;
import org.sql.generation.api.grammar.booleans.MultiPredicate;
import org.sql.generation.api.grammar.booleans.Negation;
import org.sql.generation.api.grammar.booleans.NotBetweenPredicate;
import org.sql.generation.api.grammar.booleans.NotEqualsPredicate;
import org.sql.generation.api.grammar.booleans.NotInPredicate;
import org.sql.generation.api.grammar.booleans.NotLikePredicate;
import org.sql.generation.api.grammar.booleans.Predicate.EmptyPredicate;
import org.sql.generation.api.grammar.booleans.UnaryPredicate;
import org.sql.generation.api.grammar.booleans.UniquePredicate;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.common.TableNameFunction;
import org.sql.generation.api.grammar.common.datatypes.BigInt;
import org.sql.generation.api.grammar.common.datatypes.Decimal;
import org.sql.generation.api.grammar.common.datatypes.DoublePrecision;
import org.sql.generation.api.grammar.common.datatypes.Numeric;
import org.sql.generation.api.grammar.common.datatypes.Real;
import org.sql.generation.api.grammar.common.datatypes.SQLBoolean;
import org.sql.generation.api.grammar.common.datatypes.SQLChar;
import org.sql.generation.api.grammar.common.datatypes.SQLDate;
import org.sql.generation.api.grammar.common.datatypes.SQLFloat;
import org.sql.generation.api.grammar.common.datatypes.SQLInteger;
import org.sql.generation.api.grammar.common.datatypes.SQLInterval;
import org.sql.generation.api.grammar.common.datatypes.SQLTime;
import org.sql.generation.api.grammar.common.datatypes.SQLTimeStamp;
import org.sql.generation.api.grammar.common.datatypes.SmallInt;
import org.sql.generation.api.grammar.common.datatypes.UserDefinedType;
import org.sql.generation.api.grammar.definition.schema.SchemaDefinition;
import org.sql.generation.api.grammar.definition.table.CheckConstraint;
import org.sql.generation.api.grammar.definition.table.ColumnDefinition;
import org.sql.generation.api.grammar.definition.table.ForeignKeyConstraint;
import org.sql.generation.api.grammar.definition.table.LikeClause;
import org.sql.generation.api.grammar.definition.table.TableConstraintDefinition;
import org.sql.generation.api.grammar.definition.table.TableDefinition;
import org.sql.generation.api.grammar.definition.table.TableElementList;
import org.sql.generation.api.grammar.definition.table.UniqueConstraint;
import org.sql.generation.api.grammar.definition.view.RegularViewSpecification;
import org.sql.generation.api.grammar.definition.view.ViewDefinition;
import org.sql.generation.api.grammar.literals.DateTimeLiteral;
import org.sql.generation.api.grammar.literals.DirectLiteral;
import org.sql.generation.api.grammar.literals.NumericLiteral;
import org.sql.generation.api.grammar.literals.SQLFunctionLiteral;
import org.sql.generation.api.grammar.literals.StringLiteral;
import org.sql.generation.api.grammar.manipulation.AddColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AddTableConstraintDefinition;
import org.sql.generation.api.grammar.manipulation.AlterColumnAction.DropDefault;
import org.sql.generation.api.grammar.manipulation.AlterColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AlterTableStatement;
import org.sql.generation.api.grammar.manipulation.DropColumnDefinition;
import org.sql.generation.api.grammar.manipulation.DropSchemaStatement;
import org.sql.generation.api.grammar.manipulation.DropTableConstraintDefinition;
import org.sql.generation.api.grammar.manipulation.DropTableOrViewStatement;
import org.sql.generation.api.grammar.manipulation.SetColumnDefault;
import org.sql.generation.api.grammar.modification.ColumnSource.Defaults;
import org.sql.generation.api.grammar.modification.ColumnSourceByQuery;
import org.sql.generation.api.grammar.modification.ColumnSourceByValues;
import org.sql.generation.api.grammar.modification.DeleteBySearch;
import org.sql.generation.api.grammar.modification.InsertStatement;
import org.sql.generation.api.grammar.modification.SetClause;
import org.sql.generation.api.grammar.modification.TargetTable;
import org.sql.generation.api.grammar.modification.UpdateBySearch;
import org.sql.generation.api.grammar.modification.UpdateSource.Default;
import org.sql.generation.api.grammar.modification.UpdateSource.Null;
import org.sql.generation.api.grammar.modification.UpdateSourceByExpression;
import org.sql.generation.api.grammar.query.AsteriskSelect;
import org.sql.generation.api.grammar.query.ColumnReferenceByExpression;
import org.sql.generation.api.grammar.query.ColumnReferenceByName;
import org.sql.generation.api.grammar.query.ColumnReferences;
import org.sql.generation.api.grammar.query.CorrespondingSpec;
import org.sql.generation.api.grammar.query.FromClause;
import org.sql.generation.api.grammar.query.GroupByClause;
import org.sql.generation.api.grammar.query.GroupingElement.GrandTotal;
import org.sql.generation.api.grammar.query.OrderByClause;
import org.sql.generation.api.grammar.query.OrdinaryGroupingSet;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.QueryExpressionBody.EmptyQueryExpressionBody;
import org.sql.generation.api.grammar.query.QueryExpressionBodyBinary;
import org.sql.generation.api.grammar.query.QuerySpecification;
import org.sql.generation.api.grammar.query.RowDefinition;
import org.sql.generation.api.grammar.query.RowSubQuery;
import org.sql.generation.api.grammar.query.SortSpecification;
import org.sql.generation.api.grammar.query.TableReferenceByExpression;
import org.sql.generation.api.grammar.query.TableReferenceByName;
import org.sql.generation.api.grammar.query.TableValueConstructor;
import org.sql.generation.api.grammar.query.joins.CrossJoinedTable;
import org.sql.generation.api.grammar.query.joins.JoinCondition;
import org.sql.generation.api.grammar.query.joins.NamedColumnsJoin;
import org.sql.generation.api.grammar.query.joins.NaturalJoinedTable;
import org.sql.generation.api.grammar.query.joins.QualifiedJoinedTable;
import org.sql.generation.api.grammar.query.joins.UnionJoinedTable;
import org.sql.generation.api.vendor.UnsupportedElementException;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.BinaryPredicateProcessor;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.BooleanTestProcessor;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.ConjunctionProcessor;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.DisjunctionProcessor;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.MultiPredicateProcessor;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.NegationProcessor;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.UnaryPredicateProcessor;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.UnaryPredicateProcessor.UnaryOperatorOrientation;
import org.sql.generation.implementation.transformation.ColumnProcessing.ColumnNamesProcessor;
import org.sql.generation.implementation.transformation.ColumnProcessing.ColumnReferenceByExpressionProcessor;
import org.sql.generation.implementation.transformation.ColumnProcessing.ColumnReferenceByNameProcessor;
import org.sql.generation.implementation.transformation.DataTypeProcessing.DecimalProcessor;
import org.sql.generation.implementation.transformation.DataTypeProcessing.NumericProcessor;
import org.sql.generation.implementation.transformation.DataTypeProcessing.SQLCharProcessor;
import org.sql.generation.implementation.transformation.DataTypeProcessing.SQLFloatProcessor;
import org.sql.generation.implementation.transformation.DataTypeProcessing.SQLIntervalProcessor;
import org.sql.generation.implementation.transformation.DataTypeProcessing.SQLTimeProcessor;
import org.sql.generation.implementation.transformation.DataTypeProcessing.SQLTimeStampProcessor;
import org.sql.generation.implementation.transformation.DataTypeProcessing.UserDefinedDataTypeProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.CheckConstraintProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.ColumnDefinitionProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.ForeignKeyConstraintProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.LikeClauseProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.RegularViewSpecificationProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.SchemaDefinitionProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.TableConstraintDefinitionProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.TableDefinitionProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.TableElementListProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.UniqueConstraintProcessor;
import org.sql.generation.implementation.transformation.DefinitionProcessing.ViewDefinitionProcessor;
import org.sql.generation.implementation.transformation.LiteralExpressionProcessing.DateTimeLiteralProcessor;
import org.sql.generation.implementation.transformation.LiteralExpressionProcessing.DirectLiteralProcessor;
import org.sql.generation.implementation.transformation.LiteralExpressionProcessing.NumericLiteralProcessor;
import org.sql.generation.implementation.transformation.LiteralExpressionProcessing.SQLFunctionLiteralProcessor;
import org.sql.generation.implementation.transformation.LiteralExpressionProcessing.StringLiteralExpressionProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.AddColumnDefinitionProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.AddTableConstraintDefinitionProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.AlterColumnDefinitionProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.AlterTableStatementProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.DropColumnDefaultProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.DropColumnDefinitionProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.DropSchemaStatementProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.DropTableConstraintDefinitionProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.DropTableOrViewStatementProcessor;
import org.sql.generation.implementation.transformation.ManipulationProcessing.SetColumnDefaultProcessor;
import org.sql.generation.implementation.transformation.ModificationProcessing.ColumnSourceByQueryProcessor;
import org.sql.generation.implementation.transformation.ModificationProcessing.ColumnSourceByValuesProcessor;
import org.sql.generation.implementation.transformation.ModificationProcessing.DeleteBySearchProcessor;
import org.sql.generation.implementation.transformation.ModificationProcessing.InsertStatementProcessor;
import org.sql.generation.implementation.transformation.ModificationProcessing.SetClauseProcessor;
import org.sql.generation.implementation.transformation.ModificationProcessing.TargetTableProcessor;
import org.sql.generation.implementation.transformation.ModificationProcessing.UpdateBySearchProcessor;
import org.sql.generation.implementation.transformation.ModificationProcessing.UpdateSourceByExpressionProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.CorrespondingSpecProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.FromProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.GroupByProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.OrderByProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.OrdinaryGroupingSetProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.QueryExpressionBinaryProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.QueryExpressionProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.QuerySpecificationProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.RowDefinitionProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.RowSubQueryProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.SelectColumnsProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.SortSpecificationProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.TableValueConstructorProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.CrossJoinedTableProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.JoinConditionProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.NamedColumnsJoinProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.NaturalJoinedTableProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.QualifiedJoinedTableProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.TableNameDirectProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.TableNameFunctionProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.TableReferenceByExpressionProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.TableReferenceByNameProcessor;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.UnionJoinedTableProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * This is base class for processing the SQL syntax elements defined in API. It contains the default processors for
 * nearly all default syntax elements, and a way to easily extend this class in order to add custom processors, or
 * replace default processors with custom ones.
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultSQLProcessor
    implements SQLProcessorAggregator
{

    private static final Map<Class<? extends Typeable<?>>, SQLProcessor> _defaultProcessors;

    private static final Map<Class<? extends UnaryPredicate>, UnaryOperatorOrientation> _defaultUnaryOrientations;

    private static final Map<Class<? extends UnaryPredicate>, String> _defaultUnaryOperators;

    private static final Map<Class<? extends BinaryPredicate>, String> _defaultBinaryOperators;

    private static final Map<Class<? extends MultiPredicate>, String> _defaultMultiOperators;

    private static final Map<Class<? extends MultiPredicate>, String> _defaultMultiSeparators;

    private static final Map<Class<? extends MultiPredicate>, Boolean> _defaultParenthesisPolicies;

    static
    {
        Map<Class<? extends UnaryPredicate>, UnaryOperatorOrientation> unaryOrientations = new HashMap<Class<? extends UnaryPredicate>, UnaryOperatorOrientation>();
        unaryOrientations.put( IsNullPredicate.class, UnaryOperatorOrientation.AFTER_EXPRESSION );
        unaryOrientations.put( IsNotNullPredicate.class, UnaryOperatorOrientation.AFTER_EXPRESSION );
        unaryOrientations.put( ExistsPredicate.class, UnaryOperatorOrientation.BEFORE_EXPRESSION );
        unaryOrientations.put( UniquePredicate.class, UnaryOperatorOrientation.BEFORE_EXPRESSION );
        _defaultUnaryOrientations = Collections.unmodifiableMap( unaryOrientations );

        Map<Class<? extends UnaryPredicate>, String> unaryOperators = new HashMap<Class<? extends UnaryPredicate>, String>();
        unaryOperators.put( IsNullPredicate.class, "IS NULL" );
        unaryOperators.put( IsNotNullPredicate.class, "IS NOT NULL" );
        unaryOperators.put( ExistsPredicate.class, "EXISTS" );
        unaryOperators.put( UniquePredicate.class, "UNIQUE" );
        _defaultUnaryOperators = Collections.unmodifiableMap( unaryOperators );

        Map<Class<? extends BinaryPredicate>, String> binaryOperators = new HashMap<Class<? extends BinaryPredicate>, String>();
        binaryOperators.put( EqualsPredicate.class, "=" );
        binaryOperators.put( NotEqualsPredicate.class, "<>" );
        binaryOperators.put( GreaterOrEqualPredicate.class, ">=" );
        binaryOperators.put( GreaterThanPredicate.class, ">" );
        binaryOperators.put( LessOrEqualPredicate.class, "<=" );
        binaryOperators.put( LessThanPredicate.class, "<" );
        binaryOperators.put( LikePredicate.class, "LIKE" );
        binaryOperators.put( NotLikePredicate.class, "NOT LIKE" );
        _defaultBinaryOperators = Collections.unmodifiableMap( binaryOperators );

        Map<Class<? extends MultiPredicate>, String> multiOperators = new HashMap<Class<? extends MultiPredicate>, String>();
        multiOperators.put( BetweenPredicate.class, "BETWEEN" );
        multiOperators.put( InPredicate.class, "IN" );
        multiOperators.put( NotBetweenPredicate.class, "NOT BETWEEN" );
        multiOperators.put( NotInPredicate.class, "NOT IN" );
        _defaultMultiOperators = Collections.unmodifiableMap( multiOperators );

        Map<Class<? extends MultiPredicate>, String> multiSeparators = new HashMap<Class<? extends MultiPredicate>, String>();
        multiSeparators.put( BetweenPredicate.class, " AND " );
        multiSeparators.put( InPredicate.class, ", " );
        multiSeparators.put( NotBetweenPredicate.class, " AND " );
        multiSeparators.put( NotInPredicate.class, ", " );
        _defaultMultiSeparators = Collections.unmodifiableMap( multiSeparators );

        Map<Class<? extends MultiPredicate>, Boolean> parenthesisPolicies = new HashMap<Class<? extends MultiPredicate>, Boolean>();
        parenthesisPolicies.put( BetweenPredicate.class, false );
        parenthesisPolicies.put( InPredicate.class, true );
        parenthesisPolicies.put( NotBetweenPredicate.class, false );
        parenthesisPolicies.put( NotInPredicate.class, true );
        _defaultParenthesisPolicies = parenthesisPolicies;

        Map<Class<? extends Typeable<?>>, SQLProcessor> processors = new HashMap<Class<? extends Typeable<?>>, SQLProcessor>();

        // Boolean expressions
        // Constants
        processors.put( True.class, new ConstantProcessor( "TRUE" ) );
        processors.put( False.class, new ConstantProcessor( "FALSE" ) );
        // Unary
        processors.put(
            IsNullPredicate.class,
            new UnaryPredicateProcessor( _defaultUnaryOrientations.get( IsNullPredicate.class ), _defaultUnaryOperators
                .get( IsNullPredicate.class ) ) );
        processors.put( IsNotNullPredicate.class,
            new UnaryPredicateProcessor( _defaultUnaryOrientations.get( IsNotNullPredicate.class ),
                _defaultUnaryOperators.get( IsNotNullPredicate.class ) ) );
        processors.put(
            ExistsPredicate.class,
            new UnaryPredicateProcessor( _defaultUnaryOrientations.get( ExistsPredicate.class ), _defaultUnaryOperators
                .get( ExistsPredicate.class ) ) );
        processors.put(
            UniquePredicate.class,
            new UnaryPredicateProcessor( _defaultUnaryOrientations.get( UniquePredicate.class ), _defaultUnaryOperators
                .get( UniquePredicate.class ) ) );
        // Binary
        processors.put( EqualsPredicate.class,
            new BinaryPredicateProcessor( _defaultBinaryOperators.get( EqualsPredicate.class ) ) );
        processors.put( NotEqualsPredicate.class,
            new BinaryPredicateProcessor( _defaultBinaryOperators.get( NotEqualsPredicate.class ) ) );
        processors.put( GreaterOrEqualPredicate.class,
            new BinaryPredicateProcessor( _defaultBinaryOperators.get( GreaterOrEqualPredicate.class ) ) );
        processors.put( GreaterThanPredicate.class,
            new BinaryPredicateProcessor( _defaultBinaryOperators.get( GreaterThanPredicate.class ) ) );
        processors.put( LessOrEqualPredicate.class,
            new BinaryPredicateProcessor( _defaultBinaryOperators.get( LessOrEqualPredicate.class ) ) );
        processors.put( LessThanPredicate.class,
            new BinaryPredicateProcessor( _defaultBinaryOperators.get( LessThanPredicate.class ) ) );
        processors.put( LikePredicate.class,
            new BinaryPredicateProcessor( _defaultBinaryOperators.get( LikePredicate.class ) ) );
        processors.put( NotLikePredicate.class,
            new BinaryPredicateProcessor( _defaultBinaryOperators.get( NotLikePredicate.class ) ) );
        // Multi
        processors.put(
            BetweenPredicate.class,
            new MultiPredicateProcessor( _defaultMultiOperators.get( BetweenPredicate.class ), _defaultMultiSeparators
                .get( BetweenPredicate.class ), _defaultParenthesisPolicies.get( BetweenPredicate.class ) ) );
        processors.put( InPredicate.class, new MultiPredicateProcessor(
            _defaultMultiOperators.get( InPredicate.class ), _defaultMultiSeparators.get( InPredicate.class ),
            _defaultParenthesisPolicies.get( InPredicate.class ) ) );
        processors.put(
            NotBetweenPredicate.class,
            new MultiPredicateProcessor( _defaultMultiOperators.get( NotBetweenPredicate.class ),
                _defaultMultiSeparators.get( NotBetweenPredicate.class ), _defaultParenthesisPolicies
                    .get( NotBetweenPredicate.class ) ) );
        processors.put(
            NotInPredicate.class,
            new MultiPredicateProcessor( _defaultMultiOperators.get( NotInPredicate.class ), _defaultMultiSeparators
                .get( NotInPredicate.class ), _defaultParenthesisPolicies.get( NotInPredicate.class ) ) );
        // Composed
        processors.put( Conjunction.class, new ConjunctionProcessor() );
        processors.put( Disjunction.class, new DisjunctionProcessor() );
        processors.put( Negation.class, new NegationProcessor() );
        processors.put( BooleanTest.class, new BooleanTestProcessor() );
        // Empty
        processors.put( EmptyPredicate.class, new NoOpProcessor() );

        // Column references
        processors.put( ColumnReferenceByName.class, new ColumnReferenceByNameProcessor() );
        processors.put( ColumnReferenceByExpression.class, new ColumnReferenceByExpressionProcessor() );
        processors.put( ColumnNameList.class, new ColumnNamesProcessor() );

        // Literals
        processors.put( StringLiteral.class, new StringLiteralExpressionProcessor() );
        processors.put( DateTimeLiteral.class, new DateTimeLiteralProcessor() );
        processors.put( SQLFunctionLiteral.class, new SQLFunctionLiteralProcessor() );
        processors.put( NumericLiteral.class, new NumericLiteralProcessor() );
        processors.put( DirectLiteral.class, new DirectLiteralProcessor() );

        // Queries
        processors.put( QueryExpressionBodyBinary.class, new QueryExpressionBinaryProcessor() );
        processors.put( QuerySpecification.class, new QuerySpecificationProcessor() );
        processors.put( QueryExpression.class, new QueryExpressionProcessor() );
        processors.put( EmptyQueryExpressionBody.class, new NoOpProcessor() );
        processors.put( CorrespondingSpec.class, new CorrespondingSpecProcessor() );
        processors.put( GrandTotal.class, new ConstantProcessor( SQLConstants.OPEN_PARENTHESIS
            + SQLConstants.CLOSE_PARENTHESIS ) );
        processors.put( OrdinaryGroupingSet.class, new OrdinaryGroupingSetProcessor() );
        processors.put( SortSpecification.class, new SortSpecificationProcessor() );
        processors.put( GroupByClause.class, new GroupByProcessor() );
        processors.put( OrderByClause.class, new OrderByProcessor() );
        processors.put( FromClause.class, new FromProcessor() );
        SelectColumnsProcessor selectProcessor = new SelectColumnsProcessor();
        processors.put( AsteriskSelect.class, selectProcessor );
        processors.put( ColumnReferences.class, selectProcessor );
        processors.put( TableValueConstructor.class, new TableValueConstructorProcessor() );
        processors.put( RowDefinition.class, new RowDefinitionProcessor() );
        processors.put( RowSubQuery.class, new RowSubQueryProcessor() );

        // Table references
        processors.put( TableNameDirect.class, new TableNameDirectProcessor() );
        processors.put( TableNameFunction.class, new TableNameFunctionProcessor() );
        processors.put( TableReferenceByName.class, new TableReferenceByNameProcessor() );
        processors.put( TableReferenceByExpression.class, new TableReferenceByExpressionProcessor() );
        processors.put( CrossJoinedTable.class, new CrossJoinedTableProcessor() );
        processors.put( NaturalJoinedTable.class, new NaturalJoinedTableProcessor() );
        processors.put( QualifiedJoinedTable.class, new QualifiedJoinedTableProcessor() );
        processors.put( UnionJoinedTable.class, new UnionJoinedTableProcessor() );
        processors.put( JoinCondition.class, new JoinConditionProcessor() );
        processors.put( NamedColumnsJoin.class, new NamedColumnsJoinProcessor() );

        // Modification clauses
        processors.put( ColumnSourceByQuery.class, new ColumnSourceByQueryProcessor() );
        processors.put( ColumnSourceByValues.class, new ColumnSourceByValuesProcessor() );
        processors.put( DeleteBySearch.class, new DeleteBySearchProcessor() );
        processors.put( InsertStatement.class, new InsertStatementProcessor() );
        processors.put( SetClause.class, new SetClauseProcessor() );
        processors.put( TargetTable.class, new TargetTableProcessor() );
        processors.put( UpdateBySearch.class, new UpdateBySearchProcessor() );
        processors.put( UpdateSourceByExpression.class, new UpdateSourceByExpressionProcessor() );
        processors.put( Null.class, new ConstantProcessor( "NULL" ) );
        processors.put( Default.class, new ConstantProcessor( "DEFAULT" ) );
        processors.put( Defaults.class, new ConstantProcessor( SQLConstants.TOKEN_SEPARATOR + "DEFAULT VALUES" ) );

        // Data definition
        // First data types
        processors.put( BigInt.class, new ConstantProcessor( "BIGINT" ) );
        processors.put( DoublePrecision.class, new ConstantProcessor( "DOUBLE PRECISION" ) );
        processors.put( Real.class, new ConstantProcessor( "REAL" ) );
        processors.put( SmallInt.class, new ConstantProcessor( "SMALLINT" ) );
        processors.put( SQLBoolean.class, new ConstantProcessor( "BOOLEAN" ) );
        processors.put( SQLDate.class, new ConstantProcessor( "DATE" ) );
        processors.put( SQLInteger.class, new ConstantProcessor( "INTEGER" ) );
        processors.put( UserDefinedType.class, new UserDefinedDataTypeProcessor() );
        processors.put( Decimal.class, new DecimalProcessor() );
        processors.put( Numeric.class, new NumericProcessor() );
        processors.put( SQLChar.class, new SQLCharProcessor() );
        processors.put( SQLFloat.class, new SQLFloatProcessor() );
        processors.put( SQLInterval.class, new SQLIntervalProcessor() );
        processors.put( SQLTime.class, new SQLTimeProcessor() );
        processors.put( SQLTimeStamp.class, new SQLTimeStampProcessor() );
        // Then statements and clauses
        processors.put( SchemaDefinition.class, new SchemaDefinitionProcessor() );
        processors.put( TableDefinition.class, new TableDefinitionProcessor() );
        processors.put( TableElementList.class, new TableElementListProcessor() );
        processors.put( ColumnDefinition.class, new ColumnDefinitionProcessor() );
        processors.put( LikeClause.class, new LikeClauseProcessor() );
        processors.put( TableConstraintDefinition.class, new TableConstraintDefinitionProcessor() );
        processors.put( CheckConstraint.class, new CheckConstraintProcessor() );
        processors.put( UniqueConstraint.class, new UniqueConstraintProcessor() );
        processors.put( ForeignKeyConstraint.class, new ForeignKeyConstraintProcessor() );
        processors.put( ViewDefinition.class, new ViewDefinitionProcessor() );
        processors.put( RegularViewSpecification.class, new RegularViewSpecificationProcessor() );

        // Data manipulation
        processors.put( AlterTableStatement.class, new AlterTableStatementProcessor() );
        processors.put( AddColumnDefinition.class, new AddColumnDefinitionProcessor() );
        processors.put( AddTableConstraintDefinition.class, new AddTableConstraintDefinitionProcessor() );
        processors.put( AlterColumnDefinition.class, new AlterColumnDefinitionProcessor() );
        processors.put( DropDefault.class, new DropColumnDefaultProcessor() );
        processors.put( SetColumnDefault.class, new SetColumnDefaultProcessor() );
        processors.put( DropColumnDefinition.class, new DropColumnDefinitionProcessor() );
        processors.put( DropTableConstraintDefinition.class, new DropTableConstraintDefinitionProcessor() );
        processors.put( DropSchemaStatement.class, new DropSchemaStatementProcessor() );
        processors.put( DropTableOrViewStatement.class, new DropTableOrViewStatementProcessor() );

        _defaultProcessors = Collections.unmodifiableMap( processors );
    }

    private final Map<Class<? extends Typeable<?>>, SQLProcessor> _processors;

    public DefaultSQLProcessor()
    {
        this( _defaultProcessors );
    }

    public DefaultSQLProcessor( Map<Class<? extends Typeable<?>>, SQLProcessor> processors )
    {
        NullArgumentException.validateNotNull( "Processors", processors );

        this._processors = processors;
    }

    public void process( Typeable<?> object, StringBuilder builder )
    {
        SQLProcessor processor = this._processors.get( object.getImplementedType() );
        if( processor != null )
        {
            processor.process( this, object, builder );
        }
        else
        {
            throw new UnsupportedElementException( "The vendor " + this.getClass()
                + " does not know how to handle element " + object + ".", object );
        }
    }

    public static Map<Class<? extends Typeable<?>>, SQLProcessor> getDefaultProcessors()
    {
        return _defaultProcessors;
    }

    public static Map<Class<? extends BinaryPredicate>, String> getDefaultBinaryOperators()
    {
        return _defaultBinaryOperators;
    }

    public static Map<Class<? extends MultiPredicate>, String> getDefaultMultiOperators()
    {
        return _defaultMultiOperators;
    }

    public static Map<Class<? extends MultiPredicate>, String> getDefaultMultiSeparators()
    {
        return _defaultMultiSeparators;
    }

    public static Map<Class<? extends MultiPredicate>, Boolean> getDefaultParenthesisPolicies()
    {
        return _defaultParenthesisPolicies;
    }

    public static Map<Class<? extends UnaryPredicate>, String> getDefaultUnaryOperators()
    {
        return _defaultUnaryOperators;
    }

    public static Map<Class<? extends UnaryPredicate>, UnaryOperatorOrientation> getDefaultUnaryOrientations()
    {
        return _defaultUnaryOrientations;
    }

}

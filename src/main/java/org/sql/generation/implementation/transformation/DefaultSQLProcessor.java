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

import org.atp.api.Typeable;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.booleans.*;
import org.sql.generation.api.grammar.booleans.BooleanExpression.False;
import org.sql.generation.api.grammar.booleans.BooleanExpression.True;
import org.sql.generation.api.grammar.booleans.Predicate.EmptyPredicate;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.common.TableNameFunction;
import org.sql.generation.api.grammar.common.datatypes.*;
import org.sql.generation.api.grammar.definition.schema.SchemaDefinition;
import org.sql.generation.api.grammar.definition.table.*;
import org.sql.generation.api.grammar.definition.view.RegularViewSpecification;
import org.sql.generation.api.grammar.definition.view.ViewDefinition;
import org.sql.generation.api.grammar.literals.*;
import org.sql.generation.api.grammar.manipulation.*;
import org.sql.generation.api.grammar.manipulation.AlterColumnAction.DropDefault;
import org.sql.generation.api.grammar.modification.ColumnSource.Defaults;
import org.sql.generation.api.grammar.modification.*;
import org.sql.generation.api.grammar.query.*;
import org.sql.generation.api.grammar.query.GroupingElement.GrandTotal;
import org.sql.generation.api.grammar.query.QueryExpressionBody.EmptyQueryExpressionBody;
import org.sql.generation.api.grammar.query.joins.*;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.api.vendor.UnsupportedElementException;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.*;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.UnaryPredicateProcessor.UnaryOperatorOrientation;
import org.sql.generation.implementation.transformation.ColumnProcessing.ColumnNamesProcessor;
import org.sql.generation.implementation.transformation.ColumnProcessing.ColumnReferenceByExpressionProcessor;
import org.sql.generation.implementation.transformation.ColumnProcessing.ColumnReferenceByNameProcessor;
import org.sql.generation.implementation.transformation.DataTypeProcessing.*;
import org.sql.generation.implementation.transformation.DefinitionProcessing.*;
import org.sql.generation.implementation.transformation.LiteralExpressionProcessing.*;
import org.sql.generation.implementation.transformation.ManipulationProcessing.*;
import org.sql.generation.implementation.transformation.ModificationProcessing.*;
import org.sql.generation.implementation.transformation.QueryProcessing.*;
import org.sql.generation.implementation.transformation.TableReferenceProcessing.*;
import org.sql.generation.implementation.transformation.spi.SQLProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This is base class for processing the SQL syntax elements defined in API. It contains the default
 * processors for nearly all default syntax elements, and a way to easily extend this class in order
 * to add custom processors, or replace default processors with custom ones.
 *
 * @author Stanislav Muhametsin
 */
public class DefaultSQLProcessor
        implements SQLProcessorAggregator {

    private static final Map<Class<? extends Typeable<?>>, SQLProcessor> _defaultProcessors;

    private static final Map<Class<? extends UnaryPredicate>, UnaryOperatorOrientation> _defaultUnaryOrientations;

    private static final Map<Class<? extends UnaryPredicate>, String> _defaultUnaryOperators;

    private static final Map<Class<? extends BinaryPredicate>, String> _defaultBinaryOperators;

    private static final Map<Class<? extends MultiPredicate>, String> _defaultMultiOperators;

    private static final Map<Class<? extends MultiPredicate>, String> _defaultMultiSeparators;

    private static final Map<Class<? extends MultiPredicate>, Boolean> _defaultParenthesisPolicies;

    static {
        final Map<Class<? extends UnaryPredicate>, UnaryOperatorOrientation> unaryOrientations =
                new HashMap<>();
        unaryOrientations.put(IsNullPredicate.class, UnaryOperatorOrientation.AFTER_EXPRESSION);
        unaryOrientations.put(IsNotNullPredicate.class, UnaryOperatorOrientation.AFTER_EXPRESSION);
        unaryOrientations.put(ExistsPredicate.class, UnaryOperatorOrientation.BEFORE_EXPRESSION);
        unaryOrientations.put(UniquePredicate.class, UnaryOperatorOrientation.BEFORE_EXPRESSION);
        _defaultUnaryOrientations = Collections.unmodifiableMap(unaryOrientations);

        final Map<Class<? extends UnaryPredicate>, String> unaryOperators =
                new HashMap<>();
        unaryOperators.put(IsNullPredicate.class, "IS NULL");
        unaryOperators.put(IsNotNullPredicate.class, "IS NOT NULL");
        unaryOperators.put(ExistsPredicate.class, "EXISTS");
        unaryOperators.put(UniquePredicate.class, "UNIQUE");
        _defaultUnaryOperators = Collections.unmodifiableMap(unaryOperators);

        final Map<Class<? extends BinaryPredicate>, String> binaryOperators =
                new HashMap<>();
        binaryOperators.put(EqualsPredicate.class, "=");
        binaryOperators.put(NotEqualsPredicate.class, "<>");
        binaryOperators.put(GreaterOrEqualPredicate.class, ">=");
        binaryOperators.put(GreaterThanPredicate.class, ">");
        binaryOperators.put(LessOrEqualPredicate.class, "<=");
        binaryOperators.put(LessThanPredicate.class, "<");
        binaryOperators.put(LikePredicate.class, "LIKE");
        binaryOperators.put(NotLikePredicate.class, "NOT LIKE");
        _defaultBinaryOperators = Collections.unmodifiableMap(binaryOperators);

        final Map<Class<? extends MultiPredicate>, String> multiOperators =
                new HashMap<>();
        multiOperators.put(BetweenPredicate.class, "BETWEEN");
        multiOperators.put(InPredicate.class, "IN");
        multiOperators.put(NotBetweenPredicate.class, "NOT BETWEEN");
        multiOperators.put(NotInPredicate.class, "NOT IN");
        _defaultMultiOperators = Collections.unmodifiableMap(multiOperators);

        final Map<Class<? extends MultiPredicate>, String> multiSeparators =
                new HashMap<>();
        multiSeparators.put(BetweenPredicate.class, " AND ");
        multiSeparators.put(InPredicate.class, ", ");
        multiSeparators.put(NotBetweenPredicate.class, " AND ");
        multiSeparators.put(NotInPredicate.class, ", ");
        _defaultMultiSeparators = Collections.unmodifiableMap(multiSeparators);

        final Map<Class<? extends MultiPredicate>, Boolean> parenthesisPolicies =
                new HashMap<>();
        parenthesisPolicies.put(BetweenPredicate.class, false);
        parenthesisPolicies.put(InPredicate.class, true);
        parenthesisPolicies.put(NotBetweenPredicate.class, false);
        parenthesisPolicies.put(NotInPredicate.class, true);
        _defaultParenthesisPolicies = parenthesisPolicies;

        final Map<Class<? extends Typeable<?>>, SQLProcessor> processors =
                new HashMap<>();

        // Boolean expressions
        // Constants
        processors.put(True.class, new ConstantProcessor("TRUE"));
        processors.put(False.class, new ConstantProcessor("FALSE"));
        // Unary
        processors.put(
                IsNullPredicate.class,
                new UnaryPredicateProcessor(DefaultSQLProcessor._defaultUnaryOrientations.get(IsNullPredicate.class),
                        DefaultSQLProcessor._defaultUnaryOperators
                                .get(IsNullPredicate.class)));
        processors.put(IsNotNullPredicate.class,
                new UnaryPredicateProcessor(DefaultSQLProcessor._defaultUnaryOrientations.get(IsNotNullPredicate.class),
                        DefaultSQLProcessor._defaultUnaryOperators.get(IsNotNullPredicate.class)));
        processors.put(
                ExistsPredicate.class,
                new UnaryPredicateProcessor(DefaultSQLProcessor._defaultUnaryOrientations.get(ExistsPredicate.class),
                        DefaultSQLProcessor._defaultUnaryOperators
                                .get(ExistsPredicate.class)));
        processors.put(
                UniquePredicate.class,
                new UnaryPredicateProcessor(DefaultSQLProcessor._defaultUnaryOrientations.get(UniquePredicate.class),
                        DefaultSQLProcessor._defaultUnaryOperators
                                .get(UniquePredicate.class)));
        // Binary
        processors.put(EqualsPredicate.class,
                new BinaryPredicateProcessor(DefaultSQLProcessor._defaultBinaryOperators.get(EqualsPredicate.class)));
        processors
                .put(
                        NotEqualsPredicate.class,
                        new BinaryPredicateProcessor(DefaultSQLProcessor._defaultBinaryOperators
                                .get(NotEqualsPredicate.class)));
        processors.put(
                GreaterOrEqualPredicate.class,
                new BinaryPredicateProcessor(DefaultSQLProcessor._defaultBinaryOperators
                        .get(GreaterOrEqualPredicate.class)));
        processors
                .put(
                        GreaterThanPredicate.class,
                        new BinaryPredicateProcessor(DefaultSQLProcessor._defaultBinaryOperators
                                .get(GreaterThanPredicate.class)));
        processors
                .put(
                        LessOrEqualPredicate.class,
                        new BinaryPredicateProcessor(DefaultSQLProcessor._defaultBinaryOperators
                                .get(LessOrEqualPredicate.class)));
        processors.put(LessThanPredicate.class,
                new BinaryPredicateProcessor(DefaultSQLProcessor._defaultBinaryOperators.get(LessThanPredicate.class)));
        processors.put(LikePredicate.class,
                new BinaryPredicateProcessor(DefaultSQLProcessor._defaultBinaryOperators.get(LikePredicate.class)));
        processors.put(NotLikePredicate.class,
                new BinaryPredicateProcessor(DefaultSQLProcessor._defaultBinaryOperators.get(NotLikePredicate.class)));
        // Multi
        processors.put(
                BetweenPredicate.class,
                new MultiPredicateProcessor(DefaultSQLProcessor._defaultMultiOperators.get(BetweenPredicate.class),
                        DefaultSQLProcessor._defaultMultiSeparators
                                .get(BetweenPredicate.class), DefaultSQLProcessor._defaultParenthesisPolicies
                        .get(BetweenPredicate.class)));
        processors.put(
                InPredicate.class,
                new MultiPredicateProcessor(
                        DefaultSQLProcessor._defaultMultiOperators.get(InPredicate.class), DefaultSQLProcessor._defaultMultiSeparators
                        .get(InPredicate.class),
                        DefaultSQLProcessor._defaultParenthesisPolicies.get(InPredicate.class)));
        processors.put(
                NotBetweenPredicate.class,
                new MultiPredicateProcessor(DefaultSQLProcessor._defaultMultiOperators.get(NotBetweenPredicate.class),
                        DefaultSQLProcessor._defaultMultiSeparators.get(NotBetweenPredicate.class),
                        DefaultSQLProcessor._defaultParenthesisPolicies
                                .get(NotBetweenPredicate.class)));
        processors.put(
                NotInPredicate.class,
                new MultiPredicateProcessor(DefaultSQLProcessor._defaultMultiOperators.get(NotInPredicate.class),
                        DefaultSQLProcessor._defaultMultiSeparators
                                .get(NotInPredicate.class), DefaultSQLProcessor._defaultParenthesisPolicies
                        .get(NotInPredicate.class)));
        // Composed
        processors.put(Conjunction.class, new ConjunctionProcessor());
        processors.put(Disjunction.class, new DisjunctionProcessor());
        processors.put(Negation.class, new NegationProcessor());
        processors.put(BooleanTest.class, new BooleanTestProcessor());
        // Empty
        processors.put(EmptyPredicate.class, new NoOpProcessor());

        // Column references
        processors.put(ColumnReferenceByName.class, new ColumnReferenceByNameProcessor());
        processors.put(ColumnReferenceByExpression.class,
                new ColumnReferenceByExpressionProcessor());
        processors.put(ColumnNameList.class, new ColumnNamesProcessor());

        // Literals
        processors.put(StringLiteral.class, new StringLiteralExpressionProcessor());
        processors.put(TimestampTimeLiteral.class, new DateTimeLiteralProcessor());
        processors.put(SQLFunctionLiteral.class, new SQLFunctionLiteralProcessor());
        processors.put(NumericLiteral.class, new NumericLiteralProcessor());
        processors.put(DirectLiteral.class, new DirectLiteralProcessor());

        // Queries
        processors.put(QueryExpressionBodyBinary.class, new QueryExpressionBinaryProcessor());
        processors.put(QuerySpecification.class, new QuerySpecificationProcessor());
        processors.put(QueryExpression.class, new QueryExpressionProcessor());
        processors.put(EmptyQueryExpressionBody.class, new NoOpProcessor());
        processors.put(CorrespondingSpec.class, new CorrespondingSpecProcessor());
        processors.put(GrandTotal.class, new ConstantProcessor(SQLConstants.OPEN_PARENTHESIS
                + SQLConstants.CLOSE_PARENTHESIS));
        processors.put(OrdinaryGroupingSet.class, new OrdinaryGroupingSetProcessor());
        processors.put(SortSpecification.class, new SortSpecificationProcessor());
        processors.put(GroupByClause.class, new GroupByProcessor());
        processors.put(OrderByClause.class, new OrderByProcessor());
        processors.put(FromClause.class, new FromProcessor());
        final SelectColumnsProcessor selectProcessor = new SelectColumnsProcessor();
        processors.put(AsteriskSelect.class, selectProcessor);
        processors.put(ColumnReferences.class, selectProcessor);
        processors.put(TableValueConstructor.class, new TableValueConstructorProcessor());
        processors.put(RowDefinition.class, new RowDefinitionProcessor());
        processors.put(RowSubQuery.class, new RowSubQueryProcessor());
        processors.put(OffsetSpecification.class, new OffsetSpecificationProcessor());
        processors.put(LimitSpecification.class, new LimitSpecificationProcessor());

        // Table references
        processors.put(TableNameDirect.class, new TableNameDirectProcessor());
        processors.put(TableNameFunction.class, new TableNameFunctionProcessor());
        processors.put(TableReferenceByName.class, new TableReferenceByNameProcessor());
        processors
                .put(TableReferenceByExpression.class, new TableReferenceByExpressionProcessor());
        processors.put(CrossJoinedTable.class, new CrossJoinedTableProcessor());
        processors.put(NaturalJoinedTable.class, new NaturalJoinedTableProcessor());
        processors.put(QualifiedJoinedTable.class, new QualifiedJoinedTableProcessor());
        processors.put(UnionJoinedTable.class, new UnionJoinedTableProcessor());
        processors.put(JoinCondition.class, new JoinConditionProcessor());
        processors.put(NamedColumnsJoin.class, new NamedColumnsJoinProcessor());

        // Modification clauses
        processors.put(ColumnSourceByQuery.class, new ColumnSourceByQueryProcessor());
        processors.put(ColumnSourceByValues.class, new ColumnSourceByValuesProcessor());
        processors.put(DeleteBySearch.class, new DeleteBySearchProcessor());
        processors.put(InsertStatement.class, new InsertStatementProcessor());
        processors.put(SetClause.class, new SetClauseProcessor());
        processors.put(TargetTable.class, new TargetTableProcessor());
        processors.put(UpdateBySearch.class, new UpdateBySearchProcessor());
        processors.put(UpdateSourceByExpression.class, new UpdateSourceByExpressionProcessor());
        processors.put(ValueSource.Null.class, new ConstantProcessor("NULL"));
        processors.put(ValueSource.Default.class, new ConstantProcessor("DEFAULT"));
        processors.put(Defaults.class, new ConstantProcessor(SQLConstants.TOKEN_SEPARATOR
                + "DEFAULT VALUES"));

        // Data definition
        // First data types
        processors.put(BigInt.class, new ConstantProcessor("BIGINT"));
        processors.put(DoublePrecision.class, new ConstantProcessor("DOUBLE PRECISION"));
        processors.put(Real.class, new ConstantProcessor("REAL"));
        processors.put(SmallInt.class, new ConstantProcessor("SMALLINT"));
        processors.put(SQLBoolean.class, new ConstantProcessor("BOOLEAN"));
        processors.put(SQLDate.class, new ConstantProcessor("DATE"));
        processors.put(SQLInteger.class, new ConstantProcessor("INTEGER"));
        processors.put(UserDefinedType.class, new UserDefinedDataTypeProcessor());
        processors.put(Decimal.class, new DecimalProcessor());
        processors.put(Numeric.class, new NumericProcessor());
        processors.put(SQLChar.class, new SQLCharProcessor());
        processors.put(SQLFloat.class, new SQLFloatProcessor());
        processors.put(SQLInterval.class, new SQLIntervalProcessor());
        processors.put(SQLTime.class, new SQLTimeProcessor());
        processors.put(SQLTimeStamp.class, new SQLTimeStampProcessor());
        // Then statements and clauses
        processors.put(SchemaDefinition.class, new SchemaDefinitionProcessor());
        processors.put(TableDefinition.class, new TableDefinitionProcessor());
        processors.put(TableElementList.class, new TableElementListProcessor());
        processors.put(ColumnDefinition.class, new ColumnDefinitionProcessor());
        processors.put(LikeClause.class, new LikeClauseProcessor());
        processors.put(TableConstraintDefinition.class, new TableConstraintDefinitionProcessor());
        processors.put(CheckConstraint.class, new CheckConstraintProcessor());
        processors.put(UniqueConstraint.class, new UniqueConstraintProcessor());
        processors.put(ForeignKeyConstraint.class, new ForeignKeyConstraintProcessor());
        processors.put(ViewDefinition.class, new ViewDefinitionProcessor());
        processors.put(RegularViewSpecification.class, new RegularViewSpecificationProcessor());

        // Data manipulation
        processors.put(AlterTableStatement.class, new AlterTableStatementProcessor());
        processors.put(AddColumnDefinition.class, new AddColumnDefinitionProcessor());
        processors.put(AddTableConstraintDefinition.class,
                new AddTableConstraintDefinitionProcessor());
        processors.put(AlterColumnDefinition.class, new AlterColumnDefinitionProcessor());
        processors.put(DropDefault.class, new DropColumnDefaultProcessor());
        processors.put(SetColumnDefault.class, new SetColumnDefaultProcessor());
        processors.put(DropColumnDefinition.class, new DropColumnDefinitionProcessor());
        processors.put(DropTableConstraintDefinition.class,
                new DropTableConstraintDefinitionProcessor());
        processors.put(DropSchemaStatement.class, new DropSchemaStatementProcessor());
        processors.put(DropTableOrViewStatement.class, new DropTableOrViewStatementProcessor());

        _defaultProcessors = Collections.unmodifiableMap(processors);
    }

    private final Map<Class<? extends Typeable<?>>, SQLProcessor> _processors;
    private final SQLVendor _vendor;

    public DefaultSQLProcessor(final SQLVendor vendor) {
        this(vendor, DefaultSQLProcessor._defaultProcessors);
    }

    public DefaultSQLProcessor(final SQLVendor vendor,
                               final Map<Class<? extends Typeable<?>>, SQLProcessor> processors) {
        NullArgumentException.validateNotNull("Vendor", vendor);
        NullArgumentException.validateNotNull("Processors", processors);

        this._vendor = vendor;
        this._processors = new HashMap<>(processors);
    }

    @Override
    public void process(final Typeable<?> object, final StringBuilder builder) {
        final SQLProcessor processor = this._processors.get(object.getImplementedType());
        if (processor != null) {
            processor.process(this, object, builder);
        } else {
            throw new UnsupportedElementException("The vendor " + this.getClass()
                    + " does not know how to handle element of type " + object.getImplementedType()
                    + ".", object);
        }
    }

    @Override
    public SQLVendor getVendor() {
        return this._vendor;
    }

    protected Map<Class<? extends Typeable<?>>, SQLProcessor> getProcessors() {
        return this._processors;
    }

    public static Map<Class<? extends Typeable<?>>, SQLProcessor> getDefaultProcessors() {
        return DefaultSQLProcessor._defaultProcessors;
    }

    public static Map<Class<? extends BinaryPredicate>, String> getDefaultBinaryOperators() {
        return DefaultSQLProcessor._defaultBinaryOperators;
    }

    public static Map<Class<? extends MultiPredicate>, String> getDefaultMultiOperators() {
        return DefaultSQLProcessor._defaultMultiOperators;
    }

    public static Map<Class<? extends MultiPredicate>, String> getDefaultMultiSeparators() {
        return DefaultSQLProcessor._defaultMultiSeparators;
    }

    public static Map<Class<? extends MultiPredicate>, Boolean> getDefaultParenthesisPolicies() {
        return DefaultSQLProcessor._defaultParenthesisPolicies;
    }

    public static Map<Class<? extends UnaryPredicate>, String> getDefaultUnaryOperators() {
        return DefaultSQLProcessor._defaultUnaryOperators;
    }

    public static Map<Class<? extends UnaryPredicate>, UnaryOperatorOrientation>
    getDefaultUnaryOrientations() {
        return DefaultSQLProcessor._defaultUnaryOrientations;
    }

}

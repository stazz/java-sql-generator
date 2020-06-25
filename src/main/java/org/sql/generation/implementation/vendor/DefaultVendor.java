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

package org.sql.generation.implementation.vendor;

import org.atp.api.Typeable;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.SQLStatement;
import org.sql.generation.api.grammar.factories.*;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.factories.*;
import org.sql.generation.implementation.transformation.DefaultSQLProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class DefaultVendor
        implements SQLVendor {

    protected interface ProcessorCallback {
        SQLProcessorAggregator get(SQLVendor vendor);
    }

    protected interface Callback<T> {
        T get(SQLVendor vendor, SQLProcessorAggregator processor);
    }

    protected static final Callback<BooleanFactory> BOOLEAN_FACTORY = DefaultBooleanFactory::new;

    protected static final Callback<ColumnsFactory> COLUMNS_FACTORY = DefaultColumnsFactory::new;
    
    protected static final Callback<LiteralFactory> LITERAL_FACTORY = DefaultLiteralFactory::new;

    protected static final Callback<ModificationFactory> MODIFICATION_FACTORY = DefaultModificationFactory::new;

    protected static final Callback<QueryFactory> QUERY_FACTORY = DefaultQueryFactory::new;

    protected static final Callback<TableReferenceFactory> TABLE_REFERENCE_FACTORY = DefaultTableRefFactory::new;

    protected static final Callback<DefinitionFactory> DEFINITION_FACTORY = DefaultDefinitionFactory::new;

    protected static final Callback<ManipulationFactory> MANIPULATION_FACTORY = DefaultManipulationFactory::new;

    protected static final Callback<DataTypeFactory> DATA_TYPE_FACTORY = DefaultDataTypeFactory::new;

    protected static final ProcessorCallback DEFAULT_PROCESSOR = DefaultSQLProcessor::new;

    private final QueryFactory _queryFactory;

    private final BooleanFactory _booleanFactory;

    private final TableReferenceFactory _fromFactory;

    private final LiteralFactory _literalFactory;

    private final ColumnsFactory _columnsFactory;

    private final ModificationFactory _modificationFactory;

    private final DefinitionFactory _definitionFactory;

    private final ManipulationFactory _manipulationFactory;

    private final DataTypeFactory _dataTypeFactory;

    private final SQLProcessorAggregator _processor;

    public DefaultVendor() {
        this(DefaultVendor.DEFAULT_PROCESSOR);
    }

    protected DefaultVendor(final ProcessorCallback processor) {
        this(processor, DefaultVendor.BOOLEAN_FACTORY, DefaultVendor.COLUMNS_FACTORY, DefaultVendor.LITERAL_FACTORY, DefaultVendor.MODIFICATION_FACTORY, DefaultVendor.QUERY_FACTORY,
                DefaultVendor.TABLE_REFERENCE_FACTORY, DefaultVendor.DEFINITION_FACTORY, DefaultVendor.MANIPULATION_FACTORY, DefaultVendor.DATA_TYPE_FACTORY);
    }

    protected DefaultVendor(final ProcessorCallback processor, final Callback<? extends BooleanFactory> booleanFactory,
                            final Callback<? extends ColumnsFactory> columnsFactory, final Callback<? extends LiteralFactory> literalFactory,
                            final Callback<? extends ModificationFactory> modificationFactory, final Callback<? extends QueryFactory> queryFactory,
                            final Callback<? extends TableReferenceFactory> tableReferenceFactory,
                            final Callback<? extends DefinitionFactory> definitionFactory,
                            final Callback<? extends ManipulationFactory> manipulationFactory, final Callback<? extends DataTypeFactory> dataTypeFactory) {
        NullArgumentException.validateNotNull("processor", processor);

        this._processor = processor.get(this);
        this._booleanFactory = booleanFactory.get(this, this._processor);
        this._columnsFactory = columnsFactory.get(this, this._processor);
        this._literalFactory = literalFactory.get(this, this._processor);
        this._queryFactory = queryFactory.get(this, this._processor);
        this._modificationFactory = modificationFactory.get(this, this._processor);
        this._fromFactory = tableReferenceFactory.get(this, this._processor);
        this._definitionFactory = definitionFactory.get(this, this._processor);
        this._manipulationFactory = manipulationFactory.get(this, this._processor);
        this._dataTypeFactory = dataTypeFactory.get(this, this._processor);
    }

    /**
     * Note that exactly one string builder is allocated for each statement.
     */
    @Override
    public String toString(final SQLStatement statement) {
        final StringBuilder builder = new StringBuilder();
        this._processor.process((Typeable<?>) statement, builder);
        return builder.toString();
    }

    @Override
    public QueryFactory getQueryFactory() {
        return this._queryFactory;
    }

    @Override
    public BooleanFactory getBooleanFactory() {
        return this._booleanFactory;
    }

    @Override
    public TableReferenceFactory getTableReferenceFactory() {
        return this._fromFactory;
    }

    @Override
    public LiteralFactory getLiteralFactory() {
        return this._literalFactory;
    }

    @Override
    public ColumnsFactory getColumnsFactory() {
        return this._columnsFactory;
    }

    @Override
    public ModificationFactory getModificationFactory() {
        return this._modificationFactory;
    }

    @Override
    public DefinitionFactory getDefinitionFactory() {
        return this._definitionFactory;
    }

    @Override
    public ManipulationFactory getManipulationFactory() {
        return this._manipulationFactory;
    }

    @Override
    public DataTypeFactory getDataTypeFactory() {
        return this._dataTypeFactory;
    }

    protected SQLProcessorAggregator getProcessor() {
        return this._processor;
    }
}

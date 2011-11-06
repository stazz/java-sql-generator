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
import org.sql.generation.api.grammar.factories.BooleanFactory;
import org.sql.generation.api.grammar.factories.ColumnsFactory;
import org.sql.generation.api.grammar.factories.DataTypeFactory;
import org.sql.generation.api.grammar.factories.DefinitionFactory;
import org.sql.generation.api.grammar.factories.LiteralFactory;
import org.sql.generation.api.grammar.factories.ManipulationFactory;
import org.sql.generation.api.grammar.factories.ModificationFactory;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.factories.TableReferenceFactory;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.factories.DefaultBooleanFactory;
import org.sql.generation.implementation.grammar.factories.DefaultColumnsFactory;
import org.sql.generation.implementation.grammar.factories.DefaultDataTypeFactory;
import org.sql.generation.implementation.grammar.factories.DefaultDefinitionFactory;
import org.sql.generation.implementation.grammar.factories.DefaultLiteralFactory;
import org.sql.generation.implementation.grammar.factories.DefaultManipulationFactory;
import org.sql.generation.implementation.grammar.factories.DefaultModificationFactory;
import org.sql.generation.implementation.grammar.factories.DefaultQueryFactory;
import org.sql.generation.implementation.grammar.factories.DefaultTableRefFactory;
import org.sql.generation.implementation.transformation.DefaultSQLProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultVendor
    implements SQLVendor
{

    protected static interface ProcessorCallback
    {
        public SQLProcessorAggregator get( SQLVendor vendor );
    }

    protected static interface Callback<T>
    {
        public T get( SQLVendor vendor, SQLProcessorAggregator processor );
    }

    protected static final Callback<BooleanFactory> BOOLEAN_FACTORY = new Callback<BooleanFactory>()
    {
        public BooleanFactory get( SQLVendor vendor, SQLProcessorAggregator processor )
        {
            return new DefaultBooleanFactory( vendor, processor );
        }
    };

    protected static final Callback<ColumnsFactory> COLUMNS_FACTORY = new Callback<ColumnsFactory>()
    {
        public ColumnsFactory get( SQLVendor vendor, SQLProcessorAggregator processor )
        {
            return new DefaultColumnsFactory( vendor, processor );
        }
    };
    protected static final Callback<LiteralFactory> LITERAL_FACTORY = new Callback<LiteralFactory>()
    {
        public LiteralFactory get( SQLVendor vendor, SQLProcessorAggregator processor )
        {
            return new DefaultLiteralFactory( vendor, processor );
        }
    };

    protected static final Callback<ModificationFactory> MODIFICATION_FACTORY = new Callback<ModificationFactory>()
    {
        public ModificationFactory get( SQLVendor vendor, SQLProcessorAggregator processor )
        {
            return new DefaultModificationFactory( vendor, processor );
        }
    };

    protected static final Callback<QueryFactory> QUERY_FACTORY = new Callback<QueryFactory>()
    {
        public QueryFactory get( SQLVendor vendor, SQLProcessorAggregator processor )
        {
            return new DefaultQueryFactory( vendor, processor );
        }
    };

    protected static final Callback<TableReferenceFactory> TABLE_REFERENCE_FACTORY = new Callback<TableReferenceFactory>()
    {
        public TableReferenceFactory get( SQLVendor vendor, SQLProcessorAggregator processor )
        {
            return new DefaultTableRefFactory( vendor, processor );
        }
    };

    protected static final Callback<DefinitionFactory> DEFINITION_FACTORY = new Callback<DefinitionFactory>()
    {
        public DefinitionFactory get( SQLVendor vendor, SQLProcessorAggregator processor )
        {
            return new DefaultDefinitionFactory( vendor, processor );
        }
    };

    protected static final Callback<ManipulationFactory> MANIPULATION_FACTORY = new Callback<ManipulationFactory>()
    {
        public ManipulationFactory get( SQLVendor vendor, SQLProcessorAggregator processor )
        {
            return new DefaultManipulationFactory( vendor, processor );
        }
    };

    protected static final Callback<DataTypeFactory> DATA_TYPE_FACTORY = new Callback<DataTypeFactory>()
    {
        public DataTypeFactory get( SQLVendor vendor, SQLProcessorAggregator processor )
        {
            return new DefaultDataTypeFactory( vendor, processor );
        }
    };

    protected static final ProcessorCallback DEFAULT_PROCESSOR = new ProcessorCallback()
    {
        public SQLProcessorAggregator get( SQLVendor vendor )
        {
            return new DefaultSQLProcessor( vendor );
        }
    };

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

    public DefaultVendor()
    {
        this( DEFAULT_PROCESSOR );
    }

    protected DefaultVendor( ProcessorCallback processor )
    {
        this( processor, BOOLEAN_FACTORY, COLUMNS_FACTORY, LITERAL_FACTORY, MODIFICATION_FACTORY, QUERY_FACTORY,
            TABLE_REFERENCE_FACTORY, DEFINITION_FACTORY, MANIPULATION_FACTORY, DATA_TYPE_FACTORY );
    }

    protected DefaultVendor( ProcessorCallback processor, Callback<? extends BooleanFactory> booleanFactory,
        Callback<? extends ColumnsFactory> columnsFactory, Callback<? extends LiteralFactory> literalFactory,
        Callback<? extends ModificationFactory> modificationFactory, Callback<? extends QueryFactory> queryFactory,
        Callback<? extends TableReferenceFactory> tableReferenceFactory,
        Callback<? extends DefinitionFactory> definitionFactory,
        Callback<? extends ManipulationFactory> manipulationFactory, Callback<? extends DataTypeFactory> dataTypeFactory )
    {
        NullArgumentException.validateNotNull( "processor", processor );

        this._processor = processor.get( this );
        this._booleanFactory = booleanFactory.get( this, this._processor );
        this._columnsFactory = columnsFactory.get( this, this._processor );
        this._literalFactory = literalFactory.get( this, this._processor );
        this._queryFactory = queryFactory.get( this, this._processor );
        this._modificationFactory = modificationFactory.get( this, this._processor );
        this._fromFactory = tableReferenceFactory.get( this, this._processor );
        this._definitionFactory = definitionFactory.get( this, this._processor );
        this._manipulationFactory = manipulationFactory.get( this, this._processor );
        this._dataTypeFactory = dataTypeFactory.get( this, this._processor );
    }

    /**
     * Note that exactly one string builder is allocated for each statement.
     */
    public String toString( SQLStatement statement )
    {
        StringBuilder builder = new StringBuilder();
        this._processor.process( (Typeable<?>) statement, builder );
        return builder.toString();
    }

    public QueryFactory getQueryFactory()
    {
        return this._queryFactory;
    }

    public BooleanFactory getBooleanFactory()
    {
        return this._booleanFactory;
    }

    public TableReferenceFactory getTableReferenceFactory()
    {
        return this._fromFactory;
    }

    public LiteralFactory getLiteralFactory()
    {
        return this._literalFactory;
    }

    public ColumnsFactory getColumnsFactory()
    {
        return this._columnsFactory;
    }

    public ModificationFactory getModificationFactory()
    {
        return this._modificationFactory;
    }

    public DefinitionFactory getDefinitionFactory()
    {
        return this._definitionFactory;
    }

    public ManipulationFactory getManipulationFactory()
    {
        return this._manipulationFactory;
    }

    public DataTypeFactory getDataTypeFactory()
    {
        return this._dataTypeFactory;
    }

    protected SQLProcessorAggregator getProcessor()
    {
        return this._processor;
    }
}

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
import org.sql.generation.api.grammar.factories.LiteralFactory;
import org.sql.generation.api.grammar.factories.ModificationFactory;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.factories.TableReferenceFactory;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.factories.DefaultBooleanFactory;
import org.sql.generation.implementation.grammar.factories.DefaultColumnsFactory;
import org.sql.generation.implementation.grammar.factories.DefaultLiteralFactory;
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

    protected static interface Callback<T>
    {
        public T get( SQLVendor vendor );
    }

    protected static final Callback<BooleanFactory> BOOLEAN_FACTORY = new Callback<BooleanFactory>()
    {
        public BooleanFactory get( SQLVendor vendor )
        {
            return new DefaultBooleanFactory();
        }
    };

    protected static final Callback<ColumnsFactory> COLUMNS_FACTORY = new Callback<ColumnsFactory>()
    {
        public ColumnsFactory get( SQLVendor vendor )
        {
            return new DefaultColumnsFactory();
        }
    };
    protected static final Callback<LiteralFactory> LITERAL_FACTORY = new Callback<LiteralFactory>()
    {
        public LiteralFactory get( SQLVendor vendor )
        {
            return new DefaultLiteralFactory();
        }
    };

    protected static final Callback<ModificationFactory> MODIFICATION_FACTORY = new Callback<ModificationFactory>()
    {
        public ModificationFactory get( SQLVendor vendor )
        {
            return new DefaultModificationFactory( vendor );
        }
    };

    protected static final Callback<QueryFactory> QUERY_FACTORY = new Callback<QueryFactory>()
    {
        public QueryFactory get( SQLVendor vendor )
        {
            return new DefaultQueryFactory( vendor );
        }
    };

    protected static final Callback<TableReferenceFactory> TABLE_REFERENCE_FACTORY = new Callback<TableReferenceFactory>()
    {
        public TableReferenceFactory get( SQLVendor vendor )
        {
            return new DefaultTableRefFactory( vendor );
        }
    };

    private final QueryFactory _queryFactory;

    private final BooleanFactory _booleanFactory;

    private final TableReferenceFactory _fromFactory;

    private final LiteralFactory _literalFactory;

    private final ColumnsFactory _columnsFactory;

    private final ModificationFactory _modificationFactory;

    private final SQLProcessorAggregator _processor;

    public DefaultVendor()
    {
        this( new DefaultSQLProcessor() );
    }

    protected DefaultVendor( SQLProcessorAggregator processor )
    {
        this( processor, BOOLEAN_FACTORY, COLUMNS_FACTORY, LITERAL_FACTORY, MODIFICATION_FACTORY, QUERY_FACTORY,
            TABLE_REFERENCE_FACTORY );
    }

    protected DefaultVendor( SQLProcessorAggregator processor, Callback<? extends BooleanFactory> booleanFactory,
        Callback<? extends ColumnsFactory> columnsFactory, Callback<? extends LiteralFactory> literalFactory,
        Callback<? extends ModificationFactory> modificationFactory, Callback<? extends QueryFactory> queryFactory,
        Callback<? extends TableReferenceFactory> tableReferenceFactory )
    {
        NullArgumentException.validateNotNull( "processor", processor );

        this._processor = processor;
        this._booleanFactory = booleanFactory.get( this );
        this._columnsFactory = columnsFactory.get( this );
        this._literalFactory = literalFactory.get( this );
        this._queryFactory = queryFactory.get( this );
        this._modificationFactory = modificationFactory.get( this );
        this._fromFactory = tableReferenceFactory.get( this );
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
}

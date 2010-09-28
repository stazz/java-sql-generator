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

import org.lwdci.api.context.single.Typeable;
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
import org.sql.generation.implementation.transformation.SQLStatementProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultVendor
    implements SQLVendor
{

    private QueryFactory _queryFactory;

    private BooleanFactory _booleanFactory;

    private TableReferenceFactory _fromFactory;

    private LiteralFactory _literalFactory;

    private ColumnsFactory _columnsFactory;

    private ModificationFactory _modificationFactory;

    public DefaultVendor()
    {
        this._booleanFactory = new DefaultBooleanFactory();
        this._columnsFactory = new DefaultColumnsFactory();
        this._fromFactory = new DefaultTableRefFactory( this );
        this._literalFactory = new DefaultLiteralFactory();
        this._queryFactory = new DefaultQueryFactory( this );
        this._modificationFactory = new DefaultModificationFactory( this );
    }

    @Override
    public String toString( SQLStatement statement )
    {
        StringBuilder builder = new StringBuilder();
        this.getProcessor().process( (Typeable<?>) statement, builder );
        return builder.toString();
    }

    protected SQLProcessorAggregator getProcessor()
    {
        return new SQLStatementProcessor();
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

    protected void setQueryFactory( QueryFactory queryFactory )
    {
        this._queryFactory = queryFactory;
    }

    protected void setBooleanFactory( BooleanFactory booleanFactory )
    {
        this._booleanFactory = booleanFactory;
    }

    protected void setFromFactory( TableReferenceFactory fromFactory )
    {
        this._fromFactory = fromFactory;
    }

    protected void setLiteralFactory( LiteralFactory literalFactory )
    {
        this._literalFactory = literalFactory;
    }

    protected void setColumnsFactory( ColumnsFactory columnsFactory )
    {
        this._columnsFactory = columnsFactory;
    }

    protected void setModificationFactory( ModificationFactory modificationFactory )
    {
        this._modificationFactory = modificationFactory;
    }
}

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

package org.sql.generation.implementation.grammar.builders.modification;

import org.sql.generation.api.grammar.builders.modification.InsertStatementBuilder;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.modification.ColumnSource;
import org.sql.generation.api.grammar.modification.InsertStatement;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.modification.InsertStatementImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class InsertStatementBuilderImpl extends SQLBuilderBase
    implements InsertStatementBuilder
{

    private TableNameDirect _tableName;

    private ColumnSource _columnSource;

    public InsertStatementBuilderImpl( SQLProcessorAggregator processor )
    {
        super( processor );
    }

    public InsertStatement createExpression()
    {
        return new InsertStatementImpl( this.getProcessor(), this._tableName, this._columnSource );
    }

    public InsertStatementBuilder setTableName( TableNameDirect tableName )
    {
        this._tableName = tableName;
        return this;
    }

    public TableNameDirect getTableName()
    {
        return this._tableName;
    }

    public InsertStatementBuilder setColumnSource( ColumnSource source )
    {
        this._columnSource = source;
        return this;
    }

    public ColumnSource getColumnSource()
    {
        return this._columnSource;
    }

}

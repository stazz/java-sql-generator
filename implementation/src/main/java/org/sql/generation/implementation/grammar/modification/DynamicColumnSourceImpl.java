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

package org.sql.generation.implementation.grammar.modification;

import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.modification.ColumnSource;
import org.sql.generation.api.grammar.modification.DynamicColumnSource;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class DynamicColumnSourceImpl<ColumnSourceType extends DynamicColumnSource> extends
    SQLSyntaxElementBase<ColumnSource, ColumnSourceType>
    implements DynamicColumnSource
{

    private final ColumnNameList _columnNames;

    protected DynamicColumnSourceImpl( SQLProcessorAggregator processor,
        Class<? extends ColumnSourceType> expressionClass, ColumnNameList columnNames )
    {
        super( processor, expressionClass );

        this._columnNames = columnNames;
    }

    public ColumnNameList getColumnNames()
    {
        return this._columnNames;
    }

    @Override
    protected boolean doesEqual( ColumnSourceType another )
    {
        return (this._columnNames != null && this._columnNames.equals( another.getColumnNames() ))
            || (this._columnNames == null && another.getColumnNames() == null);
    }

}

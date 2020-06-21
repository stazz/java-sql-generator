/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
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

package org.sql.generation.implementation.grammar.common;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author 2011 Stanislav Muhametsin
 */
public class TableNameImpl<TableNameType extends TableName> extends SQLSyntaxElementBase<TableName, TableNameType>
        implements TableName {
    private final String _schemaName;

    protected TableNameImpl(final SQLProcessorAggregator processor, final Class<? extends TableNameType> implClass,
                            final String schemaName) {
        super(processor, implClass);

        this._schemaName = schemaName;
    }

    @Override
    public String getSchemaName() {
        return this._schemaName;
    }

    @Override
    protected boolean doesEqual(final TableNameType another) {
        return TypeableImpl.bothNullOrEquals(this._schemaName, another.getSchemaName());
    }
}

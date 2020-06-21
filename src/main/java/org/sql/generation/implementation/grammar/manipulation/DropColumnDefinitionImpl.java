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

package org.sql.generation.implementation.grammar.manipulation;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;
import org.sql.generation.api.grammar.manipulation.DropBehaviour;
import org.sql.generation.api.grammar.manipulation.DropColumnDefinition;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class DropColumnDefinitionImpl extends SQLSyntaxElementBase<AlterTableAction, DropColumnDefinition>
        implements DropColumnDefinition {

    private final String _columnName;
    private final DropBehaviour _dropBehaviour;

    public DropColumnDefinitionImpl(final SQLProcessorAggregator processor, final String columnName, final DropBehaviour dropBehaviour) {
        this(processor, DropColumnDefinition.class, columnName, dropBehaviour);
    }

    protected DropColumnDefinitionImpl(final SQLProcessorAggregator processor,
                                       final Class<? extends DropColumnDefinition> realImplementingType, final String columnName, final DropBehaviour dropBehaviour) {
        super(processor, realImplementingType);

        NullArgumentException.validateNotNull("Column name", columnName);
        NullArgumentException.validateNotNull("Drop behaviour", dropBehaviour);

        this._columnName = columnName;
        this._dropBehaviour = dropBehaviour;
    }

    @Override
    protected boolean doesEqual(final DropColumnDefinition another) {
        return this._dropBehaviour.equals(another.getDropBehaviour())
                && this._columnName.equals(another.getColumnName());
    }

    @Override
    public String getColumnName() {
        return this._columnName;
    }

    @Override
    public DropBehaviour getDropBehaviour() {
        return this._dropBehaviour;
    }

}

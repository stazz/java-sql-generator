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
import org.sql.generation.api.grammar.manipulation.AlterColumnAction;
import org.sql.generation.api.grammar.manipulation.AlterColumnDefinition;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class AlterColumnDefinitionImpl extends SQLSyntaxElementBase<AlterTableAction, AlterColumnDefinition>
        implements AlterColumnDefinition {

    private final String _columnName;
    private final AlterColumnAction _action;

    public AlterColumnDefinitionImpl(final SQLProcessorAggregator processor, final String columnName, final AlterColumnAction action) {
        this(processor, AlterColumnDefinition.class, columnName, action);
    }

    protected AlterColumnDefinitionImpl(final SQLProcessorAggregator processor,
                                        final Class<? extends AlterColumnDefinition> realImplementingType, final String columnName, final AlterColumnAction action) {
        super(processor, realImplementingType);

        NullArgumentException.validateNotNull("Column name", columnName);
        NullArgumentException.validateNotNull("Alter column action", action);

        this._columnName = columnName;
        this._action = action;
    }

    @Override
    protected boolean doesEqual(final AlterColumnDefinition another) {
        return this._columnName.equals(another.getColumnName()) && this._action.equals(another.getAction());
    }

    @Override
    public AlterColumnAction getAction() {
        return this._action;
    }

    @Override
    public String getColumnName() {
        return this._columnName;
    }

}

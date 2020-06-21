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
import org.sql.generation.api.grammar.common.SchemaStatement;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.manipulation.AlterTableAction;
import org.sql.generation.api.grammar.manipulation.AlterTableStatement;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class AlterTableStatementImpl extends SQLSyntaxElementBase<SchemaStatement, AlterTableStatement>
        implements AlterTableStatement {

    private final TableNameDirect _tableName;
    private final AlterTableAction _action;

    public AlterTableStatementImpl(final SQLProcessorAggregator processor, final TableNameDirect tableName, final AlterTableAction action) {
        this(processor, AlterTableStatement.class, tableName, action);
    }

    protected AlterTableStatementImpl(final SQLProcessorAggregator processor,
                                      final Class<? extends AlterTableStatement> realImplementingType, final TableNameDirect tableName, final AlterTableAction action) {
        super(processor, realImplementingType);

        NullArgumentException.validateNotNull("Table name", tableName);
        NullArgumentException.validateNotNull("Alter table taction", action);

        this._tableName = tableName;
        this._action = action;
    }

    @Override
    protected boolean doesEqual(final AlterTableStatement another) {
        return this._tableName.equals(another.getTableName()) && this._action.equals(another.getAction());
    }

    @Override
    public AlterTableAction getAction() {
        return this._action;
    }

    @Override
    public TableNameDirect getTableName() {
        return this._tableName;
    }

}

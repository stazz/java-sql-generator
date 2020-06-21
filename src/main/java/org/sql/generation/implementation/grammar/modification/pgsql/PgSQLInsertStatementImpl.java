/*
 * Copyright (c) 2012, Stanislav Muhametsin. All Rights Reserved.
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

package org.sql.generation.implementation.grammar.modification.pgsql;

import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.modification.ColumnSource;
import org.sql.generation.api.grammar.modification.pgsql.PgSQLInsertStatement;
import org.sql.generation.api.grammar.query.SelectColumnClause;
import org.sql.generation.implementation.grammar.modification.InsertStatementImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

public class PgSQLInsertStatementImpl extends InsertStatementImpl implements PgSQLInsertStatement {

    private final SelectColumnClause _returning;

    public PgSQLInsertStatementImpl(final SQLProcessorAggregator processor,
                                    final TableNameDirect tableName, final ColumnSource columnSource, final SelectColumnClause returning) {
        super(processor, PgSQLInsertStatement.class, tableName, columnSource);

        this._returning = returning;
    }

    @Override
    public SelectColumnClause getReturningClause() {
        return this._returning;
    }

}

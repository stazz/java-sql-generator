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

package org.sql.generation.api.grammar.builders.modification.pgsql;

import org.sql.generation.api.grammar.builders.modification.InsertStatementBuilder;
import org.sql.generation.api.grammar.query.SelectColumnClause;

/**
 * This build is used for creating {@code INSERT} statements specific for PostgreSQL databases.
 *
 * @author Stanislav Muhametsin
 */
public interface PgSQLInsertStatementBuilder extends InsertStatementBuilder {

    /**
     * Sets the {@code RETURNING} clause for this {@code INSERT} statement.
     *
     * @param clause The {@code RETURNING} clause. May be {@code null} if no {@code RETURNING}
     *               clause is desired.
     * @return This builder.
     */
    PgSQLInsertStatementBuilder setReturningClause(SelectColumnClause clause);
}

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

package org.sql.generation.implementation.transformation.pgsql;

import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.modification.InsertStatement;
import org.sql.generation.api.grammar.modification.pgsql.PgSQLInsertStatement;
import org.sql.generation.api.grammar.query.SelectColumnClause;
import org.sql.generation.implementation.transformation.ModificationProcessing.InsertStatementProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

public class ModificationProcessing {

    public static class PgSQLInsertStatementProcessor extends InsertStatementProcessor {
        @Override
        protected void doProcess(final SQLProcessorAggregator processor, final InsertStatement object,
                                 final StringBuilder builder) {
            super.doProcess(processor, object, builder);

            final SelectColumnClause returning = ((PgSQLInsertStatement) object).getReturningClause();
            if (returning != null) {
                builder.append(SQLConstants.NEWLINE).append("RETURNING ");
                processor.process(returning, builder);
            }
        }
    }
}

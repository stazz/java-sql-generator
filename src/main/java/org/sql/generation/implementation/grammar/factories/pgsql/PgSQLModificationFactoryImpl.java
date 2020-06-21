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

package org.sql.generation.implementation.grammar.factories.pgsql;

import org.sql.generation.api.grammar.builders.modification.InsertStatementBuilder;
import org.sql.generation.api.vendor.PostgreSQLVendor;
import org.sql.generation.implementation.grammar.builders.modification.pgsql.PgSQLInsertStatementBuilderImpl;
import org.sql.generation.implementation.grammar.factories.DefaultModificationFactory;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

public class PgSQLModificationFactoryImpl extends DefaultModificationFactory {

    public PgSQLModificationFactoryImpl(final PostgreSQLVendor vendor, final SQLProcessorAggregator processor) {
        super(vendor, processor);
    }

    @Override
    public InsertStatementBuilder insert() {
        return new PgSQLInsertStatementBuilderImpl(this.getProcessor());
    }
}

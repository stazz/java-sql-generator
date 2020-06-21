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

package org.sql.generation.implementation;

import org.junit.Test;
import org.sql.generation.api.grammar.builders.modification.pgsql.PgSQLInsertStatementBuilder;
import org.sql.generation.api.grammar.factories.*;
import org.sql.generation.api.grammar.modification.InsertStatement;
import org.sql.generation.api.grammar.modification.ValueSource;
import org.sql.generation.api.vendor.PostgreSQLVendor;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.api.vendor.SQLVendorProvider;

public class PostgreSQLModificationTest extends AbstractModificationTest {

    @Override
    protected SQLVendor loadVendor() throws Exception {
        return SQLVendorProvider.createVendor(PostgreSQLVendor.class);
    }

    @Test
    public void pgInsert1() {
        // INSERT INTO some_schema.some_table
        // VALUES (DEFAULT, "SomeString")
        // RETURNING id_column;
        final SQLVendor vendor = this.getVendor();

        final QueryFactory q = vendor.getQueryFactory();
        final TableReferenceFactory t = vendor.getTableReferenceFactory();
        final ModificationFactory m = vendor.getModificationFactory();
        final LiteralFactory l = vendor.getLiteralFactory();
        final ColumnsFactory c = vendor.getColumnsFactory();

        final InsertStatement insert =
                ((PgSQLInsertStatementBuilder) m.insert())
                        .setReturningClause(
                                q.columnsBuilder().addUnnamedColumns(c.colName("id_column"))
                                        .createExpression())
                        .setTableName(t.tableName("some_schema", "some_table"))
                        .setColumnSource(
                                m.columnSourceByValues()
                                        .addValues(ValueSource.Default.INSTANCE, l.s("SomeString"))
                                        .createExpression()
                        ).createExpression();

        this.logStatement("PGSQL table modification", vendor, insert);
    }

}

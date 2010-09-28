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

package org.sql.generation.implementation;

import org.junit.Test;
import org.sql.generation.api.grammar.builders.query.pgsql.PgSQLQuerySpecificationBuilder;
import org.sql.generation.api.grammar.factories.BooleanFactory;
import org.sql.generation.api.grammar.factories.ColumnsFactory;
import org.sql.generation.api.grammar.factories.LiteralFactory;
import org.sql.generation.api.grammar.factories.TableReferenceFactory;
import org.sql.generation.api.grammar.factories.pgsql.PgSQLQueryFactory;
import org.sql.generation.api.grammar.query.Ordering;
import org.sql.generation.api.vendor.PostgreSQLVendor;
import org.sql.generation.api.vendor.SQLVendorProvider;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class PostgreSQLQueryTest extends AbstractQueryTest
{

    @Override
    protected PostgreSQLVendor getVendor()
        throws Exception
    {
        return SQLVendorProvider.createVendor( PostgreSQLVendor.class );
    }

    @Test
    public void pgQuery1()
        throws Exception
    {
        // @formatter:off
        /*
          SELECT *
          FROM table
          WHERE table.value ~ ?
          ORDER BY 1 ASC
          LIMIT 6
          OFFSET 3
        */
        // @formatter:on

        PostgreSQLVendor vendor = this.getVendor();

        PgSQLQueryFactory q = vendor.getQueryFactory();
        BooleanFactory b = vendor.getBooleanFactory();
        TableReferenceFactory t = vendor.getTableReferenceFactory();
        LiteralFactory l = vendor.getLiteralFactory();
        ColumnsFactory c = vendor.getColumnsFactory();

        PgSQLQuerySpecificationBuilder query = q.querySpecificationBuilder();
        query.getSelect().selectAll();
        query.getFrom().addTableReferences( t.tableBuilder( t.table( t.tableName( "table" ) ) ) );
        query.getWhere().reset( b.regexp( c.colName( "table", "value" ), l.param() ) );
        query.limit( q.limit( 6 ) ).offset( q.offset( 3 ) );
        query.getOrderBy().addSortSpecs( q.sortSpec( l.n( 1 ), Ordering.ASCENDING ) );

        this.logQuery( vendor, q.createQuery( query.createExpression() ) );

    }
}

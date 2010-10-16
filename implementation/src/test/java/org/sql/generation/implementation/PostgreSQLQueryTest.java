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
import org.sql.generation.api.grammar.factories.BooleanFactory;
import org.sql.generation.api.grammar.factories.ColumnsFactory;
import org.sql.generation.api.grammar.query.QueryExpression;
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


        PostgreSQLVendor vendor = this.getVendor();

        BooleanFactory b = vendor.getBooleanFactory();
        ColumnsFactory c = vendor.getColumnsFactory();
        
        QueryExpression query = vendor.getQueryFactory().simpleQueryBuilder()
            .select( "*" )
            .from( "table" )
            .where( b.regexp( c.colName( "table", "value" ), vendor.getLiteralFactory().param() ) )
            .orderByAsc( "1" )
            .limit( 6 )
            .offset( 3 )
            .createExpression();
        // @formatter:on

        this.logQuery( vendor, query );

    }
}

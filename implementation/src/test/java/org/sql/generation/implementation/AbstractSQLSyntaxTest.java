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

import org.junit.Assert;
import org.slf4j.LoggerFactory;
import org.sql.generation.api.grammar.common.SQLStatement;
import org.sql.generation.api.vendor.SQLVendor;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class AbstractSQLSyntaxTest
{

    protected void logStatement( String statementType, SQLVendor vendor, SQLStatement statement )
    {
        String stringStmt = vendor.toString( statement );
        LoggerFactory.getLogger( this.getClass().getName() ).info( statementType + ":" + "\n" + stringStmt + "\n" );

        Assert.assertEquals(
            "Strings must be same from both SQLVendor.toString(...) and statement.toString() methods.", stringStmt,
            statement.toString() );
    }

    protected abstract SQLVendor getVendor()
        throws Exception;
}

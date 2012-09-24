/*
 *  Copyright 2012 Paul Merlin.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package org.sql.generation.implementation.vendor.sqlite;

import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.api.vendor.SQLiteVendor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;
import org.sql.generation.implementation.transformation.sqlite.SQLiteProcessor;
import org.sql.generation.implementation.vendor.DefaultVendor;

public class SQLiteVendorImpl
        extends DefaultVendor
        implements SQLiteVendor
{

    protected static final ProcessorCallback SQLITE_PROCESSOR = new ProcessorCallback()
    {

        public SQLProcessorAggregator get( SQLVendor vendor )
        {
            return new SQLiteProcessor( vendor );
        }

    };

    public SQLiteVendorImpl()
    {
        super( SQLITE_PROCESSOR );
    }

}

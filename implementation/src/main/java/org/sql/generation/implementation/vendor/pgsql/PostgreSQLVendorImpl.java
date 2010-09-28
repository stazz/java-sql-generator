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

package org.sql.generation.implementation.vendor.pgsql;

import org.sql.generation.api.grammar.factories.pgsql.PgSQLQueryFactory;
import org.sql.generation.api.vendor.PostgreSQLVendor;
import org.sql.generation.implementation.grammar.factories.pgsql.PgSQLQueryFactoryImpl;
import org.sql.generation.implementation.vendor.DefaultVendor;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class PostgreSQLVendorImpl extends DefaultVendor
    implements PostgreSQLVendor
{
    public static final Class<? extends PostgreSQLVendor> PG_VENDOR_CLASS = PostgreSQLVendor.class;

    public PostgreSQLVendorImpl()
    {
        super( new PostgreSQLTransformationContextCreator( PG_VENDOR_CLASS ) );

        this.setQueryFactory( new PgSQLQueryFactoryImpl( this ) );
    }

    @Override
    public PgSQLQueryFactory getQueryFactory()
    {
        return (PgSQLQueryFactory) super.getQueryFactory();
    }

}

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

package org.sql.generation.implementation.transformation.spi;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.SQLStatement;
import org.sql.generation.api.vendor.SQLVendor;

/**
 * 
 * @author Stanislav Muhametsin
 */
public final class SQLTransformationContextCreationArgs
{

    private final SQLVendor _vendor;

    private final SQLStatement _statement;

    public SQLTransformationContextCreationArgs( SQLVendor vendor, SQLStatement statement )
    {
        NullArgumentException.validateNotNull( "SQL vendor", vendor );
        NullArgumentException.validateNotNull( "SQL statement", statement );

        this._vendor = vendor;
        this._statement = statement;
    }

    public SQLVendor getVendor()
    {
        return this._vendor;
    }

    public SQLStatement getSQLStatement()
    {
        return this._statement;
    }
}

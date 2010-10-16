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

package org.sql.generation.implementation.grammar.factories;

import org.sql.generation.api.grammar.common.datatypes.Decimal;
import org.sql.generation.api.grammar.common.datatypes.Numeric;
import org.sql.generation.api.grammar.common.datatypes.SQLChar;
import org.sql.generation.api.grammar.common.datatypes.SQLFloat;
import org.sql.generation.api.grammar.common.datatypes.SQLTime;
import org.sql.generation.api.grammar.common.datatypes.SQLTimeStamp;
import org.sql.generation.api.grammar.factories.DataTypeFactory;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class AbstractDataTypeFactory
    implements DataTypeFactory
{

    public Decimal decimal()
    {
        return this.decimal( null, null );
    }

    public Decimal decimal( Integer precision )
    {
        return this.decimal( precision, null );
    }

    public Numeric numeric()
    {
        return this.numeric( null, null );
    }

    public Numeric numeric( Integer precision )
    {
        return this.numeric( precision, null );
    }

    public SQLChar sqlChar()
    {
        return this.sqlChar( null );
    }

    public SQLFloat sqlFloat()
    {
        return this.sqlFloat( null );
    }

    public SQLChar sqlVarChar()
    {
        return this.sqlVarChar( null );
    }

    public SQLTime time()
    {
        return this.time( null, null );
    }

    public SQLTime time( Boolean withTimeZone )
    {
        return this.time( null, withTimeZone );
    }

    public SQLTime time( Integer precision )
    {
        return this.time( precision, null );
    }

    public SQLTimeStamp timeStamp()
    {
        return this.timeStamp( null, null );
    }

    public SQLTimeStamp timeStamp( Boolean withTimeZone )
    {
        return this.timeStamp( null, withTimeZone );
    }

    public SQLTimeStamp timeStamp( Integer precision )
    {
        return this.timeStamp( precision, null );
    }
}

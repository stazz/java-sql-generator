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

package org.sql.generation.implementation.grammar.literals;

import org.sql.generation.api.grammar.literals.NumericLiteral;
import org.sql.generation.implementation.grammar.common.NonBooleanExpressionImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class NumericLiteralImpl extends NonBooleanExpressionImpl<NumericLiteral>
    implements NumericLiteral
{

    private final Number _number;

    public NumericLiteralImpl( Number number )
    {
        this( NumericLiteral.class, number );
    }

    protected NumericLiteralImpl( Class<? extends NumericLiteral> implClass, Number number )
    {
        super( implClass );

        this._number = number;
    }

    public Number getNumber()
    {
        return this._number;
    }
}

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

package org.sql.generation.api.grammar.factories;

import java.sql.Date;

import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.literals.DateTimeLiteral;
import org.sql.generation.api.grammar.literals.DirectLiteral;
import org.sql.generation.api.grammar.literals.NumericLiteral;
import org.sql.generation.api.grammar.literals.SQLFunctionLiteral;
import org.sql.generation.api.grammar.literals.StringLiteral;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface LiteralFactory
{

    public StringLiteral s( String stringContents );

    public DirectLiteral param();

    public DirectLiteral d( String literalContents );

    public DateTimeLiteral dt( Date date );

    public NumericLiteral n( Number number );

    public SQLFunctionLiteral func( String name, ValueExpression... parameters );

}

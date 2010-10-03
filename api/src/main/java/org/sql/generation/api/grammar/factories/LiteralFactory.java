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
import org.sql.generation.api.vendor.SQLVendor;

/**
 * A factory to create various literal expressions. This factory is obtainable from {@link SQLVendor}.
 * 
 * @author Stanislav Muhametsin
 * @see SQLVendor
 */
public interface LiteralFactory
{

    /**
     * Creates new string literal, that is, a string to be enclosed in {@code '} -characters.
     * 
     * @param stringContents The contents of string literal.
     * @return The new {@link StringLiteral}
     */
    public StringLiteral s( String stringContents );

    /**
     * Returns literal, representing a parameter ({@code ?}) in parameterized SQL statement.
     * 
     * @return The expression representing parameter ({@code ?}) in parameterized SQL statement.
     */
    public DirectLiteral param();

    /**
     * Creates a literal, which has some textual content. This content will be used <b>directly</b> when processing SQL
     * statement.
     * 
     * @param literalContents The contents to be used.
     * @return The new {@link DirectLiteral}.
     */
    public DirectLiteral l( String literalContents );

    /**
     * Creates a literal, which has some date as content.
     * 
     * @param date The date to use.
     * @return The new {@link DateTimeLiteral}.
     */
    public DateTimeLiteral dt( Date date );

    /**
     * Creates a literal, which has some number as contents.
     * 
     * @param number The number to use.
     * @return The new {@link NumericLiteral}.
     */
    public NumericLiteral n( Number number );

    /**
     * Creates a literal, which represents a use of SQL function.
     * 
     * @param name The name of function.
     * @param parameters The parameters for function.
     * @return The new {@link SQLFunctionLiteral}.
     */
    public SQLFunctionLiteral func( String name, ValueExpression... parameters );

}

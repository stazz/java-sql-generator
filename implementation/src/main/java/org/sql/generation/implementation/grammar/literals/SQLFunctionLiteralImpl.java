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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.literals.SQLFunctionLiteral;
import org.sql.generation.implementation.grammar.common.NonBooleanExpressionImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class SQLFunctionLiteralImpl extends NonBooleanExpressionImpl<SQLFunctionLiteral>
    implements SQLFunctionLiteral
{

    private final String _name;

    private final List<ValueExpression> _parameters;

    public SQLFunctionLiteralImpl( SQLProcessorAggregator processor, String name, ValueExpression... parameters )
    {
        this( processor, name, Arrays.asList( parameters ) );
    }

    public SQLFunctionLiteralImpl( SQLProcessorAggregator processor, String name, List<ValueExpression> parameters )
    {
        this( processor, SQLFunctionLiteral.class, name, parameters );
    }

    protected SQLFunctionLiteralImpl( SQLProcessorAggregator processor, Class<? extends SQLFunctionLiteral> implClass,
        String name, List<ValueExpression> parameters )
    {
        super( processor, implClass );
        NullArgumentException.validateNotNull( "name", name );
        NullArgumentException.validateNotNull( "parameters", parameters );
        for( ValueExpression exp : parameters )
        {
            NullArgumentException.validateNotNull( "parameter", exp );
        }

        this._name = name;
        this._parameters = Collections.unmodifiableList( new ArrayList<ValueExpression>( parameters ) );
    }

    public String getFunctionName()
    {
        return this._name;
    }

    public List<ValueExpression> getParameters()
    {
        return this._parameters;
    }

    @Override
    protected boolean doesEqual( SQLFunctionLiteral another )
    {
        return this._name.equals( another.getFunctionName() ) && this._parameters.equals( another.getParameters() );
    }
}

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

package org.sql.generation.implementation.grammar.query;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.query.Ordering;
import org.sql.generation.api.grammar.query.SortSpecification;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class SortSpecificationImpl extends SQLSyntaxElementBase<SortSpecification, SortSpecification>
    implements SortSpecification
{

    private final Ordering _ordering;

    private final ValueExpression _expression;

    public SortSpecificationImpl( SQLProcessorAggregator processor, ValueExpression expression, Ordering ordering )
    {
        this( processor, SortSpecification.class, expression, ordering );
    }

    protected SortSpecificationImpl( SQLProcessorAggregator processor, Class<? extends SortSpecification> implClass,
        ValueExpression expression, Ordering ordering )
    {
        super( processor, implClass );
        NullArgumentException.validateNotNull( "expression", expression );
        NullArgumentException.validateNotNull( "ordering", ordering );

        this._expression = expression;
        this._ordering = ordering;
    }

    public Ordering getOrderingSpecification()
    {
        return this._ordering;
    }

    public ValueExpression getValueExpression()
    {
        return this._expression;
    }

    @Override
    protected boolean doesEqual( SortSpecification another )
    {
        return this._ordering.equals( another.getOrderingSpecification() )
            && this._expression.equals( another.getOrderingSpecification() );
    }
}

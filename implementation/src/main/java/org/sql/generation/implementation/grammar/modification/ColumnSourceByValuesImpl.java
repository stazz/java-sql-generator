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

package org.sql.generation.implementation.grammar.modification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.modification.ColumnSourceByValues;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class ColumnSourceByValuesImpl extends DynamicColumnSourceImpl<ColumnSourceByValues>
    implements ColumnSourceByValues
{

    private final List<ValueExpression> _expressions;

    public ColumnSourceByValuesImpl( SQLProcessorAggregator processor, ColumnNameList columnNames,
        List<ValueExpression> expressions )
    {
        this( processor, ColumnSourceByValues.class, columnNames, expressions );
    }

    protected ColumnSourceByValuesImpl( SQLProcessorAggregator processor,
        Class<? extends ColumnSourceByValues> expressionClass, ColumnNameList columnNames,
        List<ValueExpression> expressions )
    {
        super( processor, expressionClass, columnNames );
        NullArgumentException.validateNotNull( "expressions", expressions );
        if( expressions.isEmpty() )
        {
            throw new IllegalArgumentException( "Empty column source list not allowed." );
        }

        this._expressions = Collections.unmodifiableList( new ArrayList<ValueExpression>( expressions ) );
    }

    public List<ValueExpression> getValues()
    {
        return this._expressions;
    }

}

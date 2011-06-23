/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
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

import java.util.Collections;
import java.util.List;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.query.RowDefinition;
import org.sql.generation.api.grammar.query.RowValueConstructor;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class RowDefinitionImpl extends TypeableImpl<RowValueConstructor, RowDefinition>
    implements RowDefinition
{

    private List<ValueExpression> _rowElements;

    public RowDefinitionImpl( List<ValueExpression> rowElements )
    {
        this( RowDefinition.class, rowElements );
    }

    /**
     * @param realImplementingType
     */
    protected RowDefinitionImpl( Class<? extends RowDefinition> realImplementingType, List<ValueExpression> rowElements )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "row elements", rowElements );

        this._rowElements = Collections.unmodifiableList( rowElements );
    }

    public List<ValueExpression> getRowElements()
    {
        return this._rowElements;
    }

    @Override
    protected boolean doesEqual( RowDefinition another )
    {
        return this._rowElements.equals( another.getRowElements() );
    }

}

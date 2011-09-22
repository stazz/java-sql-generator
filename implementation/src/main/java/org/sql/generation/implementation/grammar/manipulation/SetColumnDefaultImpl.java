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

package org.sql.generation.implementation.grammar.manipulation;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.manipulation.AlterColumnAction;
import org.sql.generation.api.grammar.manipulation.SetColumnDefault;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class SetColumnDefaultImpl extends SQLSyntaxElementBase<AlterColumnAction, SetColumnDefault>
    implements SetColumnDefault
{

    private final String _default;

    public SetColumnDefaultImpl( SQLProcessorAggregator processor, String colDefault )
    {
        this( processor, SetColumnDefault.class, colDefault );
    }

    protected SetColumnDefaultImpl( SQLProcessorAggregator processor,
        Class<? extends SetColumnDefault> realImplementingType, String colDefault )
    {
        super( processor, realImplementingType );

        NullArgumentException.validateNotNull( "Column default", colDefault );

        this._default = colDefault;
    }

    @Override
    protected boolean doesEqual( SetColumnDefault another )
    {
        return this._default.equals( another.getDefault() );
    }

    public String getDefault()
    {
        return this._default;
    }

}

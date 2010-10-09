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

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.manipulation.AlterColumnAction;
import org.sql.generation.api.grammar.manipulation.SetColumnDefault;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class SetColumnDefaultImpl extends TypeableImpl<AlterColumnAction, SetColumnDefault>
    implements SetColumnDefault
{

    private final String _default;

    public SetColumnDefaultImpl( String colDefault )
    {
        this( SetColumnDefault.class, colDefault );
    }

    protected SetColumnDefaultImpl( Class<? extends SetColumnDefault> realImplementingType, String colDefault )
    {
        super( realImplementingType );

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

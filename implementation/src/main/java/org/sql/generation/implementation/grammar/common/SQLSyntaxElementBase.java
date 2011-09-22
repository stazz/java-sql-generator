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

package org.sql.generation.implementation.grammar.common;

import org.atp.api.Typeable;
import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public abstract class SQLSyntaxElementBase<BaseInterfaceType extends Typeable<?>, ActualInterfaceType extends BaseInterfaceType>
    extends TypeableImpl<BaseInterfaceType, ActualInterfaceType>
{
    private final SQLProcessorAggregator _processor;

    protected SQLSyntaxElementBase( SQLProcessorAggregator processor,
        Class<? extends ActualInterfaceType> realImplementingType )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "SQL Processor", processor );

        this._processor = processor;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        this._processor.process( this, builder );
        return builder.toString();
    }

}

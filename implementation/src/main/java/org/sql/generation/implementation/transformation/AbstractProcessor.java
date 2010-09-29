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

package org.sql.generation.implementation.transformation;

import org.lwdci.api.context.single.Typeable;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.implementation.transformation.spi.SQLProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class AbstractProcessor<ProcessableType extends Typeable<?>>
    implements SQLProcessor
{

    private final Class<? extends ProcessableType> _type;

    public AbstractProcessor( Class<? extends ProcessableType> realType )
    {
        NullArgumentException.validateNotNull( "Processable type", realType );

        this._type = realType;
    }

    public void process( SQLProcessorAggregator aggregator, Typeable<?> object, StringBuilder builder )
    {
        if( object != null )
        {
            this.doProcess( aggregator, this._type.cast( object ), builder );
        }
    }

    protected abstract void doProcess( SQLProcessorAggregator aggregator, ProcessableType object, StringBuilder builder );
}

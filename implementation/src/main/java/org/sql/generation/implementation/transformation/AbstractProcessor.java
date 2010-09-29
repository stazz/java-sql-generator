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
import org.lwdci.spi.context.single.skeletons.TypeableImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class AbstractProcessor<BaseType extends Typeable<?>, ObjectType extends BaseType> extends
    TypeableImpl<BaseType, ObjectType>
    implements SQLProcessor
{
    public AbstractProcessor( Class<? extends ObjectType> realType )
    {
        super( realType );
    }

    public void process( SQLProcessorAggregator aggregator, Typeable<?> object, StringBuilder builder )
    {
        if( object != null )
        {
            this.doProcess( aggregator, this.getImplementedType().cast( object ), builder );
        }
    }

    /**
     * By default, always returns false.
     */
    protected boolean doesEqual( ObjectType another )
    {
        return false;
    }

    protected abstract void doProcess( SQLProcessorAggregator aggregator, ObjectType object, StringBuilder builder );
}

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

import java.util.HashMap;
import java.util.Map;

import org.lwdci.spi.context.skeletons.ContextCreationServiceRootImpl;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.transformation.spi.SQLTransformation;
import org.sql.generation.implementation.transformation.spi.SQLTransformationContext;
import org.sql.generation.implementation.transformation.spi.SQLTransformationContextCreationArgs;
import org.sql.generation.implementation.transformation.spi.SQLTransformationContextCreator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class SQLTransformationImpl
    extends
    ContextCreationServiceRootImpl<SQLTransformationContext, SQLTransformationContextCreationArgs, SQLTransformationContextCreator<?>>
    implements SQLTransformation
{

    private Map<Class<? extends SQLVendor>, SQLTransformationContextCreator<?>> _creators;

    public SQLTransformationImpl()
    {
        this.objectActivated();
    }

    public SQLTransformationContext createContext( SQLTransformationContextCreationArgs args )
    {
        return this._creators.get( args.getVendor().getImplementedVendorType() ).createContext( args );
    }

    public void registerContextCreator( SQLTransformationContextCreator<?> creator )
    {
        if( !this._creators.containsKey( creator.getImplementedVendorType() ) )
        {
            this.doRegisterContextCreator( creator, this._creators, creator.getImplementedVendorType() );
        }
    }

    protected void createCreatorsMap()
    {
        this._creators = new HashMap<Class<? extends SQLVendor>, SQLTransformationContextCreator<?>>();
    }

    protected void initCreators()
    {

    }

}
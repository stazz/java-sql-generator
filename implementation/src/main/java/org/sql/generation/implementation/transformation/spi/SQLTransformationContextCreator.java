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

package org.sql.generation.implementation.transformation.spi;

import org.lwdci.api.context.ContextCreatorRoot;
import org.lwdci.spi.context.skeletons.ContextCreatorRootImpl;
import org.sql.generation.api.vendor.SQLVendor;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class SQLTransformationContextCreator<ContextType extends SQLTransformationContext> extends
    ContextCreatorRootImpl<SQLTransformationContext, ContextType, SQLTransformationContextCreationArgs>
    implements ContextCreatorRoot<SQLTransformationContext, SQLTransformationContextCreationArgs>
{

    private final SQLVendorInfoImpl _info;

    public SQLTransformationContextCreator( Class<? extends SQLVendor> vendorType )
    {
        this._info = new SQLVendorInfoImpl( vendorType );
    }

    public Class<? extends SQLVendor> getImplementedVendorType()
    {
        return this._info.getImplementedVendorType();
    }
}

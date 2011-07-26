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

package org.sql.generation.api.qi4j.bootstrap;

import java.io.IOException;

import org.qi4j.bootstrap.Assembler;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.sql.generation.api.qi4j.SQLVendorServiceComposite;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.api.vendor.SQLVendorProvider;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public class SQLVendorAssembler
    implements Assembler
{

    private final Class<? extends SQLVendor> _vendorClass;

    public SQLVendorAssembler( Class<? extends SQLVendor> vendorClass )
    {
        this._vendorClass = vendorClass;
    }

    public void assemble( ModuleAssembly module )
        throws AssemblyException
    {
        try
        {
            module.services( SQLVendorServiceComposite.class ).setMetaInfo(
                SQLVendorProvider.createVendor( this._vendorClass ) );
        }
        catch( IOException ioe )
        {
            throw new AssemblyException( ioe );
        }
    }

}

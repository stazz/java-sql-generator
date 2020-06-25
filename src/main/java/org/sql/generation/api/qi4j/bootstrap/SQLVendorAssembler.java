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

import org.qi4j.api.common.Visibility;
import org.qi4j.bootstrap.Assembler;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.sql.generation.api.qi4j.SQLVendorServiceComposite;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.api.vendor.SQLVendorProvider;

/**
 * @author 2011 Stanislav Muhametsin
 */
public class SQLVendorAssembler
        implements Assembler {

    public static final Visibility DEFAULT_VISIBILITY = Visibility.module;

    private final Class<? extends SQLVendor> _vendorClass;

    private final Visibility _visibility;

    public SQLVendorAssembler(final Class<? extends SQLVendor> vendorClass) {
        this(vendorClass, SQLVendorAssembler.DEFAULT_VISIBILITY);
    }

    public SQLVendorAssembler(final Class<? extends SQLVendor> vendorClass, final Visibility visibility) {
        this._vendorClass = vendorClass;
        this._visibility = visibility;
    }

    @Override
    public void assemble(final ModuleAssembly module)
            throws AssemblyException {
//        try {
        final SQLVendor sqlVendor = SQLVendorProvider.createVendor(this._vendorClass);
        if (sqlVendor != null) {
            module.services(SQLVendorServiceComposite.class).visibleIn(this._visibility)
                    .setMetaInfo(sqlVendor);
        } else {
            throw new AssemblyException();
        }
//        } catch (final IOException ioe) {
//
//        }
    }

}

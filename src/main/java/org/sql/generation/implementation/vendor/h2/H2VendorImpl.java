/*
 * Copyright (c) 2012, Paul Merlin.
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
package org.sql.generation.implementation.vendor.h2;

import org.sql.generation.api.vendor.H2Vendor;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.transformation.h2.H2Processor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;
import org.sql.generation.implementation.vendor.DefaultVendor;

public class H2VendorImpl
        extends DefaultVendor
        implements H2Vendor {

    protected static final ProcessorCallback H2_PROCESSOR = new ProcessorCallback() {

        @Override
        public SQLProcessorAggregator get(final SQLVendor vendor) {
            return new H2Processor(vendor);
        }

    };

    public H2VendorImpl() {
        super(H2VendorImpl.H2_PROCESSOR);
    }

}

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

package org.sql.generation.implementation.grammar.factories;

import org.sql.generation.api.grammar.common.datatypes.*;
import org.sql.generation.api.grammar.factories.DataTypeFactory;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.common.SQLFactoryBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public abstract class AbstractDataTypeFactory extends SQLFactoryBase
        implements DataTypeFactory {

    protected AbstractDataTypeFactory(final SQLVendor vendor, final SQLProcessorAggregator processor) {
        super(vendor, processor);
    }

    @Override
    public Decimal decimal() {
        return this.decimal(null, null);
    }

    @Override
    public Decimal decimal(final Integer precision) {
        return this.decimal(precision, null);
    }

    @Override
    public Numeric numeric() {
        return this.numeric(null, null);
    }

    @Override
    public Numeric numeric(final Integer precision) {
        return this.numeric(precision, null);
    }

    @Override
    public SQLChar sqlChar() {
        return this.sqlChar(null);
    }

    @Override
    public SQLFloat sqlFloat() {
        return this.sqlFloat(null);
    }

    @Override
    public SQLChar sqlVarChar() {
        return this.sqlVarChar(null);
    }

    @Override
    public SQLTime time() {
        return this.time(null, null);
    }

    @Override
    public SQLTime time(final Boolean withTimeZone) {
        return this.time(null, withTimeZone);
    }

    @Override
    public SQLTime time(final Integer precision) {
        return this.time(precision, null);
    }

    @Override
    public SQLTimeStamp timeStamp() {
        return this.timeStamp(null, null);
    }

    @Override
    public SQLTimeStamp timeStamp(final Boolean withTimeZone) {
        return this.timeStamp(null, withTimeZone);
    }

    @Override
    public SQLTimeStamp timeStamp(final Integer precision) {
        return this.timeStamp(precision, null);
    }
}

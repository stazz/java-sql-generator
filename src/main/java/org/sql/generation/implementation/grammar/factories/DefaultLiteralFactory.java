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

import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.factories.LiteralFactory;
import org.sql.generation.api.grammar.literals.*;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.common.SQLFactoryBase;
import org.sql.generation.implementation.grammar.literals.*;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.sql.Timestamp;

/**
 * @author Stanislav Muhametsin
 */
public class DefaultLiteralFactory extends SQLFactoryBase
        implements LiteralFactory {

    private final DirectLiteral _param;

    public DefaultLiteralFactory(final SQLVendor vendor, final SQLProcessorAggregator processor) {
        super(vendor, processor);

        this._param = new DirectLiteralImpl(this.getProcessor(), SQLConstants.QUESTION_MARK);
    }

    @Override
    public DirectLiteral l(final String literalContents) {
        return new DirectLiteralImpl(this.getProcessor(), literalContents);
    }

    @Override
    public DirectLiteral param() {
        return this._param;
    }

    @Override
    public StringLiteral s(final String literal) {
        return new StringLiteralImpl(this.getProcessor(), literal);
    }

    @Override
    public TimestampTimeLiteral dt(final Timestamp date) {
        return new TimestampLiteralImpl(this.getProcessor(), date);
    }

    @Override
    public NumericLiteral n(final Number number) {
        return new NumericLiteralImpl(this.getProcessor(), number);
    }

    @Override
    public SQLFunctionLiteral func(final String name, final ValueExpression... parameters) {
        return new SQLFunctionLiteralImpl(this.getProcessor(), name, parameters);
    }
}

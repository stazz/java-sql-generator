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

package org.sql.generation.implementation.grammar.query;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.query.RowDefinition;
import org.sql.generation.api.grammar.query.RowValueConstructor;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Muhametsin
 */
public class RowDefinitionImpl extends SQLSyntaxElementBase<RowValueConstructor, RowDefinition>
        implements RowDefinition {

    private final List<ValueExpression> _rowElements;

    public RowDefinitionImpl(final SQLProcessorAggregator processor, final List<ValueExpression> rowElements) {
        this(processor, RowDefinition.class, rowElements);
    }

    protected RowDefinitionImpl(final SQLProcessorAggregator processor, final Class<? extends RowDefinition> realImplementingType,
                                final List<ValueExpression> rowElements) {
        super(processor, realImplementingType);

        NullArgumentException.validateNotNull("row elements", rowElements);

        this._rowElements = Collections.unmodifiableList(rowElements);
    }

    @Override
    public List<ValueExpression> getRowElements() {
        return this._rowElements;
    }

    @Override
    protected boolean doesEqual(final RowDefinition another) {
        return this._rowElements.equals(another.getRowElements());
    }

}

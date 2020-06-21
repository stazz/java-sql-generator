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

package org.sql.generation.implementation.grammar.definition.view;

import org.atp.api.Typeable;
import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.SchemaStatement;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.definition.view.ViewCheckOption;
import org.sql.generation.api.grammar.definition.view.ViewDefinition;
import org.sql.generation.api.grammar.definition.view.ViewSpecification;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class ViewDefinitionImpl extends SQLSyntaxElementBase<SchemaStatement, ViewDefinition>
        implements ViewDefinition {

    private final TableNameDirect _name;
    private final QueryExpression _query;
    private final ViewSpecification _spec;
    private final ViewCheckOption _viewCheck;
    private final Boolean _isRecursive;

    public ViewDefinitionImpl(final SQLProcessorAggregator processor, final TableNameDirect name, final QueryExpression query,
                              final ViewSpecification spec, final ViewCheckOption viewCheck, final Boolean isRecursive) {
        this(processor, ViewDefinition.class, name, query, spec, viewCheck, isRecursive);
    }

    protected ViewDefinitionImpl(final SQLProcessorAggregator processor,
                                 final Class<? extends ViewDefinition> realImplementingType, final TableNameDirect name, final QueryExpression query,
                                 final ViewSpecification spec, final ViewCheckOption viewCheck, final Boolean isRecursive) {
        super(processor, realImplementingType);

        NullArgumentException.validateNotNull("View name", name);
        NullArgumentException.validateNotNull("View query", query);
        NullArgumentException.validateNotNull("Is recursive", isRecursive);
        NullArgumentException.validateNotNull("View specification", spec);

        this._name = name;
        this._query = query;
        this._spec = spec;
        this._isRecursive = isRecursive;
        this._viewCheck = viewCheck;
    }

    @Override
    protected boolean doesEqual(final ViewDefinition another) {
        return this._name.equals(another.getViewName()) && this._isRecursive.equals(another.isRecursive())
                && this._spec.equals(another.getViewSpecification()) && this._query.equals(another.getViewQuery())
                && TypeableImpl.bothNullOrEquals(this._viewCheck, another.getViewCheckOption());
    }

    @Override
    public Typeable<?> asTypeable() {
        return this;
    }

    @Override
    public ViewCheckOption getViewCheckOption() {
        return this._viewCheck;
    }

    @Override
    public TableNameDirect getViewName() {
        return this._name;
    }

    @Override
    public QueryExpression getViewQuery() {
        return this._query;
    }

    @Override
    public ViewSpecification getViewSpecification() {
        return this._spec;
    }

    @Override
    public Boolean isRecursive() {
        return this._isRecursive;
    }

}

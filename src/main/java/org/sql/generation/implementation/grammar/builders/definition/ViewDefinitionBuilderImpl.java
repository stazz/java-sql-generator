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

package org.sql.generation.implementation.grammar.builders.definition;

import org.sql.generation.api.grammar.builders.definition.ViewDefinitionBuilder;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.definition.view.ViewCheckOption;
import org.sql.generation.api.grammar.definition.view.ViewDefinition;
import org.sql.generation.api.grammar.definition.view.ViewSpecification;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.definition.view.ViewDefinitionImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class ViewDefinitionBuilderImpl extends SQLBuilderBase
        implements ViewDefinitionBuilder {

    private Boolean _isRecursive;
    private TableNameDirect _name;
    private QueryExpression _query;
    private ViewCheckOption _viewCheck;
    private ViewSpecification _viewSpec;

    public ViewDefinitionBuilderImpl(final SQLProcessorAggregator processor) {
        super(processor);
    }

    @Override
    public ViewDefinition createExpression() {
        return new ViewDefinitionImpl(this.getProcessor(), this._name, this._query, this._viewSpec, this._viewCheck,
                this._isRecursive);
    }

    @Override
    public ViewDefinitionBuilder setRecursive(final Boolean isRecursive) {
        this._isRecursive = isRecursive;
        return this;
    }

    @Override
    public ViewDefinitionBuilder setViewName(final TableNameDirect viewName) {
        this._name = viewName;
        return this;
    }

    @Override
    public ViewDefinitionBuilder setQuery(final QueryExpression query) {
        this._query = query;
        return this;
    }

    @Override
    public ViewDefinitionBuilder setViewCheckOption(final ViewCheckOption viewCheck) {
        this._viewCheck = viewCheck;
        return this;
    }

    @Override
    public ViewDefinitionBuilder setViewSpecification(final ViewSpecification spec) {
        this._viewSpec = spec;
        return this;
    }

    @Override
    public Boolean isRecursive() {
        return this._isRecursive;
    }

    @Override
    public TableNameDirect getViewName() {
        return this._name;
    }

    @Override
    public QueryExpression getQueryExpression() {
        return this._query;
    }

    @Override
    public ViewCheckOption getViewCheckOption() {
        return this._viewCheck;
    }

    @Override
    public ViewSpecification getViewSpecification() {
        return this._viewSpec;
    }
}

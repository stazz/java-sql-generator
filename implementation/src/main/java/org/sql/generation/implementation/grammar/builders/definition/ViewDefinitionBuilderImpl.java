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
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.definition.view.ViewCheckOption;
import org.sql.generation.api.grammar.definition.view.ViewDefinition;
import org.sql.generation.api.grammar.definition.view.ViewSpecification;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.implementation.grammar.definition.view.ViewDefinitionImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class ViewDefinitionBuilderImpl
    implements ViewDefinitionBuilder
{

    private Boolean _isRecursive;
    private TableName _name;
    private QueryExpression _query;
    private ViewCheckOption _viewCheck;
    private ViewSpecification _viewSpec;

    public ViewDefinition createExpression()
    {
        return new ViewDefinitionImpl( this._name, this._query, this._viewSpec, this._viewCheck, this._isRecursive );
    }

    public ViewDefinitionBuilder setRecursive( Boolean isRecursive )
    {
        this._isRecursive = isRecursive;
        return this;
    }

    public ViewDefinitionBuilder setViewName( TableName viewName )
    {
        this._name = viewName;
        return this;
    }

    public ViewDefinitionBuilder setQuery( QueryExpression query )
    {
        this._query = query;
        return this;
    }

    public ViewDefinitionBuilder setViewCheckOption( ViewCheckOption viewCheck )
    {
        this._viewCheck = viewCheck;
        return this;
    }

    public ViewDefinitionBuilder setViewSpecification( ViewSpecification spec )
    {
        this._viewSpec = spec;
        return this;
    }

    public Boolean isRecursive()
    {
        return this._isRecursive;
    }

    public TableName getViewName()
    {
        return this._name;
    }

    public QueryExpression getQueryExpression()
    {
        return this._query;
    }

    public ViewCheckOption getViewCheckOption()
    {
        return this._viewCheck;
    }

    public ViewSpecification getViewSpecification()
    {
        return this._viewSpec;
    }
}

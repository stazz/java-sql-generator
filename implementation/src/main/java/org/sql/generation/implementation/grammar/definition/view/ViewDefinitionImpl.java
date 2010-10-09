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

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.SchemaStatement;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.definition.view.ViewCheckOption;
import org.sql.generation.api.grammar.definition.view.ViewDefinition;
import org.sql.generation.api.grammar.definition.view.ViewSpecification;
import org.sql.generation.api.grammar.query.QueryExpression;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class ViewDefinitionImpl extends TypeableImpl<SchemaStatement, ViewDefinition>
    implements ViewDefinition
{

    private final TableName _name;
    private final QueryExpression _query;
    private final ViewSpecification _spec;
    private final ViewCheckOption _viewCheck;
    private final Boolean _isRecursive;

    public ViewDefinitionImpl( TableName name, QueryExpression query, ViewSpecification spec,
        ViewCheckOption viewCheck, Boolean isRecursive )
    {
        this( ViewDefinition.class, name, query, spec, viewCheck, isRecursive );
    }

    protected ViewDefinitionImpl( Class<? extends ViewDefinition> realImplementingType, TableName name,
        QueryExpression query, ViewSpecification spec, ViewCheckOption viewCheck, Boolean isRecursive )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "View name", name );
        NullArgumentException.validateNotNull( "View query", query );
        NullArgumentException.validateNotNull( "Is recursive", isRecursive );
        NullArgumentException.validateNotNull( "View specification", spec );

        this._name = name;
        this._query = query;
        this._spec = spec;
        this._isRecursive = isRecursive;
        this._viewCheck = viewCheck;
    }

    @Override
    protected boolean doesEqual( ViewDefinition another )
    {
        return this._name.equals( another.getViewName() ) && this._isRecursive.equals( another.isRecursive() )
            && this._spec.equals( another.getViewSpecification() ) && this._query.equals( another.getViewQuery() )
            && TypeableImpl.bothNullOrEquals( this._viewCheck, another.getViewCheckOption() );
    }

    public ViewCheckOption getViewCheckOption()
    {
        return this._viewCheck;
    }

    public TableName getViewName()
    {
        return this._name;
    }

    public QueryExpression getViewQuery()
    {
        return this._query;
    }

    public ViewSpecification getViewSpecification()
    {
        return this._spec;
    }

    public Boolean isRecursive()
    {
        return this._isRecursive;
    }

}

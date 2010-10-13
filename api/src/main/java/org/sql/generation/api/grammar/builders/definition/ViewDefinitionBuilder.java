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

package org.sql.generation.api.grammar.builders.definition;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.definition.view.RegularViewSpecification;
import org.sql.generation.api.grammar.definition.view.ViewCheckOption;
import org.sql.generation.api.grammar.definition.view.ViewDefinition;
import org.sql.generation.api.grammar.definition.view.ViewSpecification;
import org.sql.generation.api.grammar.query.QueryExpression;

/**
 * This is a builder for {@code CREATE VIEW} statements.
 * 
 * @author Stanislav Muhametsin
 * @see ViewDefinition
 */
public interface ViewDefinitionBuilder
    extends AbstractBuilder<ViewDefinition>
{

    /**
     * Sets whether this view is {@code RECURSIVE}.
     * 
     * @param isRecursive True if view is to be {@code RECURSIVE}; false otherwise.
     * @return This builder.
     */
    public ViewDefinitionBuilder setRecursive( Boolean isRecursive );

    /**
     * Sets the name for this view.
     * 
     * @param viewName The name for this view.
     * @return This builder.
     */
    public ViewDefinitionBuilder setViewName( TableName viewName );

    /**
     * Sets the query for this view.
     * 
     * @param query The query for this view.
     * @return This builder.
     */
    public ViewDefinitionBuilder setQuery( QueryExpression query );

    /**
     * Sets the view check option for this view.
     * 
     * @param viewCheck The view check option for this view.
     * @return This builder.
     * @see ViewCheckOption
     */
    public ViewDefinitionBuilder setViewCheckOption( ViewCheckOption viewCheck );

    /**
     * Sets the view specification for this view. Typically is a list of columns (via {@link RegularViewSpecification}).
     * 
     * @param spec The view specification.
     * @return This builder.
     * @see ViewSpecification
     */
    public ViewDefinitionBuilder setViewSpecification( ViewSpecification spec );

    /**
     * Returns whether this view is to be {@code RECURSIVE}.
     * 
     * @return True if this view is to be {@code RECURSIVE}; false otherwise.
     */
    public Boolean isRecursive();

    /**
     * Returns the name of the view.
     * 
     * @return The name of the view.
     */
    public TableName getViewName();

    /**
     * Returns the query for the view.
     * 
     * @return The query for the view.
     */
    public QueryExpression getQueryExpression();

    /**
     * Returns the view check option.
     * 
     * @return The view check option.
     * @see ViewCheckOption
     */
    public ViewCheckOption getViewCheckOption();

    /**
     * Returns the view specification. Typically is a list of columns (via {@link RegularViewSpecification}).
     * 
     * @return The view specification.
     */
    public ViewSpecification getViewSpecification();

}

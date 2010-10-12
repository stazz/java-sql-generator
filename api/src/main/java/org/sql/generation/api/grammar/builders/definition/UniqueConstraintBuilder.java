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

import java.util.List;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.definition.table.UniqueConstraint;
import org.sql.generation.api.grammar.definition.table.UniqueSpecification;

/**
 * This is builder for {@code UNIQUE(...)} and {@code PRIMARY KEY(...)} table constraints in table definition.
 * 
 * @author Stanislav Muhametsin
 */
public interface UniqueConstraintBuilder
    extends AbstractBuilder<UniqueConstraint>
{

    /**
     * Sets the uniqueness kind for this uniqueness constraint.
     * 
     * @param uniqueness The uniqueness kind for this uniqueness constraint.
     * @return This builder.
     * @see UniqueSpecification
     */
    public UniqueConstraintBuilder setUniqueness( UniqueSpecification uniqueness );

    /**
     * Adds the columns that have to be unique.
     * 
     * @param columnNames The column names that have to be unique.
     * @return This builder.
     */
    public UniqueConstraintBuilder addColumns( String... columnNames );

    /**
     * Returns the uniqueness type for this uniqueness constraint.
     * 
     * @return The uniqueness type for this uniqueness constraint.
     */
    public UniqueSpecification getUniqueness();

    /**
     * Returns the column names that have to be unique.
     * 
     * @return The column names that have to be unique.
     */
    public List<String> getColumns();
}

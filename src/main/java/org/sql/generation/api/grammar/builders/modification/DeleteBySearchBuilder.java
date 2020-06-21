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

package org.sql.generation.api.grammar.builders.modification;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.builders.booleans.BooleanBuilder;
import org.sql.generation.api.grammar.modification.DeleteBySearch;
import org.sql.generation.api.grammar.modification.TargetTable;

/**
 * This builder builds statements deleting rows matching search condition ({@code DELETE FROM} table {@code [WHERE}
 * searchCondition {@code ]}).
 *
 * @author Stanislav Muhametsin
 * @see TargetTable
 * @see BooleanBuilder
 */
public interface DeleteBySearchBuilder
        extends AbstractBuilder<DeleteBySearch> {
    /**
     * Sets the target table for this {@code DELETE} statement.
     *
     * @param table The target table for this {@code DELETE} statement.
     * @return This builder.
     */
    DeleteBySearchBuilder setTargetTable(TargetTable table);

    /**
     * Retrieves the target table for this {@code DELETE} statement.
     *
     * @return The target table for this {@code DELETE} statement.
     */
    TargetTable getTargetTable();

    /**
     * Returns the builder for search condition for this {@code DELETE} statement (boolean expression after
     * {@code WHERE}). The search condition is optional.
     *
     * @return The builder for search condition for this {@code DELETE} statement.
     */
    BooleanBuilder getWhere();
}

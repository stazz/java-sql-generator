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

package org.sql.generation.api.grammar.query;

import org.sql.generation.api.common.NullArgumentException;

import java.util.List;

/**
 * This syntax element represents the whole {@code SELECT <column1>, <column2>, ...} clause, up until {@code FROM}. Each
 * column might have an alias.
 *
 * @author Stanislav Muhametsin
 */
public interface ColumnReferences
        extends SelectColumnClause {
    /**
     * A helper class to encapsulate column reference along with its alias.
     *
     * @author Stanislav Muhametsin
     */
    class ColumnReferenceInfo {
        private final String _alias;
        private final ColumnReference _reference;

        public ColumnReferenceInfo(final String alias, final ColumnReference reference) {
            NullArgumentException.validateNotNull("reference", reference);

            this._alias = alias;
            this._reference = reference;
        }

        /**
         * Returns the alias of this column reference. May be {@code null.}
         *
         * @return The alias of this column reference. May be {@code null.}
         */
        public String getAlias() {
            return this._alias;
        }

        /**
         * Returns the column reference.
         *
         * @return The column reference.
         */
        public ColumnReference getReference() {
            return this._reference;
        }
    }

    /**
     * Returns the list of column references, along with their aliases.
     *
     * @return The list of column references, along with their aliases.
     */
    List<ColumnReferenceInfo> getColumns();

}

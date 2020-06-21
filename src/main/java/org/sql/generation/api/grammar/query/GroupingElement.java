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

import org.atp.api.Typeable;

/**
 * This is a common interface for a grouping element of the {@code GROUP BY} clause.
 *
 * @author Stanislav Muhametsin
 * @see GroupByClause
 */
public interface GroupingElement
        extends Typeable<GroupingElement> {
    /**
     * This syntax element represents the grand total grouping element. It is open parenthesis followed by close
     * parenthesis.
     *
     * @author Stanislav Muhametsin
     */
    final class GrandTotal
            implements GroupingElement {
        private GrandTotal() {
        }

        /**
         * Returns {@link GrandTotal}
         */
        @Override
        public Class<? extends GroupingElement> getImplementedType() {
            return GrandTotal.class;
        }

        /**
         * The singleton instance of {@link GrandTotal}.
         */
        public static final GrandTotal INSTANCE = new GrandTotal();
    }
}

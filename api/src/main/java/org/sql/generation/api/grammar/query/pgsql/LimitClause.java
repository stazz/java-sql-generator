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

package org.sql.generation.api.grammar.query.pgsql;

/**
 * This is common interface for {@code LIMIT} clauses used in PostgreSQL queries.
 * 
 * @author Stanislav Muhametsin
 * @see LimitAll
 * @see LimitByNumber
 */
public interface LimitClause
{
    /**
     * This syntax element represents the {@code LIMIT ALL} clause in PostgreSQL queries.
     * 
     * @author Stanislav Muhametsin
     */
    public static final class LimitAll
        implements LimitClause
    {
        private LimitAll()
        {

        }

        /**
         * The singleton instance of {@link LimitAll}.
         */
        public static final LimitAll INSTANCE = new LimitAll();
    }
}

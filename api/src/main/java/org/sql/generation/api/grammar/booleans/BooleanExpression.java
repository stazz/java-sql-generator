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

package org.sql.generation.api.grammar.booleans;

import org.sql.generation.api.grammar.common.ValueExpression;

/**
 * A common interface for all boolean expressions in SQL language.
 * 
 * @author Stanislav Muhametsin
 */
public interface BooleanExpression
    extends ValueExpression
{

    /**
     * This class represents a boolean expression which always evaluates to true ({@code TRUE}).
     * 
     * @author Stanislav Muhametsin
     */
    public static final class True
        implements BooleanExpression
    {
        private True()
        {

        }

        /**
         * Returns {@link True}.
         */
        public Class<? extends ValueExpression> getImplementedType()
        {
            return True.class;
        }

        /**
         * Returns the singleton instance representing {@code TRUE}.
         */
        public static final True INSTANCE = new True();
    }

    /**
     * This class represents a boolean expression which always evaluates to false ({@code FALSE}.
     * 
     * @author Stanislav Muhametsin
     */
    public static final class False
        implements BooleanExpression
    {
        private False()
        {

        }

        /**
         * Returns {@link False}.
         */
        public Class<? extends ValueExpression> getImplementedType()
        {
            return False.class;
        }

        /**
         * Returns the singleton instance representing {@code FALSE}.
         */
        public static final False INSTANCE = new False();
    }
}

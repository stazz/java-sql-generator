/*
 * Copyright (c) 2012, Stanislav Muhametsin. All Rights Reserved.
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

package org.sql.generation.api.grammar.modification;

import org.sql.generation.api.grammar.common.ValueExpression;

public class ValueSource
{

    /**
     * This syntax element represents the {@code DEFAULT} keyword as data source for column, meaning
     * to use the default value for the column.
     * 
     * @author Stanislav Muhametsin
     */
    public static final class Default
            implements ValueExpression
    {
        private Default()
        {
    
        }
    
        /**
         * Returns {@link Default}.
         */
        public Class<? extends ValueExpression> getImplementedType()
        {
            return Default.class;
        }
    
        /**
         * Singleton instance of {@link Default}.
         */
        public static final Default INSTANCE = new Default();
    }

    /**
     * This syntax element represents the {@code NULL} keyword as data source for column, meaning to
     * use the {@code NULL} value for the column.
     * 
     * @author Stanislav Muhametsin
     */
    public static final class Null
            implements ValueExpression
    {
        private Null()
        {
    
        }
    
        /**
         * Returns {@link Null}.
         */
        public Class<? extends ValueExpression> getImplementedType()
        {
            return Null.class;
        }
    
        /**
         * The singleton instance of {@link Null}.
         */
        public static final Null INSTANCE = new Null();
    }

}

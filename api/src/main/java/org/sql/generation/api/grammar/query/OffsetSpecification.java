/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
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
import org.sql.generation.api.grammar.common.NonBooleanExpression;

/**
 * This syntax element represents the {@code OFFSET <number> ROWS} element of the query. This addition has been defined
 * in SQL 2008 standard.
 * 
 * @author 2011 Stanislav Muhametsin
 */
public interface OffsetSpecification
    extends Typeable<OffsetSpecification>
{
    /**
     * Returns the {@code <number>} element in the {@code OFFSET <number> ROWS} expression. Means the amount of rows to
     * skip before including them into select.
     * 
     * @return The amount of rows to be skipped before starting selecting.
     */
    public NonBooleanExpression getSkip();
}

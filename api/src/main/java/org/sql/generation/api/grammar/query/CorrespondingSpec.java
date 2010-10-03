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
import org.sql.generation.api.grammar.common.ColumnNameList;

/**
 * This syntax element represents the {@code CORRESPONDING BY} clause in {@code UNION}, {@code INTERSECT}, and
 * {@code EXCEPT} operations on queries.
 * 
 * @author Stanislav Muhametsin
 */
public interface CorrespondingSpec
    extends Typeable<CorrespondingSpec>
{
    /**
     * Gets the column name correspondence list.
     * 
     * @return The column name correspondence list.
     */
    public ColumnNameList getColumnList();
}

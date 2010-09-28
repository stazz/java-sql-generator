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

import java.util.List;

import org.sql.generation.api.common.NullArgumentException;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface ColumnReferences
    extends SelectColumnClause
{
    public static class ColumnReferenceInfo
    {
        private final String _alias;
        private final ColumnReference _reference;

        public ColumnReferenceInfo( String alias, ColumnReference reference )
        {
            NullArgumentException.validateNotNull( "reference", reference );

            this._alias = alias;
            this._reference = reference;
        }

        public String getAlias()
        {
            return this._alias;
        }

        public ColumnReference getReference()
        {
            return this._reference;
        }
    }

    public List<ColumnReferenceInfo> getColumns();

}

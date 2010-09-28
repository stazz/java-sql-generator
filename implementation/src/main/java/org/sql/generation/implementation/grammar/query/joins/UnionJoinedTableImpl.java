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

package org.sql.generation.implementation.grammar.query.joins;

import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.api.grammar.query.TableReferencePrimary;
import org.sql.generation.api.grammar.query.joins.UnionJoinedTable;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class UnionJoinedTableImpl extends JoinedTablePrimary<UnionJoinedTable>
    implements UnionJoinedTable
{

    public UnionJoinedTableImpl( TableReference left, TableReferencePrimary right )
    {
        this( UnionJoinedTable.class, left, right );
    }

    protected UnionJoinedTableImpl( Class<? extends UnionJoinedTable> implClass, TableReference left,
        TableReferencePrimary right )
    {
        super( implClass, left, right );
    }

}

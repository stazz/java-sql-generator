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

package org.sql.generation.api.grammar.factories.pgsql;

import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.factories.ManipulationFactory;
import org.sql.generation.api.grammar.manipulation.DropBehaviour;
import org.sql.generation.api.grammar.manipulation.ObjectType;
import org.sql.generation.api.grammar.manipulation.pgsql.PgSQLDropTableOrViewStatement;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface PgSQLManipulationFactory
    extends ManipulationFactory
{

    public PgSQLDropTableOrViewStatement createDropTableOrViewStatement( TableNameDirect tableName, ObjectType theType,
        DropBehaviour dropBehaviour );

    /**
     * Creates {@code DROP TABLE/VIEW} statement, which may use {@code IF EXISTS} clause before the table name.
     * 
     * @param tableName The name of the table/view to drop.
     * @param theType What to drop - {@link ObjectType#TABLE} or {@link ObjectType#VIEW}.
     * @param dropBehaviour Drop behaviour - {@link DropBehaviour#CASCADE} or {@link DropBehaviour#RESTRICT}.
     * @param useIfExists {@code true} to append {@code IF EXISTS} before table/view name, {@code false} otherwise.
     * @return New {@code DROP TABLE/VIEW} statement.
     */
    public PgSQLDropTableOrViewStatement createDropTableOrViewStatement( TableNameDirect tableName, ObjectType theType,
        DropBehaviour dropBehaviour, Boolean useIfExists );
}

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

package org.sql.generation.implementation.grammar.factories.pgsql;

import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.factories.pgsql.PgSQLManipulationFactory;
import org.sql.generation.api.grammar.manipulation.DropBehaviour;
import org.sql.generation.api.grammar.manipulation.ObjectType;
import org.sql.generation.api.grammar.manipulation.pgsql.PgSQLDropTableOrViewStatement;
import org.sql.generation.implementation.grammar.factories.DefaultManipulationFactory;
import org.sql.generation.implementation.grammar.manipulation.pgsql.PgSQLDropTableOrViewStatementImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class PgSQLManipulationFactoryImpl extends DefaultManipulationFactory
    implements PgSQLManipulationFactory
{

    @Override
    public PgSQLDropTableOrViewStatement createDropTableOrViewStatement( TableNameDirect tableName, ObjectType theType,
        DropBehaviour dropBehaviour )
    {
        return this.createDropTableOrViewStatement( tableName, theType, dropBehaviour, false );
    }

    public PgSQLDropTableOrViewStatement createDropTableOrViewStatement( TableNameDirect tableName, ObjectType theType,
        DropBehaviour dropBehaviour, Boolean useIfExists )
    {
        return new PgSQLDropTableOrViewStatementImpl( theType, dropBehaviour, tableName, useIfExists );
    }

}

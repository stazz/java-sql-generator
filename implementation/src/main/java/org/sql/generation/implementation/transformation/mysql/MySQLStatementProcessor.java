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

package org.sql.generation.implementation.transformation.mysql;

import java.util.HashMap;
import java.util.Map;

import org.lwdci.api.context.single.Typeable;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.implementation.transformation.SQLStatementProcessor;
import org.sql.generation.implementation.transformation.mysql.MySQLTableProcessing.MySQLTableNameProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessor;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class MySQLStatementProcessor extends SQLStatementProcessor
{

    private static final Map<Class<? extends Typeable<?>>, SQLProcessor> _defaultProcessors;

    static
    {
        Map<Class<? extends Typeable<?>>, SQLProcessor> processors = new HashMap<Class<? extends Typeable<?>>, SQLProcessor>(
            SQLStatementProcessor.getDefaultProcessors() );
        // MySQL does not understand schema-qualified table names
        processors.put( TableName.class, new MySQLTableNameProcessor() );

        _defaultProcessors = processors;
    }

    public MySQLStatementProcessor()
    {
        super( _defaultProcessors );
    }
}

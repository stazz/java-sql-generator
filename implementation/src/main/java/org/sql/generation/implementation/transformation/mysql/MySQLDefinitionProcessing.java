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

import org.sql.generation.api.grammar.definition.schema.SchemaDefinition;
import org.sql.generation.implementation.transformation.DefinitionProcessing.SchemaDefinitionProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class MySQLDefinitionProcessing
{

    public static class MySQLSchemaDefinitionProcessor extends SchemaDefinitionProcessor
    {

        @Override
        protected void doProcess( SQLProcessorAggregator aggregator, SchemaDefinition object, StringBuilder builder )
        {
            // Just process schema elements
            this.processSchemaElements( aggregator, object, builder );
        }

    }
}

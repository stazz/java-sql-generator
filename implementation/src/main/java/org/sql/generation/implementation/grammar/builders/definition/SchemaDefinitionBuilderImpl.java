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

package org.sql.generation.implementation.grammar.builders.definition;

import java.util.ArrayList;
import java.util.List;

import org.sql.generation.api.grammar.builders.definition.SchemaDefinitionBuilder;
import org.sql.generation.api.grammar.definition.schema.SchemaDefinition;
import org.sql.generation.api.grammar.definition.schema.SchemaElement;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.definition.schema.SchemaDefinitionImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class SchemaDefinitionBuilderImpl extends SQLBuilderBase
    implements SchemaDefinitionBuilder
{

    private String _schemaName;
    private String _schemaCharset;
    private final List<SchemaElement> _elements;

    public SchemaDefinitionBuilderImpl( SQLProcessorAggregator processor )
    {
        super( processor );
        this._elements = new ArrayList<SchemaElement>();
    }

    public SchemaDefinition createExpression()
    {
        return new SchemaDefinitionImpl( this.getProcessor(), this._schemaName, this._schemaCharset, this._elements );
    }

    public SchemaDefinitionBuilder setSchemaName( String schemaName )
    {
        this._schemaName = schemaName;
        return this;
    }

    public SchemaDefinitionBuilder setSchemaCharset( String charset )
    {
        this._schemaCharset = charset;
        return this;
    }

    public SchemaDefinitionBuilder addSchemaElements( SchemaElement... elements )
    {
        for( SchemaElement el : elements )
        {
            this._elements.add( el );
        }
        return this;
    }

    public String getSchemaName()
    {
        return this._schemaName;
    }

    public String getSchemaCharset()
    {
        return this._schemaCharset;
    }

    public List<SchemaElement> getSchemaElements()
    {
        return this._elements;
    }

}

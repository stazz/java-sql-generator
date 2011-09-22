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

package org.sql.generation.implementation.grammar.definition.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.SchemaStatement;
import org.sql.generation.api.grammar.definition.schema.SchemaDefinition;
import org.sql.generation.api.grammar.definition.schema.SchemaElement;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class SchemaDefinitionImpl extends SQLSyntaxElementBase<SchemaStatement, SchemaDefinition>
    implements SchemaDefinition
{

    private final String _charset;
    private final String _name;
    private final List<SchemaElement> _elements;

    public SchemaDefinitionImpl( SQLProcessorAggregator processor, String name, String charset,
        List<SchemaElement> elements )
    {
        this( processor, SchemaDefinition.class, name, charset, elements );
    }

    protected SchemaDefinitionImpl( SQLProcessorAggregator processor,
        Class<? extends SchemaDefinition> realImplementingType, String name, String charset,
        List<SchemaElement> elements )
    {
        super( processor, realImplementingType );

        NullArgumentException.validateNotNull( "Schema name", name );
        NullArgumentException.validateNotNull( "Elements", elements );

        this._name = name;
        this._charset = charset;
        this._elements = Collections.unmodifiableList( new ArrayList<SchemaElement>( elements ) );
    }

    @Override
    protected boolean doesEqual( SchemaDefinition another )
    {
        return this._name.equals( another.getSchemaName() ) && this._elements.equals( another.getSchemaElements() )
            && TypeableImpl.bothNullOrEquals( this._charset, another.getSchemaCharset() );
    }

    public String getSchemaCharset()
    {
        return this._charset;
    }

    public List<SchemaElement> getSchemaElements()
    {
        return this._elements;
    }

    public String getSchemaName()
    {
        return this._name;
    }

}

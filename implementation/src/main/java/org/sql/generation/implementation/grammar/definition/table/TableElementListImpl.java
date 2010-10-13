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

package org.sql.generation.implementation.grammar.definition.table;

import java.util.Collections;
import java.util.List;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.definition.table.TableContentsSource;
import org.sql.generation.api.grammar.definition.table.TableElement;
import org.sql.generation.api.grammar.definition.table.TableElementList;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class TableElementListImpl extends TypeableImpl<TableContentsSource, TableElementList>
    implements TableElementList
{

    private final List<TableElement> _elements;

    public TableElementListImpl( List<TableElement> elements )
    {
        this( TableElementList.class, elements );
    }

    protected TableElementListImpl( Class<? extends TableElementList> realImplementingType, List<TableElement> elements )
    {
        super( realImplementingType );

        NullArgumentException.validateNotNull( "Table elements", elements );

        this._elements = Collections.unmodifiableList( elements );
    }

    @Override
    protected boolean doesEqual( TableElementList another )
    {
        return this._elements.equals( another.getElementList() );
    }

    public List<TableElement> getElementList()
    {
        return this._elements;
    }

}

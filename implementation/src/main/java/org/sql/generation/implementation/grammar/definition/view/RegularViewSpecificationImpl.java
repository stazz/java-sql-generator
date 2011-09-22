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

package org.sql.generation.implementation.grammar.definition.view;

import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.definition.view.RegularViewSpecification;
import org.sql.generation.api.grammar.definition.view.ViewSpecification;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class RegularViewSpecificationImpl extends SQLSyntaxElementBase<ViewSpecification, RegularViewSpecification>
    implements RegularViewSpecification
{

    private final ColumnNameList _columns;

    public RegularViewSpecificationImpl( SQLProcessorAggregator processor, ColumnNameList columns )
    {
        this( processor, RegularViewSpecification.class, columns );
    }

    protected RegularViewSpecificationImpl( SQLProcessorAggregator processor,
        Class<? extends RegularViewSpecification> realImplementingType, ColumnNameList columns )
    {
        super( processor, realImplementingType );

        this._columns = columns;
    }

    @Override
    protected boolean doesEqual( RegularViewSpecification another )
    {
        return this._columns.equals( another.getColumns() );
    }

    public ColumnNameList getColumns()
    {
        return this._columns;
    }

}

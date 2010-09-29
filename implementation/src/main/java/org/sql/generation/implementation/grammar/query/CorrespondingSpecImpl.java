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

package org.sql.generation.implementation.grammar.query;

import org.lwdci.spi.context.single.skeletons.TypeableImpl;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.query.CorrespondingSpec;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class CorrespondingSpecImpl extends TypeableImpl<CorrespondingSpec, CorrespondingSpec>
    implements CorrespondingSpec
{

    private final ColumnNameList _columnList;

    public CorrespondingSpecImpl( ColumnNameList columnNames )
    {
        this( CorrespondingSpec.class, columnNames );
    }

    protected CorrespondingSpecImpl( Class<? extends CorrespondingSpec> implClass, ColumnNameList columnNames )
    {
        super( implClass );

        this._columnList = columnNames;
    }

    public ColumnNameList getColumnList()
    {
        return this._columnList;
    }

    @Override
    protected boolean doesEqual( CorrespondingSpec another )
    {
        return TypeableImpl.bothNullOrEquals( this._columnList, another.getColumnList() );
    }
}

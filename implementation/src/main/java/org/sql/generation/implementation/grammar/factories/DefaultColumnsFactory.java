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

package org.sql.generation.implementation.grammar.factories;

import org.sql.generation.api.grammar.builders.query.ColumnsBuilder;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.query.ColumnReferenceByExpression;
import org.sql.generation.api.grammar.query.ColumnReferenceByName;
import org.sql.generation.implementation.grammar.builders.query.ColumnsBuilderImpl;
import org.sql.generation.implementation.grammar.common.ColumnNameListImpl;
import org.sql.generation.implementation.grammar.query.ColumnReferenceByExpressionImpl;
import org.sql.generation.implementation.grammar.query.ColumnReferenceByNameImpl;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultColumnsFactory extends AbstractColumnsFactory
{
    public ColumnsBuilder columnsBuilder( SetQuantifier setQuantifier )
    {
        return new ColumnsBuilderImpl( setQuantifier );
    }

    public ColumnReferenceByName colName( String tableName, String colName )
    {
        return new ColumnReferenceByNameImpl( tableName, colName );
    }

    public ColumnReferenceByExpression colExp( ValueExpression expression )
    {
        return new ColumnReferenceByExpressionImpl( expression );
    }

    public ColumnNameList colNames( String... names )
    {
        return new ColumnNameListImpl( names );
    }

}

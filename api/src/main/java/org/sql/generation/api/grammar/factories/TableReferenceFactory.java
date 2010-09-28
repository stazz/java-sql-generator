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

package org.sql.generation.api.grammar.factories;

import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.builders.query.TableReferenceBuilder;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.TableAlias;
import org.sql.generation.api.grammar.query.TableReferenceByExpression;
import org.sql.generation.api.grammar.query.TableReferenceByName;
import org.sql.generation.api.grammar.query.TableReferencePrimary;
import org.sql.generation.api.grammar.query.joins.JoinCondition;
import org.sql.generation.api.grammar.query.joins.NamedColumnsJoin;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface TableReferenceFactory
{
    public TableReferenceByName table( TableName tableName );

    public TableReferenceByName table( TableName tableName, TableAlias alias );

    public TableName tableName( String tableName );

    public TableName tableName( String schemaName, String tableName );

    public TableAlias tableAlias( String tableNameAlias );

    public TableAlias tableAliasWithCols( String tableNameAlias, String... colNames );

    public TableReferenceByExpression table( QueryExpression query );

    public TableReferenceByExpression table( QueryExpression query, TableAlias alias );

    public TableReferenceBuilder tableBuilder( TableReferencePrimary firstTable );

    public JoinCondition jc( BooleanExpression condition );

    public NamedColumnsJoin nc( ColumnNameList columnNames );

}

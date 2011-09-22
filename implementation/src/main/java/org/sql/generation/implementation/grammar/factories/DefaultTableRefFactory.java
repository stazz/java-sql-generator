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

import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.builders.query.TableReferenceBuilder;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.common.TableNameFunction;
import org.sql.generation.api.grammar.literals.SQLFunctionLiteral;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.grammar.query.TableAlias;
import org.sql.generation.api.grammar.query.TableReferenceByExpression;
import org.sql.generation.api.grammar.query.TableReferenceByName;
import org.sql.generation.api.grammar.query.TableReferencePrimary;
import org.sql.generation.api.grammar.query.joins.JoinCondition;
import org.sql.generation.api.grammar.query.joins.NamedColumnsJoin;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.builders.query.TableReferenceBuilderImpl;
import org.sql.generation.implementation.grammar.common.TableNameDirectImpl;
import org.sql.generation.implementation.grammar.common.TableNameFunctionImpl;
import org.sql.generation.implementation.grammar.query.TableAliasImpl;
import org.sql.generation.implementation.grammar.query.TableReferenceByExpressionImpl;
import org.sql.generation.implementation.grammar.query.TableReferenceByNameImpl;
import org.sql.generation.implementation.grammar.query.joins.JoinConditionImpl;
import org.sql.generation.implementation.grammar.query.joins.NamedColumnsJoinImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultTableRefFactory extends AbstractTableRefFactory
{

    public DefaultTableRefFactory( SQLVendor vendor, SQLProcessorAggregator processor )
    {
        super( vendor, processor );
    }

    public JoinCondition jc( BooleanExpression condition )
    {
        return new JoinConditionImpl( this.getProcessor(), condition );
    }

    public NamedColumnsJoin nc( ColumnNameList columnNames )
    {
        return new NamedColumnsJoinImpl( this.getProcessor(), columnNames );
    }

    public TableReferenceByName table( TableName tableName, TableAlias alias )
    {
        return new TableReferenceByNameImpl( this.getProcessor(), tableName, alias );
    }

    public TableNameDirect tableName( String schemaName, String tableName )
    {
        return new TableNameDirectImpl( this.getProcessor(), schemaName, tableName );
    }

    public TableNameFunction tableName( String schemaName, SQLFunctionLiteral function )
    {
        return new TableNameFunctionImpl( this.getProcessor(), schemaName, function );
    }

    public TableAlias tableAliasWithCols( String tableNameAlias, String... colNames )
    {
        ColumnNameList colNameList = null;
        if( colNames.length > 0 )
        {
            colNameList = this.getVendor().getColumnsFactory().colNames( colNames );
        }

        return new TableAliasImpl( this.getProcessor(), tableNameAlias, colNameList );
    }

    public TableReferenceByExpression table( QueryExpression query, TableAlias alias )
    {
        return new TableReferenceByExpressionImpl( this.getProcessor(), query, alias );
    }

    public TableReferenceBuilder tableBuilder( TableReferencePrimary firstTable )
    {
        return new TableReferenceBuilderImpl( this.getProcessor(), firstTable );
    }

}

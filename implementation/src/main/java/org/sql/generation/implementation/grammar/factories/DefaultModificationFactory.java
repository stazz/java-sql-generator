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

import org.sql.generation.api.grammar.builders.modification.ColumnSourceByValuesBuilder;
import org.sql.generation.api.grammar.builders.modification.DeleteBySearchBuilder;
import org.sql.generation.api.grammar.builders.modification.InsertStatementBuilder;
import org.sql.generation.api.grammar.builders.modification.UpdateBySearchBuilder;
import org.sql.generation.api.grammar.common.ColumnNameList;
import org.sql.generation.api.grammar.common.TableNameDirect;
import org.sql.generation.api.grammar.common.ValueExpression;
import org.sql.generation.api.grammar.modification.ColumnSourceByQuery;
import org.sql.generation.api.grammar.modification.SetClause;
import org.sql.generation.api.grammar.modification.TargetTable;
import org.sql.generation.api.grammar.modification.UpdateSource;
import org.sql.generation.api.grammar.modification.UpdateSourceByExpression;
import org.sql.generation.api.grammar.query.QueryExpression;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.grammar.builders.modification.ColumnSourceByValuesBuilderImpl;
import org.sql.generation.implementation.grammar.builders.modification.DeleteBySearchBuilderImpl;
import org.sql.generation.implementation.grammar.builders.modification.InsertStatementBuilderImpl;
import org.sql.generation.implementation.grammar.builders.modification.UpdateBySearchBuilderImpl;
import org.sql.generation.implementation.grammar.modification.ColumnSourceByQueryImpl;
import org.sql.generation.implementation.grammar.modification.SetClauseImpl;
import org.sql.generation.implementation.grammar.modification.TargetTableImpl;
import org.sql.generation.implementation.grammar.modification.UpdateSourceByExpressionImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class DefaultModificationFactory extends AbstractModificationFactory
{

    public DefaultModificationFactory( SQLVendor vendor, SQLProcessorAggregator processor )
    {
        super( vendor, processor );
    }

    public ColumnSourceByValuesBuilder columnSourceByValues()
    {
        return new ColumnSourceByValuesBuilderImpl( this.getProcessor() );
    }

    public ColumnSourceByQuery columnSourceByQuery( ColumnNameList columnNames, QueryExpression query )
    {
        return new ColumnSourceByQueryImpl( this.getProcessor(), columnNames, query );
    }

    public DeleteBySearchBuilder deleteBySearch()
    {
        return new DeleteBySearchBuilderImpl( this.getProcessor(), this.getVendor().getBooleanFactory()
            .booleanBuilder() );
    }

    public InsertStatementBuilder insert()
    {
        return new InsertStatementBuilderImpl( this.getProcessor() );
    }

    public UpdateBySearchBuilder updateBySearch()
    {
        return new UpdateBySearchBuilderImpl( this.getProcessor(), this.getVendor().getBooleanFactory()
            .booleanBuilder() );
    }

    public TargetTable createTargetTable( TableNameDirect tableName, Boolean isOnly )
    {
        return new TargetTableImpl( this.getProcessor(), isOnly, tableName );
    }

    public UpdateSourceByExpression updateSourceByExp( ValueExpression expression )
    {
        return new UpdateSourceByExpressionImpl( this.getProcessor(), expression );
    }

    public SetClause setClause( String updateTarget, UpdateSource updateSource )
    {
        return new SetClauseImpl( this.getProcessor(), updateTarget, updateSource );
    }
}

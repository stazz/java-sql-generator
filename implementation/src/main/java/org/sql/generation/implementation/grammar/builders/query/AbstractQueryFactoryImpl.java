/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
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

package org.sql.generation.implementation.grammar.builders.query;

import org.sql.generation.api.grammar.builders.query.AbstractQueryBuilder;
import org.sql.generation.api.grammar.common.NonBooleanExpression;
import org.sql.generation.api.grammar.query.LimitSpecification;
import org.sql.generation.api.grammar.query.OffsetSpecification;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.query.LimitSpecificationImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public abstract class AbstractQueryFactoryImpl<ExpressionType> extends SQLBuilderBase
        implements AbstractQueryBuilder<ExpressionType>
{

    private LimitSpecification _limit;
    private OffsetSpecification _offset;

    protected AbstractQueryFactoryImpl( SQLProcessorAggregator processor )
    {
        super( processor );
    }

    public AbstractQueryBuilder<ExpressionType> limit()
    {
        this._limit = new LimitSpecificationImpl( this.getProcessor(), null );
        return this;
    }

    public AbstractQueryBuilder<ExpressionType> limit( Integer max )
    {
        return this.limit( this.getProcessor().getVendor().getLiteralFactory().n( max ) );
    }

    public AbstractQueryBuilder<ExpressionType> limit( NonBooleanExpression max )
    {
        this._limit =
            ( max == null ? null : this.getProcessor().getVendor().getQueryFactory().limit( max ) );
        return this;
    }

    public AbstractQueryBuilder<ExpressionType> offset( Integer skip )
    {
        return this.offset( this.getProcessor().getVendor().getLiteralFactory().n( skip ) );
    }

    public AbstractQueryBuilder<ExpressionType> offset( NonBooleanExpression skip )
    {
        this._offset =
            ( skip == null ? null : this.getProcessor().getVendor().getQueryFactory().offset( skip ) );
        return this;
    }

    protected LimitSpecification getLimit()
    {
        return this._limit;
    }

    protected OffsetSpecification getOffset()
    {
        return this._offset;
    }

}

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

package org.sql.generation.implementation.grammar.builders.query;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.query.QueryBuilder;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.query.CorrespondingSpec;
import org.sql.generation.api.grammar.query.QueryExpressionBody;
import org.sql.generation.api.grammar.query.SetOperation;
import org.sql.generation.implementation.grammar.common.SQLBuilderBase;
import org.sql.generation.implementation.grammar.query.QueryExpressionBodyBinaryImpl;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class QueryBuilderImpl extends SQLBuilderBase
        implements QueryBuilder {

    public static final SetQuantifier DEFAULT_SET_QUANTIFIER_FOR_UNIONS = SetQuantifier.DISTINCT;

    public static final SetQuantifier DEFAULT_SET_QUANTIFIER_FOR_INTERSECTIONS = SetQuantifier.DISTINCT;

    public static final SetQuantifier DEFAULT_SET_QUANTIFIER_FOR_EXCEPTS = SetQuantifier.DISTINCT;

    private final SetQuantifier _defaultSetQuantifierForUnions;

    private final SetQuantifier _defaultSetQuantifierForIntersections;

    private final SetQuantifier _defaultSetQuantifierForExcepts;

    private QueryExpressionBody _topLevelExpression;

    public QueryBuilderImpl(final SQLProcessorAggregator processor, final QueryExpressionBody topLevelExpression) {
        this(processor, topLevelExpression, QueryBuilderImpl.DEFAULT_SET_QUANTIFIER_FOR_UNIONS,
                QueryBuilderImpl.DEFAULT_SET_QUANTIFIER_FOR_INTERSECTIONS, QueryBuilderImpl.DEFAULT_SET_QUANTIFIER_FOR_EXCEPTS);
    }

    protected QueryBuilderImpl(final SQLProcessorAggregator processor, final QueryExpressionBody topLevelExpression,
                               final SetQuantifier defaultSetQuantifierForUnions, final SetQuantifier defaultSetQuantifierForIntersections,
                               final SetQuantifier defaultSetQuantifierForExcepts) {
        super(processor);
        NullArgumentException.validateNotNull("default quantifier for unions", defaultSetQuantifierForUnions);
        NullArgumentException.validateNotNull("default quantifier for intersections",
                defaultSetQuantifierForIntersections);
        NullArgumentException.validateNotNull("default quantifier for excepts", defaultSetQuantifierForExcepts);
        NullArgumentException.validateNotNull("top level expression", topLevelExpression);

        this._defaultSetQuantifierForExcepts = defaultSetQuantifierForExcepts;
        this._defaultSetQuantifierForIntersections = defaultSetQuantifierForIntersections;
        this._defaultSetQuantifierForUnions = defaultSetQuantifierForUnions;
        this._topLevelExpression = topLevelExpression;
    }

    @Override
    public QueryBuilder union(final QueryExpressionBody another) {
        return this.union(this._defaultSetQuantifierForUnions, another);
    }

    @Override
    public QueryBuilder union(final CorrespondingSpec correspondingSpec, final QueryExpressionBody another) {
        return this.union(this._defaultSetQuantifierForUnions, correspondingSpec, another);
    }

    @Override
    public QueryBuilder union(final SetQuantifier setQuantifier, final QueryExpressionBody another) {
        return this.union(setQuantifier, null, another);
    }

    @Override
    public QueryBuilder union(final SetQuantifier setQuantifier, final CorrespondingSpec correspondingSpec,
                              final QueryExpressionBody another) {
        this._topLevelExpression = new QueryExpressionBodyBinaryImpl(this.getProcessor(), SetOperation.UNION,
                this._topLevelExpression, another, setQuantifier, correspondingSpec);
        return this;
    }

    @Override
    public QueryBuilder intersect(final QueryExpressionBody another) {
        return this.intersect(this._defaultSetQuantifierForIntersections, another);
    }

    @Override
    public QueryBuilder intersect(final CorrespondingSpec correspondingSpec, final QueryExpressionBody another) {
        return this.intersect(this._defaultSetQuantifierForIntersections, correspondingSpec, another);
    }

    @Override
    public QueryBuilder intersect(final SetQuantifier setQuantifier, final QueryExpressionBody another) {
        return this.intersect(setQuantifier, null, another);
    }

    @Override
    public QueryBuilder intersect(final SetQuantifier setQuantifier, final CorrespondingSpec correspondingSpec,
                                  final QueryExpressionBody another) {
        this._topLevelExpression = new QueryExpressionBodyBinaryImpl(this.getProcessor(), SetOperation.INTERSECT,
                this._topLevelExpression, another, setQuantifier, correspondingSpec);
        return this;
    }

    @Override
    public QueryBuilder except(final QueryExpressionBody another) {
        return this.except(this._defaultSetQuantifierForExcepts, another);
    }

    @Override
    public QueryBuilder except(final CorrespondingSpec correspondingSpec, final QueryExpressionBody another) {
        return this.except(this._defaultSetQuantifierForExcepts, correspondingSpec, another);
    }

    @Override
    public QueryBuilder except(final SetQuantifier setQuantifier, final QueryExpressionBody another) {
        return this.except(setQuantifier, null, another);
    }

    @Override
    public QueryBuilder except(final SetQuantifier setQuantifier, final CorrespondingSpec correspondingSpec,
                               final QueryExpressionBody another) {
        this._topLevelExpression = new QueryExpressionBodyBinaryImpl(this.getProcessor(), SetOperation.EXCEPT,
                this._topLevelExpression, another, setQuantifier, correspondingSpec);
        return this;
    }

    @Override
    public QueryExpressionBody createExpression() {
        return this._topLevelExpression;
    }
}

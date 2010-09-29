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
import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.query.CorrespondingSpec;
import org.sql.generation.api.grammar.query.QueryExpressionBody;
import org.sql.generation.api.grammar.query.QueryExpressionBodyBinary;
import org.sql.generation.api.grammar.query.SetOperation;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class QueryExpressionBodyBinaryImpl extends QueryExpressionBodyImpl<QueryExpressionBodyBinary>
    implements QueryExpressionBodyBinary
{

    private final SetOperation _setOperation;

    private final QueryExpressionBody _left;

    private final QueryExpressionBody _right;

    private final SetQuantifier _setQuantifier;

    private final CorrespondingSpec _correspondingColumns;

    public QueryExpressionBodyBinaryImpl( SetOperation setOperation, QueryExpressionBody left,
        QueryExpressionBody right, SetQuantifier setQuantifier, CorrespondingSpec correspondingColumns )
    {
        this( QueryExpressionBodyBinary.class, setOperation, left, right, setQuantifier, correspondingColumns );
    }

    protected QueryExpressionBodyBinaryImpl( Class<? extends QueryExpressionBodyBinary> implClass,
        SetOperation setOperation, QueryExpressionBody left, QueryExpressionBody right, SetQuantifier setQuantifier,
        CorrespondingSpec correspondingColumns )
    {
        super( implClass );
        NullArgumentException.validateNotNull( "set operation", setOperation );
        NullArgumentException.validateNotNull( "left", left );
        NullArgumentException.validateNotNull( "right", right );
        NullArgumentException.validateNotNull( "set quantifier", setQuantifier );

        this._setOperation = setOperation;
        this._left = left;
        this._right = right;
        this._correspondingColumns = correspondingColumns;
        this._setQuantifier = setQuantifier;
    }

    public QueryExpressionBody getLeft()
    {
        return this._left;
    }

    public QueryExpressionBody getRight()
    {
        return this._right;
    }

    public SetOperation getSetOperation()
    {
        return this._setOperation;
    }

    public CorrespondingSpec getCorrespondingColumns()
    {
        return this._correspondingColumns;
    }

    public SetQuantifier getSetQuantifier()
    {
        return this._setQuantifier;
    }

    @Override
    protected boolean doesEqual( QueryExpressionBodyBinary another )
    {
        return this._setOperation.equals( another.getSetOperation() )
            && this._setQuantifier.equals( another.getSetQuantifier() )
            && TypeableImpl.bothNullOrEquals( this._correspondingColumns, another.getCorrespondingColumns() )
            && this._left.equals( another.getLeft() ) && this._right.equals( another.getRight() );
    }
}

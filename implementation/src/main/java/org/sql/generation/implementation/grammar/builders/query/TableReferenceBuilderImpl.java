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

import java.util.ArrayList;
import java.util.List;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.builders.query.TableReferenceBuilder;
import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.api.grammar.query.TableReferencePrimary;
import org.sql.generation.api.grammar.query.joins.JoinSpecification;
import org.sql.generation.api.grammar.query.joins.JoinType;
import org.sql.generation.api.grammar.query.joins.JoinedTable;
import org.sql.generation.implementation.grammar.builders.internal.AbstractJoinInfo;
import org.sql.generation.implementation.grammar.builders.internal.CrossJoinInfo;
import org.sql.generation.implementation.grammar.builders.internal.NaturalJoinInfo;
import org.sql.generation.implementation.grammar.builders.internal.QualifiedJoinInfo;
import org.sql.generation.implementation.grammar.builders.internal.UnionJoinInfo;
import org.sql.generation.implementation.grammar.query.joins.CrossJoinedTableImpl;
import org.sql.generation.implementation.grammar.query.joins.NaturalJoinedTableImpl;
import org.sql.generation.implementation.grammar.query.joins.QualifiedJoinedTableImpl;
import org.sql.generation.implementation.grammar.query.joins.UnionJoinedTableImpl;

public class TableReferenceBuilderImpl
    implements TableReferenceBuilder
{

    private final TableReferencePrimary _startingTable;

    private final List<AbstractJoinInfo> _joinInfos;

    public TableReferenceBuilderImpl( TableReferencePrimary startingTable )
    {
        NullArgumentException.validateNotNull( "starting table", startingTable );

        this._startingTable = startingTable;
        this._joinInfos = new ArrayList<AbstractJoinInfo>();
    }

    public TableReferenceBuilder addQualifiedJoin( JoinType joinType, TableReference right,
        JoinSpecification joinSpec )
    {
        this._joinInfos.add( new QualifiedJoinInfo( right, joinType, joinSpec ) );
        return this;
    }

    public TableReferenceBuilder addCrossJoin( TableReferencePrimary right )
    {
        this._joinInfos.add( new CrossJoinInfo( right ) );
        return this;
    }

    public TableReferenceBuilder addNaturalJoin( JoinType joinType, TableReferencePrimary right )
    {
        this._joinInfos.add( new NaturalJoinInfo( right, joinType ) );
        return this;
    }

    public TableReferenceBuilder addUnionJoin( TableReferencePrimary right )
    {
        this._joinInfos.add( new UnionJoinInfo( right ) );
        return this;
    }

    public TableReference createExpression()
    {
        TableReference result = this._startingTable;
        for( Integer x = 0; x < this._joinInfos.size(); ++x )
        {
            result = this.createJoinedTable( result, this._joinInfos.get( x ) );
        }

        return result;
    }

    private JoinedTable createJoinedTable( TableReference left, AbstractJoinInfo info )
    {
        JoinedTable result = null;
        if( info instanceof CrossJoinInfo )
        {
            result = new CrossJoinedTableImpl( left, ((CrossJoinInfo) info).getTable() );
        }
        else if( info instanceof NaturalJoinInfo )
        {
            NaturalJoinInfo nInfo = (NaturalJoinInfo) info;
            result = new NaturalJoinedTableImpl( left, nInfo.getTable(), nInfo.getJoinType() );
        }
        else if( info instanceof QualifiedJoinInfo )
        {
            QualifiedJoinInfo qInfo = (QualifiedJoinInfo) info;
            result = new QualifiedJoinedTableImpl( left, qInfo.getTable(), qInfo.getJoinType(), qInfo.getJoinSpec() );
        }
        else if( info instanceof UnionJoinInfo )
        {
            result = new UnionJoinedTableImpl( left, ((UnionJoinInfo) info).getTable() );
        }
        else
        {
            throw new IllegalArgumentException( "Don't know what to do with " + info + "." );
        }

        return result;
    }

}
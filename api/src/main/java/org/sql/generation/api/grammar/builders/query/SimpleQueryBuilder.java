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

package org.sql.generation.api.grammar.builders.query;

import org.sql.generation.api.grammar.booleans.BooleanExpression;
import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.common.TableName;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.query.QueryExpression;

/**
 * This is builder to quickly create simple queries, without the generic and not-so-easy-to-use methods of traditional
 * {@link QuerySpecificationBuilder} + {@link QueryFactory} style. Using this builder is generally not thread-safe.
 * 
 * @author Stanislav Muhametsin
 */
public interface SimpleQueryBuilder
    extends AbstractBuilder<QueryExpression>
{

    /**
     * Adds the specified columns to the {@code SELECT} list.
     * 
     * @param columnNames The names of the columns.
     * @return This builder.
     */
    public SimpleQueryBuilder select( String... columnNames );

    /**
     * Adds alias to the most recently added column.
     * 
     * @param columnAlias The alias for most recently added column;
     * @return This builder.
     */
    public SimpleQueryBuilder as( String columnAlias );

    /**
     * Adds table names for {@code FROM} clause of this query.
     * 
     * @param tableNames The table names to add.
     * @return This builder.
     */
    public SimpleQueryBuilder from( TableName... tableNames );

    /**
     * Sets the search condition ({@code WHERE} clause) for this query.
     * 
     * @param searchCondition The search condition for this query.
     * @return This builder.
     */
    public SimpleQueryBuilder where( BooleanExpression searchCondition );

    /**
     * Adds {@code GROUP BY} columns for this query.
     * 
     * @param columns The column names for {@code GROUP BY} clause.
     * @return This builder.
     */
    public SimpleQueryBuilder groupBy( String... columns );

    /**
     * Adds {@code HAVING} grouping condition for this query.
     * 
     * @param groupingCondition The grouping condition for this query.
     * @return This builder.
     */
    public SimpleQueryBuilder having( BooleanExpression groupingCondition );

    /**
     * Adds {@code ORDER BY} columns for this query, with {@code ASC} as ordering specification.
     * 
     * @param columns The column names for {@code ORDER BY}.
     * @return This builder.
     */
    public SimpleQueryBuilder orderByAsc( String... columns );

    /**
     * Adds {@code ORDER BY} columns for this query, with {@code DESC} as ordering specification.
     * 
     * @param columns The column names for {@code ORDER BY}.
     * @return This builder.
     */
    public SimpleQueryBuilder orderByDesc( String... columns );
}

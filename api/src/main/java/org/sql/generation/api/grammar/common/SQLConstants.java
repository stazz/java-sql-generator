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

package org.sql.generation.api.grammar.common;

/**
 * A container for common textual constants of SQL language.
 * 
 * @author Stanislav Muhametsin
 */
public interface SQLConstants
{
    public static final String SELECT = "SELECT";

    public static final String FROM = "FROM";

    public static final String WHERE = "WHERE";

    public static final String GROUP_BY = "GROUP BY";

    public static final String HAVING = "HAVING";

    public static final String ORDER_BY = "ORDER BY";

    public static final String TABLE_COLUMN_SEPARATOR = ".";

    public static final String SCHEMA_TABLE_SEPARATOR = ".";

    public static final String TOKEN_SEPARATOR = " ";

    public static final String AND = "AND";

    public static final String OR = "OR";

    public static final String NOT = "NOT";

    public static final String ASTERISK = "*";

    public static final String COMMA = ",";

    public static final String PERIOD = ".";

    public static final String QUESTION_MARK = "?";

    public static final String OPEN_PARENTHESIS = "(";

    public static final String CLOSE_PARENTHESIS = ")";

    public static final String ALIAS_DEFINER = "AS";

    public static final String NEWLINE = "\n";

    public static final String NULL = "NULL";

    public static final String IS = "IS";

    public static final String CREATE = "CREATE";

    public static final String OFFSET_PREFIX = "OFFSET";

    public static final String OFFSET_POSTFIX = "ROWS";

    public static final String LIMIT_PREFIX = "FETCH FIRST";

    public static final String LIMIT_POSTFIX = "ROWS ONLY";
}

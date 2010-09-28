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

package org.sql.generation.implementation.transformation.pgsql;

import java.util.HashMap;
import java.util.Map;

import org.lwdci.api.context.single.Typeable;
import org.sql.generation.api.grammar.booleans.BinaryPredicate;
import org.sql.generation.api.grammar.booleans.NotRegexpPredicate;
import org.sql.generation.api.grammar.booleans.RegexpPredicate;
import org.sql.generation.api.grammar.query.pgsql.PgSQLQuerySpecification;
import org.sql.generation.implementation.transformation.SQLStatementProcessor;
import org.sql.generation.implementation.transformation.BooleanExpressionProcessing.BinaryPredicateProcessor;
import org.sql.generation.implementation.transformation.pgsql.QueryProcessing.PgSQLQuerySpecificationProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessor;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class PostgreSQLStatementProcessor extends SQLStatementProcessor
{

    private static final Map<Class<? extends Typeable<?>>, SQLProcessor> _defaultProcessors;

    private static final Map<Class<? extends BinaryPredicate>, String> _defaultPgSQLBinaryOperators;

    static
    {
        Map<Class<? extends BinaryPredicate>, String> binaryOperators = new HashMap<Class<? extends BinaryPredicate>, String>(
            SQLStatementProcessor.getDefaultBinaryOperators() );
        binaryOperators.put( RegexpPredicate.class, "~" );
        binaryOperators.put( NotRegexpPredicate.class, "!~" );
        _defaultPgSQLBinaryOperators = binaryOperators;

        Map<Class<? extends Typeable<?>>, SQLProcessor> processors = new HashMap<Class<? extends Typeable<?>>, SQLProcessor>(
            SQLStatementProcessor.getDefaultProcessors() );
        processors.put( RegexpPredicate.class,
            new BinaryPredicateProcessor( _defaultPgSQLBinaryOperators.get( RegexpPredicate.class ) ) );
        processors.put( NotRegexpPredicate.class,
            new BinaryPredicateProcessor( _defaultPgSQLBinaryOperators.get( NotRegexpPredicate.class ) ) );
        processors.put( PgSQLQuerySpecification.class, new PgSQLQuerySpecificationProcessor() );
        _defaultProcessors = processors;
    }

    public PostgreSQLStatementProcessor()
    {
        super( _defaultProcessors );
    }
}

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

package org.sql.generation.implementation.grammar.booleans;

import org.sql.generation.api.grammar.booleans.NotRegexpPredicate;
import org.sql.generation.api.grammar.common.NonBooleanExpression;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class NotRegexpPredicateImpl extends BinaryPredicateImpl<NotRegexpPredicate>
    implements NotRegexpPredicate
{

    public NotRegexpPredicateImpl( NonBooleanExpression left, NonBooleanExpression right )
    {
        this( NotRegexpPredicate.class, left, right );
    }

    protected NotRegexpPredicateImpl( Class<? extends NotRegexpPredicate> predicateClass, NonBooleanExpression left,
        NonBooleanExpression right )
    {
        super( predicateClass, left, right );
    }

}

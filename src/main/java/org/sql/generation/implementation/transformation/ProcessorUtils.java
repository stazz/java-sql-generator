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

package org.sql.generation.implementation.transformation;

import org.sql.generation.api.grammar.common.SQLConstants;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.manipulation.DropBehaviour;

/**
 * @author Stanislav Muhametsin
 */
public class ProcessorUtils {
    public static Boolean notNullAndNotEmpty(final String str) {
        return str != null && str.trim().length() > 0;
    }

    public static void processSetQuantifier(final SetQuantifier quantifier, final StringBuilder builder) {
        if (quantifier == SetQuantifier.ALL) {
            builder.append("ALL");
        } else {
            builder.append("DISTINCT");
        }
    }

    public static void processDropBehaviour(final DropBehaviour behaviour, final StringBuilder builder) {
        builder.append(SQLConstants.TOKEN_SEPARATOR);
        if (behaviour == DropBehaviour.CASCADE) {
            builder.append("CASCADE");
        } else {
            builder.append("RESTRICT");
        }
    }
}

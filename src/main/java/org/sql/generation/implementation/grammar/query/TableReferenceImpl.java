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

import org.atp.api.Typeable;
import org.sql.generation.api.grammar.query.TableReference;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public abstract class TableReferenceImpl<TypeableType extends Typeable<? super TypeableType>, RealType extends TypeableType>
        extends SQLSyntaxElementBase<TypeableType, RealType>
        implements TableReference {

    public TableReferenceImpl(final SQLProcessorAggregator processor, final Class<? extends RealType> expressionClass) {
        super(processor, expressionClass);
    }

    @Override
    public Typeable<?> asTypeable() {
        return this;
    }

}

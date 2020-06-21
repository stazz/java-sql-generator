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

package org.sql.generation.implementation.grammar.manipulation;

import org.sql.generation.api.common.NullArgumentException;
import org.sql.generation.api.grammar.common.SchemaStatement;
import org.sql.generation.api.grammar.manipulation.DropBehaviour;
import org.sql.generation.api.grammar.manipulation.DropStatement;
import org.sql.generation.api.grammar.manipulation.ObjectType;
import org.sql.generation.implementation.grammar.common.SQLSyntaxElementBase;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author Stanislav Muhametsin
 */
public class DropStatementImpl<DropStatementType extends DropStatement> extends
        SQLSyntaxElementBase<SchemaStatement, DropStatementType>
        implements DropStatement {

    private final DropBehaviour _dropBehaviour;
    private final ObjectType _whatToDrop;

    protected DropStatementImpl(final SQLProcessorAggregator processor,
                                final Class<? extends DropStatementType> realImplementingType, final ObjectType whatToDrop, final DropBehaviour dropBehaviour) {
        super(processor, realImplementingType);

        NullArgumentException.validateNotNull("What to drop", whatToDrop);
        NullArgumentException.validateNotNull("Drop behaviour", dropBehaviour);

        this._whatToDrop = whatToDrop;
        this._dropBehaviour = dropBehaviour;
    }

    @Override
    protected boolean doesEqual(final DropStatementType another) {
        return this._dropBehaviour.equals(another.getDropBehaviour())
                && this._whatToDrop.equals(another.whatToDrop());
    }

    @Override
    public DropBehaviour getDropBehaviour() {
        return this._dropBehaviour;
    }

    @Override
    public ObjectType whatToDrop() {
        return this._whatToDrop;
    }

}

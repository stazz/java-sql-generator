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

import java.util.List;

import org.sql.generation.api.grammar.builders.AbstractBuilder;
import org.sql.generation.api.grammar.common.SetQuantifier;
import org.sql.generation.api.grammar.query.ColumnReference;
import org.sql.generation.api.grammar.query.ColumnReferences.ColumnReferenceInfo;
import org.sql.generation.api.grammar.query.SelectColumnClause;

/**
 * 
 * @author Stanislav Muhametsin
 */
public interface ColumnsBuilder
    extends AbstractBuilder<SelectColumnClause>
{
    public ColumnsBuilder addUnnamedColumns( ColumnReference... columns );

    public ColumnsBuilder addNamedColumns( ColumnReferenceInfo... namedColumns );

    public ColumnsBuilder setSetQuantifier( SetQuantifier newSetQuantifier );

    public ColumnsBuilder selectAll();

    public List<ColumnReferenceInfo> getColumns();

    public SetQuantifier getSetQuantifier();

}

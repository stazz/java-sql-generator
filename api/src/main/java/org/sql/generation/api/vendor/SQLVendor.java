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

package org.sql.generation.api.vendor;

import org.sql.generation.api.grammar.common.SQLStatement;
import org.sql.generation.api.grammar.factories.BooleanFactory;
import org.sql.generation.api.grammar.factories.ColumnsFactory;
import org.sql.generation.api.grammar.factories.DataTypeFactory;
import org.sql.generation.api.grammar.factories.DefinitionFactory;
import org.sql.generation.api.grammar.factories.LiteralFactory;
import org.sql.generation.api.grammar.factories.ManipulationFactory;
import org.sql.generation.api.grammar.factories.ModificationFactory;
import org.sql.generation.api.grammar.factories.QueryFactory;
import org.sql.generation.api.grammar.factories.TableReferenceFactory;
import org.sql.generation.api.grammar.query.QuerySpecification;

/**
 * This is the base API interface for all SQLVendors to implement. It gives the means to acquire factories to create SQL
 * syntax elements. Additionally, the vendor creates the textual representation of these syntax elements so that the
 * query may be passed directly to database, for example, through JDBC.
 * 
 * @author Stanislav Muhametsin
 * @see MySQLVendor
 * @see PostgreSQLVendor
 * @see QueryFactory
 * @see BooleanFactory
 * @see TableReferenceFactory
 * @see LiteralFactory
 * @see ColumnsFactory
 * @see ModificationFactory
 * @see #toString(SQLStatement)
 */
public interface SQLVendor
{

    /**
     * Returns the query factory of this vendor.
     * 
     * @return The query factory of this vendor.
     * @see QueryFactory
     * @see QuerySpecification
     */
    public QueryFactory getQueryFactory();

    /**
     * Returns the boolean factory for this vendor.
     * 
     * @return The boolean factory for this vendor.
     * @see BooleanFactory
     */
    public BooleanFactory getBooleanFactory();

    /**
     * Returns the table reference factory for this vendor.
     * 
     * @return The table reference factory for this vendor.
     * @see TableReferenceFactory
     */
    public TableReferenceFactory getTableReferenceFactory();

    /**
     * Returns the literal factory for this vendor.
     * 
     * @return The literal factory for this vendor.
     * @see LiteralFactory
     */
    public LiteralFactory getLiteralFactory();

    /**
     * Returns the columns factory for this vendor.
     * 
     * @return The columns factory for this vendor.
     * @see ColumnsFactory
     */
    public ColumnsFactory getColumnsFactory();

    /**
     * Returns the factory to create modification statements for this vendor.
     * 
     * @return The factory to create modification statements for this vendor.
     * @see ModificationFactory
     */
    public ModificationFactory getModificationFactory();

    /**
     * Returns the factory to create definition statements ({@code CREATE SCHEMA/TABLE/VIEW/etc}) for this vendor.
     * 
     * @return The factory to create definition statements for this vendor.
     * @see DefinitionFactory
     */
    public DefinitionFactory getDefinitionFactory();

    /**
     * Returns the factory to create manipulation statements ({@code DROP/ALTER SCHEMA/TABLE/etc}) for this vendor.
     * 
     * @return The factory to create manipulation statements.
     * @see ManipulationFactory
     */
    public ManipulationFactory getManipulationFactory();

    /**
     * Returns the factory to create syntax elements for SQL data types compatible with this vendor.
     * 
     * @return The factory to create syntax elements for SQL data types.
     * @see DataTypeFactory
     */
    public DataTypeFactory getDataTypeFactory();

    /**
     * Takes the {@link SQLStatement} (typically either a query, or a modification statement) and produces a textual SQL
     * statement out of it.
     * 
     * @param statement The {@link SQLStatement} to process.
     * @return The textual SQL statement to be used directly with database.
     * @see SQLStatement
     */
    public String toString( SQLStatement statement );
}

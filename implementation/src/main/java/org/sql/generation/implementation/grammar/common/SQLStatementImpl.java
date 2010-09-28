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

package org.sql.generation.implementation.grammar.common;

import java.util.logging.Logger;

import org.lwdci.api.context.EmptyExecutionArgs;
import org.lwdci.api.context.single.Typeable;
import org.lwdci.spi.context.single.skeletons.TypeableImpl;
import org.sql.generation.api.grammar.common.SQLStatement;
import org.sql.generation.api.vendor.SQLVendor;
import org.sql.generation.implementation.transformation.spi.SQLTransformation;
import org.sql.generation.implementation.transformation.spi.SQLTransformationContextCreationArgs;
import org.sql.generation.implementation.transformation.spi.SQLTransformationProvider;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class SQLStatementImpl<StatementType extends Typeable<StatementType>, ActualType extends StatementType> extends
    TypeableImpl<StatementType, ActualType>
    implements SQLStatement
{

    private static final Logger _log = Logger.getLogger( SQLStatementImpl.class.getName() );

    public SQLStatementImpl( Class<? extends ActualType> expressionClass )
    {
        super( expressionClass );
    }

    public String toString( SQLVendor vendor )
    {
        return toString( vendor, this );
    }

    public static String toString( SQLVendor vendor, SQLStatement query )
    {
        SQLTransformation transform = SQLTransformationProvider.getTransformation();
        String result = transform.createContext( new SQLTransformationContextCreationArgs( vendor, query ) )
            .interaction( EmptyExecutionArgs.EMPTY_ARGS );
        // _log.info( "Generated SQL query:" + "\n" + result );
        return result;
    }
}

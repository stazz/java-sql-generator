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

import java.util.List;
import java.util.Map;

import org.sql.generation.api.grammar.common.SetQuantifier;

/**
 * 
 * @author Stanislav Muhametsin
 */
public class ProcessorUtils
{
    public static Boolean notNullAndNotEmpty( String str )
    {
        return str != null && str.trim().length() > 0;
    }

    public static <K, V> Map<K, V> mapFromValues( List<K> keys, List<V> values, Map<K, V> map )
    {
        for( Integer x = 0; x < keys.size(); ++x )
        {
            map.put( keys.get( x ), values.get( x ) );
        }

        return map;
    }

    public static void unknownType( Object type, String typeKind )
    {
        throw new IllegalArgumentException( "Could not process " + typeKind + ":" + (type == null ? "[null]" : type)
            + "." );
    }

    public static void processSetQuantifier( SetQuantifier quantifier, StringBuilder builder )
    {
        if( quantifier == SetQuantifier.ALL )
        {
            builder.append( "ALL" );
        }
        else
        {
            builder.append( "DISTINCT" );
        }
    }
}

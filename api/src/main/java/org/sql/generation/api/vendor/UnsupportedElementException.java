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

/**
 * This exception will typically be thrown by
 * {@link SQLVendor#toString(org.sql.generation.api.grammar.common.SQLStatement)} method when the vendor encounters a
 * SQL syntax element that the vendor doesn't understand.
 * 
 * @author Stanislav Muhametsin
 */
public class UnsupportedElementException extends RuntimeException
{

    private static final long serialVersionUID = -5331011803322815958L;

    private final Object _element;

    public UnsupportedElementException( String msg )
    {
        this( msg, null );
    }

    public UnsupportedElementException( String msg, Object element )
    {
        super( msg );
        this._element = element;
    }

    public Object getElement()
    {
        return this._element;
    }
}

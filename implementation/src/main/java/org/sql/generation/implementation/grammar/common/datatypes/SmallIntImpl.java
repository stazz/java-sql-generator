package org.sql.generation.implementation.grammar.common.datatypes;

import org.sql.generation.api.grammar.common.datatypes.SmallInt;

/**
 * 
 * 
 * @author Stanislav Muhametsin
 */
public final class SmallIntImpl
    implements SmallInt
{
    public SmallIntImpl()
    {
    }

    /**
     * Returns {@link SmallInt}.
     */
    public Class<SmallInt> getImplementedType()
    {
        return SmallInt.class;
    }

    /**
     * The singleton instance of {@code SMALLINT}.
     */
    public static final SmallInt INSTANCE = new SmallIntImpl();
}
package org.sql.generation.implementation.grammar.common.datatypes;

import org.sql.generation.api.grammar.common.datatypes.BigInt;

/**
 * 
 * 
 * @author Stanislav Muhametsin
 */
public final class BigIntImpl
    implements BigInt
{
    public BigIntImpl()
    {
    }

    /**
     * Returns {@link BigInt}.
     */
    public Class<BigInt> getImplementedType()
    {
        return BigInt.class;
    }

    /**
     * The singleton instance of {@code BIGINT}.
     */
    public static final BigInt INSTANCE = new BigIntImpl();
}
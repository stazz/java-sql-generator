package org.sql.generation.implementation.grammar.common.datatypes;

import org.sql.generation.api.grammar.common.datatypes.SQLInteger;

/**
 * @author Stanislav Muhametsin
 */
public final class SQLIntegerImpl
        implements SQLInteger {
    public SQLIntegerImpl() {
    }

    /**
     * Returns {@link SQLInteger}.
     */
    @Override
    public Class<SQLInteger> getImplementedType() {
        return SQLInteger.class;
    }

    /**
     * The singleton instance of {@code INTEGER}.
     */
    public static final SQLInteger INSTANCE = new SQLIntegerImpl();
}
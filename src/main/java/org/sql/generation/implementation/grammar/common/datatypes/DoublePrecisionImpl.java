package org.sql.generation.implementation.grammar.common.datatypes;

import org.sql.generation.api.grammar.common.datatypes.DoublePrecision;

public final class DoublePrecisionImpl
        implements DoublePrecision {
    public DoublePrecisionImpl() {
    }

    /**
     * Returns {@link DoublePrecision}.
     */
    @Override
    public Class<DoublePrecision> getImplementedType() {
        return DoublePrecision.class;
    }

    /**
     * A singleton instance of {@code DOUBLE PRECISION} data type.
     */
    public static final DoublePrecision INSTANCE = new DoublePrecisionImpl();
}
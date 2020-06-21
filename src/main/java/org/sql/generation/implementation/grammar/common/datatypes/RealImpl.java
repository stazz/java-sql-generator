package org.sql.generation.implementation.grammar.common.datatypes;

import org.sql.generation.api.grammar.common.datatypes.Real;

public final class RealImpl
        implements Real {
    public RealImpl() {
    }

    /**
     * Returns {@link Real}.
     */
    @Override
    public Class<Real> getImplementedType() {
        return Real.class;
    }

    /**
     * A singleton instance of {@code REAL}.
     */
    public static final Real INSTANCE = new RealImpl();
}
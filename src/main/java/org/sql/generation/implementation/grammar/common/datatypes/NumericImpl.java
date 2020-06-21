package org.sql.generation.implementation.grammar.common.datatypes;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.grammar.common.datatypes.Numeric;
import org.sql.generation.api.grammar.common.datatypes.SQLDataType;

/**
 * @author Stanislav Muhametsin
 */
public final class NumericImpl extends TypeableImpl<SQLDataType, Numeric>
        implements Numeric {
    private final Integer _precision;
    private final Integer _scale;

    public NumericImpl(final Integer precision, final Integer scale) {
        super(Numeric.class);
        this._precision = precision;
        this._scale = scale;
    }

    @Override
    protected boolean doesEqual(final Numeric another) {
        return TypeableImpl.bothNullOrEquals(this._precision, another.getPrecision())
                && TypeableImpl.bothNullOrEquals(this._scale, another.getScale());
    }

    /**
     * Returns the precision (first integer) for this {@code NUMERIC}.
     *
     * @return The precision for this {@code NUMERIC}.
     */
    @Override
    public Integer getPrecision() {
        return this._precision;
    }

    /**
     * Returns the scale (second integer) for this {@code NUMERIC}.
     *
     * @return The precision for this {@code NUMERIC}.
     */
    @Override
    public Integer getScale() {
        return this._scale;
    }

    /**
     * This instance represents {@code NUMERIC} without precision and scale.
     */
    public static final Numeric PLAIN_NUMERIC = new NumericImpl(null, null);
}
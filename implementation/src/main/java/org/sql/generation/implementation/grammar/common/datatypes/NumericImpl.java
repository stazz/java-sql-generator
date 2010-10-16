package org.sql.generation.implementation.grammar.common.datatypes;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.grammar.common.datatypes.Numeric;
import org.sql.generation.api.grammar.common.datatypes.SQLDataType;

/**
 * 
 * 
 * @author Stanislav Muhametsin
 */
public final class NumericImpl extends TypeableImpl<SQLDataType, Numeric>
    implements Numeric
{
    private final Integer _precision;
    private final Integer _scale;

    public NumericImpl( Integer precision, Integer scale )
    {
        super( Numeric.class );
        this._precision = precision;
        this._scale = scale;
    }

    @Override
    protected boolean doesEqual( Numeric another )
    {
        return bothNullOrEquals( this._precision, another.getPrecision() )
            && bothNullOrEquals( this._scale, another.getScale() );
    }

    /**
     * Returns the precision (first integer) for this {@code NUMERIC}.
     * 
     * @return The precision for this {@code NUMERIC}.
     */
    public Integer getPrecision()
    {
        return this._precision;
    }

    /**
     * Returns the scale (second integer) for this {@code NUMERIC}.
     * 
     * @return The precision for this {@code NUMERIC}.
     */
    public Integer getScale()
    {
        return this._scale;
    }

    /**
     * This instance represents {@code NUMERIC} without precision and scale.
     */
    public static final NumericImpl PLAIN_NUMERIC = new NumericImpl( null, null );
}
package org.sql.generation.implementation.grammar.common.datatypes;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.grammar.common.datatypes.SQLDataType;
import org.sql.generation.api.grammar.common.datatypes.SQLFloat;

/**
 * 
 * 
 * @author Stanislav Muhametsin
 */
public final class SQLFloatImpl extends TypeableImpl<SQLDataType, SQLFloat>
    implements SQLFloat
{
    private final Integer _precision;

    public SQLFloatImpl( Integer precision )
    {
        super( SQLFloat.class );
        this._precision = precision;
    }

    @Override
    protected boolean doesEqual( SQLFloat another )
    {
        return bothNullOrEquals( this._precision, another.getPrecision() );
    }

    /**
     * Returns the precision for this {@code FLOAT}.
     * 
     * @return The precision for this {@code FLOAT}.
     */
    public Integer getPrecision()
    {
        return this._precision;
    }

    /**
     * This instance represents {@code FLOAT} without precision.
     */
    public static final SQLFloat PLAIN_FLOAT = new SQLFloatImpl( null );
}
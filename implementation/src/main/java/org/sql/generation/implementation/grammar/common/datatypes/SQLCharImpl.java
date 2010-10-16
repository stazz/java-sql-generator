package org.sql.generation.implementation.grammar.common.datatypes;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.grammar.common.datatypes.SQLChar;
import org.sql.generation.api.grammar.common.datatypes.SQLDataType;

public final class SQLCharImpl extends TypeableImpl<SQLDataType, SQLChar>
    implements SQLChar
{
    private final Boolean _isVarying;
    private final Integer _length;

    public SQLCharImpl( Boolean isVarying, Integer length )
    {
        super( SQLChar.class );
        this._isVarying = isVarying;
        this._length = length;
    }

    @Override
    protected boolean doesEqual( SQLChar another )
    {
        return this._isVarying.equals( another.isVarying() ) && bothNullOrEquals( this._length, another.getLength() );
    }

    /**
     * Returns {@code true} if this is {@code CHARACTER VARYING}; {@code false otherwise}.
     * 
     * @return {@code true} if this is {@code CHARACTER VARYING}; {@code false otherwise}.
     */
    public Boolean isVarying()
    {
        return this._isVarying;
    }

    /**
     * Returns the length specification for this {@code CHARACTER} or {@code CHARACTER VARYING}. Returns {@code null} if
     * none specified.
     * 
     * @return The length for this {@code CHARACTER} or {@code CHARACTER VARYING}.
     */
    public Integer getLength()
    {
        return this._length;
    }

    public static final SQLChar PLAIN_FIXED = new SQLCharImpl( false, null );

    public static final SQLChar PLAIN_VARYING = new SQLCharImpl( true, null );
}
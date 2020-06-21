package org.sql.generation.implementation.grammar.common.datatypes;

import org.atp.spi.TypeableImpl;
import org.sql.generation.api.grammar.common.datatypes.SQLDataType;
import org.sql.generation.api.grammar.common.datatypes.SQLTimeStamp;

/**
 * This class represents {@code TIMESTAMP} data type.
 *
 * @author Stanislav Muhametsin
 */
public final class SQLTimeStampImpl extends TypeableImpl<SQLDataType, SQLTimeStamp>
        implements SQLTimeStamp {
    private final Integer _precision;
    private final Boolean _withTimeZone;

    public SQLTimeStampImpl(final Integer precision, final Boolean withTimeZone) {
        super(SQLTimeStamp.class);
        this._precision = precision;
        this._withTimeZone = withTimeZone;
    }

    @Override
    protected boolean doesEqual(final SQLTimeStamp another) {
        return TypeableImpl.bothNullOrEquals(this._precision, another.getPrecision())
                && TypeableImpl.bothNullOrEquals(this._withTimeZone, another.isWithTimeZone());
    }

    /**
     * Returns the precision for this {@code TIMESTAMP}. May be {@code null}.
     *
     * @return The precision for this {@code TIMESTAMP}.
     */
    @Override
    public Integer getPrecision() {
        return this._precision;
    }

    /**
     * Returns whether the {@code TIMESTAMP} should be with time zone. May be {@code null} if no choice specified.
     *
     * @return
     */
    @Override
    public Boolean isWithTimeZone() {
        return this._withTimeZone;
    }

    public static final SQLTimeStamp PLAIN_TIMESTAMP = new SQLTimeStampImpl(null, null);

    public static final SQLTimeStamp PLAIN_TIMESTAMP_WITHOUT_TZ = new SQLTimeStampImpl(null, false);

    public static final SQLTimeStamp PLAIN_TIMESTAMP_WITH_TZ = new SQLTimeStampImpl(null, true);
}
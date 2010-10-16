package org.sql.generation.implementation.grammar.common.datatypes;

import org.sql.generation.api.grammar.common.datatypes.SQLDate;

public final class SQLDateImpl
    implements SQLDate
{
    public SQLDateImpl()
    {
    }

    /**
     * Returns {@link SQLDate}.
     */
    public Class<SQLDate> getImplementedType()
    {
        return SQLDate.class;
    }

    /**
     * A singleton instance of {@code DATE} data type.
     */
    public static final SQLDate INSTANCE = new SQLDateImpl();
}
package org.sql.generation.implementation.grammar.common.datatypes;

import org.sql.generation.api.grammar.common.datatypes.SQLBoolean;

/**
 * 
 * 
 * @author Stanislav Muhametsin
 */
public final class SQLBooleanImpl
    implements SQLBoolean
{
    public SQLBooleanImpl()
    {

    }

    /**
     * Returns {@link SQLBoolean}.
     */
    public Class<SQLBoolean> getImplementedType()
    {
        return SQLBoolean.class;
    }

    /**
     * The singleton instance of {@code BOOLEAN}.
     */
    public static final SQLBoolean INSTANCE = new SQLBooleanImpl();
}
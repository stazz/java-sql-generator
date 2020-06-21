/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.sql.generation.implementation.transformation.pgsql;

import org.sql.generation.api.vendor.PostgreSQLVendor;
import org.sql.generation.implementation.transformation.QueryProcessing.LimitSpecificationProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.OffsetSpecificationProcessor;
import org.sql.generation.implementation.transformation.QueryProcessing.QuerySpecificationProcessor;
import org.sql.generation.implementation.transformation.spi.SQLProcessorAggregator;

/**
 * @author 2011 Stanislav Muhametsin
 */
public class QueryProcessing {

    private static final String LEGACY_LIMIT_PREFIX = "LIMIT";
    private static final String LEGACY_LIMIT_POSTFIX = null;
    private static final String LEGACY_OFFSET_PREFIX = "OFFSET";
    private static final String LEGACY_OFFSET_POSTFIX = null;

    public static class PgSQLQuerySpecificationProcessor extends QuerySpecificationProcessor {
        @Override
        protected boolean isOffsetBeforeLimit(final SQLProcessorAggregator processor) {
            return !((PostgreSQLVendor) processor.getVendor()).legacyOffsetAndLimit();
        }
    }

    public static class PgSQLOffsetSpecificationProcessor extends OffsetSpecificationProcessor {
        @Override
        protected String getPrefix(final SQLProcessorAggregator processor) {
            return ((PostgreSQLVendor) processor.getVendor()).legacyOffsetAndLimit() ? QueryProcessing.LEGACY_OFFSET_PREFIX : super
                    .getPrefix(processor);
        }

        @Override
        protected String getPostfix(final SQLProcessorAggregator processor) {
            return ((PostgreSQLVendor) processor.getVendor()).legacyOffsetAndLimit() ? QueryProcessing.LEGACY_OFFSET_POSTFIX : super
                    .getPostfix(processor);
        }
    }

    public static class PgSQLLimitSpecificationProcessor extends LimitSpecificationProcessor {
        @Override
        protected String getPrefix(final SQLProcessorAggregator processor) {
            return ((PostgreSQLVendor) processor.getVendor()).legacyOffsetAndLimit() ? QueryProcessing.LEGACY_LIMIT_PREFIX : super
                    .getPrefix(processor);
        }

        @Override
        protected String getPostfix(final SQLProcessorAggregator processor) {
            return ((PostgreSQLVendor) processor.getVendor()).legacyOffsetAndLimit() ? QueryProcessing.LEGACY_LIMIT_POSTFIX : super
                    .getPostfix(processor);
        }
    }
}

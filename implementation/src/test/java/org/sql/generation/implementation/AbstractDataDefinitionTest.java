/*
 * Copyright (c) 2010, Stanislav Muhametsin. All Rights Reserved.
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

package org.sql.generation.implementation;

import org.junit.Test;
import org.sql.generation.api.grammar.builders.definition.TableDefinitionBuilder;
import org.sql.generation.api.grammar.builders.definition.TableElementListBuilder;
import org.sql.generation.api.grammar.definition.table.ConstraintCharacteristics;
import org.sql.generation.api.grammar.definition.table.MatchType;
import org.sql.generation.api.grammar.definition.table.ReferentialAction;
import org.sql.generation.api.grammar.definition.table.UniqueSpecification;
import org.sql.generation.api.grammar.factories.DefinitionFactory;
import org.sql.generation.api.grammar.factories.TableReferenceFactory;
import org.sql.generation.api.vendor.SQLVendor;

/**
 * 
 * @author Stanislav Muhametsin
 */
public abstract class AbstractDataDefinitionTest extends AbstractSQLSyntaxTest
{

    @Test
    public void definition1()
        throws Exception
    {
        // @formatter:off
        /*
         * CREATE TABLE qi4j_schema.qname_9
         * (
         * qname_id integer NOT NULL,
         * entity_pk bigint NOT NULL,
         * parent_qname integer,
         * collection_path ltree NOT NULL,
         * qname_value integer,
         * CONSTRAINT qname_9_pkey PRIMARY KEY (qname_id, entity_pk),
         * CONSTRAINT qname_9_parent_qname_fkey FOREIGN KEY (parent_qname, entity_pk)
         * REFERENCES qi4j_schema.all_qnames (qname_id, entity_pk) MATCH SIMPLE
         * ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED,
         * CONSTRAINT qname_9_qname_id_fkey FOREIGN KEY (qname_id, entity_pk)
         * REFERENCES qi4j_schema.all_qnames (qname_id, entity_pk) MATCH SIMPLE
         * ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED,
         * CONSTRAINT qname_9_qname_value_fkey FOREIGN KEY (qname_value)
         * REFERENCES qi4j_schema.used_classes (used_class_id) MATCH SIMPLE
         * ON UPDATE CASCADE ON DELETE RESTRICT
         * )
         */

        SQLVendor vendor = this.getVendor();
        TableReferenceFactory t = vendor.getTableReferenceFactory();
        DefinitionFactory d = vendor.getDefinitionFactory();
        String schemaName = "qi4j_schema";

        TableDefinitionBuilder builder = d.createTableDefinitionBuilder();
        builder
            .setTableName( t.tableName( schemaName, "qname_9" ) );
        
        TableElementListBuilder cBuilder = d.createTableElementListBuilder();
        cBuilder
            .addTableElement( d.createColumnDefinition( "qname_id", "integer", false ) )
            .addTableElement( d.createColumnDefinition( "entity_pk", "bigint", false ) )
            .addTableElement( d.createColumnDefinition( "parent_qname", "integer" ) )
            .addTableElement( d.createColumnDefinition( "collection_path", "ltree", false ) )
            .addTableElement( d.createColumnDefinition( "qname_value", "integer" ) )
            .addTableElement( d.createTableConstraintDefinition( "qname_9_pkey", 
                d.createUniqueConstraintBuilder()
                .setUniqueness( UniqueSpecification.PRIMARY_KEY )
                .addColumns( "qname_id", "entity_pk" )
                .createExpression() )
                )
            .addTableElement(
                d.createTableConstraintDefinition(
                    "qname_9_parent_qname_fkey",
                    d.createForeignKeyConstraintBuilder()
                        .addSourceColumns( "parent_qname", "entity_pk" )
                        .setTargetTableName( t.tableName( schemaName, "all_qnames" ) )
                        .addTargetColumns( "qname_id", "entity_pk" )
                        .setMatchType( MatchType.SIMPLE )
                        .setOnUpdate( ReferentialAction.CASCADE )
                        .setOnDelete( ReferentialAction.CASCADE )
                        .createExpression(),
                    ConstraintCharacteristics.INITIALLY_DEFERRED_DEFERRABLE
                    )
                )
            .addTableElement(
                d.createTableConstraintDefinition(
                    "qname_9_qname_id_fkey",
                    d.createForeignKeyConstraintBuilder()
                        .addSourceColumns( "qname_id", "entity_pk" )
                        .setTargetTableName( t.tableName( schemaName, "all_qnames" ) )
                        .addTargetColumns( "qname_id", "entity_pk" )
                        .setMatchType( MatchType.SIMPLE )
                        .setOnUpdate( ReferentialAction.CASCADE )
                        .setOnDelete( ReferentialAction.CASCADE )
                        .createExpression(),
                    ConstraintCharacteristics.INITIALLY_DEFERRED_DEFERRABLE
                    )
                )
            .addTableElement(
                d.createTableConstraintDefinition(
                    "qname_9_qname_value_fkey",
                    d.createForeignKeyConstraintBuilder()
                        .addSourceColumns( "qname_value" )
                        .setTargetTableName( t.tableName( schemaName, "used_classes" ) )
                        .addTargetColumns( "used_class_id" )
                        .setMatchType( MatchType.SIMPLE )
                        .setOnUpdate( ReferentialAction.CASCADE )
                        .setOnDelete( ReferentialAction.RESTRICT )
                        .createExpression()
                    )
                );
        
        builder.setTableContentsSource( cBuilder.createExpression() );
        
        this.logStatement( "Table definition", vendor, builder.createExpression() );
        
        // @formatter:on
    }
}

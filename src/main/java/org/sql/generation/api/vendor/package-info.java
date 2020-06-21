/**
 * This package provides an API for SQLVendors to implement, and a way to load different vendors dynamically. The vendor will give access to factories in package org.sql.generation.api.grammar.factories , which will enable users to create SQL syntax elements. Vendor is also responsible for creating a textual representation (String) of a SQL syntax structure generated with these factories.
 */
package org.sql.generation.api.vendor;
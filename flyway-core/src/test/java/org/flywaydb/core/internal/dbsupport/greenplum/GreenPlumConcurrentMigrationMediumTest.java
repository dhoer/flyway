/*
 * Copyright 2010-2017 Boxfuse GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flywaydb.core.internal.dbsupport.greenplum;

import org.flywaydb.core.migration.ConcurrentMigrationTestCase;
import org.flywaydb.core.internal.util.jdbc.DriverDataSource;
import org.junit.experimental.categories.Category;
import org.flywaydb.core.DbCategory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Test to demonstrate the migration functionality using GreenPlum.
 */
@Category(DbCategory.GreenPlum.class)
public class GreenPlumConcurrentMigrationMediumTest extends ConcurrentMigrationTestCase {
    @Override
    protected DataSource createDataSource(Properties customProperties) {
        String user = customProperties.getProperty("greenplum.user", "flyway");
        // Password must be at least 8 characters, with upper and lower case:
        String password = customProperties.getProperty("greenplum.password", "Flyway123");
        // Create an ssh tunnel on port 5439 to your Redshift instance before running this test!
        String url = customProperties.getProperty("greenplum.url", "jdbc:pivotal:greenplum://localhost:5432;DatabaseName=flyway;");

        return new DriverDataSource(Thread.currentThread().getContextClassLoader(), null, url, user, password);
    }
}
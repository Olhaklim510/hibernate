package com.company.hibernate.storage;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public void initDb() {
        Flyway flyway = Flyway
                .configure()
                .dataSource(StorageConstants.CONNECTION_URL, null, null)
                .load();

        flyway.migrate();
    }

    public static void main(String[] args) {
        new DatabaseInitService().initDb();
    }
}

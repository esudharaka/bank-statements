package com.nagarrow.statements.repository.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface SimpleDataSource {
    Connection getConnection() throws SQLException;
}

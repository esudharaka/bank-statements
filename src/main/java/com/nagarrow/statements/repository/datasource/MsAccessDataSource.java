package com.nagarrow.statements.repository.datasource;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Component
public class MsAccessDataSource implements SimpleDataSource
{
    @Value("${datasource.msaccess.url}")
    private String userBucketPath;

    @Override
    public  Connection getConnection() throws SQLException {
        return DriverManager.getConnection(userBucketPath);
    }
}

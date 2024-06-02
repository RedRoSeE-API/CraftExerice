package org.example.craftexercise.model;

import org.example.craftexercise.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class DBUtilTest {

    private DBUtil dbUtil;

    @BeforeEach
    void setUp() {
        dbUtil = new DBUtil();
    }

    @Test
    void testConnectionToDB() {
        // Mock the DriverManager and Connection
        try (MockedStatic<DriverManager> mockedDriverManager = Mockito.mockStatic(DriverManager.class)) {
            Connection mockConnection = mock(Connection.class);
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                    .thenReturn(mockConnection);

            // Call the method to test
            Connection connection = dbUtil.ConnectionToDB();

            // Verify the connection
            assertNotNull(connection);
            assertEquals(mockConnection, connection);
        }
    }
}
package org.example.craftexercise.service;

import org.example.craftexercise.model.DBUserInformationModel;
import org.example.craftexercise.model.FreshdeskModel;
import org.example.craftexercise.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DBServiceTest {

    @Mock
    private DBUtil dbUtil;

    @Mock
    private Connection dbConnection;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet resultSet;

    private DBService dbService;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        dbService = new DBService();
        when(dbUtil.ConnectionToDB()).thenReturn(dbConnection);
        when(dbConnection.createStatement()).thenReturn(statement);
    }

    @Test
    void testGetUserInformationFromDB() throws SQLException {
        // Mock the behavior of ResultSet
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false); // Simulate one row in the ResultSet
        when(resultSet.getString("name")).thenReturn("Test User");
        when(resultSet.getString("email")).thenReturn("test@example.com");
        when(resultSet.getString("twitter_id")).thenReturn("test");
        when(resultSet.getString("unique_external_id")).thenReturn("test");
        when(resultSet.getTimestamp("creation_time")).thenReturn(null);

        //Insert the new information
        int userAddInfo = dbService.addNewUserInformationInDB(new FreshdeskModel("Test User", "test@example.com", "test", "test"));

        // Call the method to test
        List<DBUserInformationModel> userInfo = dbService.getUserInformationFromDB();

        // Verify the result
        assertNotNull(userInfo);

        DBUserInformationModel user = userInfo.get(userInfo.size() - 1);
        assertTrue(user instanceof DBUserInformationModel);
        assertEquals("Test User", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("test", user.getTwitter_id());
        assertEquals("test", user.getUnique_external_id());

    }

    @Test
    void testAddNewUserInformationInDB() throws SQLException {
        // Mock the behavior of executeUpdate
        when(statement.executeUpdate(anyString())).thenReturn(1); // Assuming one row is affected

        // Create a sample FreshdeskModel
        FreshdeskModel model = new FreshdeskModel();
        model.setName("Test User");
        model.setEmail("test@example.com");
        model.setTwitter_id("@test");
        model.setUnique_external_id("12345");

        // Call the method to test
        int rowsAffected = dbService.addNewUserInformationInDB(model);

        // Verify the result
        assertEquals(1, rowsAffected);
    }
}


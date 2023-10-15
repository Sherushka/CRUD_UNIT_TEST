package org.springframework.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectorTest {
    private Connection connection;

    @BeforeEach
    public void setup() {
        try {
            connection = DatabaseConnector.getConnection();
        } catch (SQLException e) {
            fail("Failed to establish a database connection: " + e.getMessage());
        }
    }

    @AfterEach
    public void cleanup() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            fail("Failed to close the database connection: " + e.getMessage());
        }
    }

    @Test
    public void testConnectionNotNull() {
        assertNotNull(connection);
    }

    @Test
    public void testConnectionIsOpen() {
        try {
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            fail("Failed to check if the connection is open: " + e.getMessage());
        }
    }

    @Test
    public void testConnectionIsValid() {
        try {
            assertTrue(connection.isValid(5));
        } catch (SQLException e) {
            fail("Failed to check if the connection is valid: " + e.getMessage());
        }
    }
}

package org.springframework.boot;

import org.junit.jupiter.api.*;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class CRUDOperationsIntegrationTest {

    private static CRUDOperations crud;

    @BeforeAll
    public static void setUp() {
        crud = new CRUDOperations();
    }

    @AfterEach
    public void tearDown() {
        try {
            crud.deleteAllRecords();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCRUDOperations() {
        // Create
        try {
            int id = 1;
            String name = "Эдди";
            crud.createRecord(name, id);

            String data = crud.readRecord(id);
            assertEquals(name, data);

            String newName = "Мэтью";
            crud.updateRecord(id, newName);

            String updatedData = crud.readRecord(id);
            assertEquals(newName, updatedData);

            crud.deleteRecord(id);

            String deletedData = crud.readRecord(id);
            assertNull(deletedData);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQL Exception occurred during test");
        }
    }

    @Test
    public void testGetRecordCount() {
        try {
            int initialCount = crud.getRecordCount();
            int id = 1;
            String name = "Эдди";

            crud.createRecord(name, id);

            int newCount = crud.getRecordCount();
            assertEquals(initialCount + 1, newCount);

            crud.deleteRecord(id);


            int finalCount = crud.getRecordCount();
            assertEquals(initialCount, finalCount);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQL Exception occurred during test");
        }
    }
}

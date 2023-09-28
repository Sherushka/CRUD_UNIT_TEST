package org.springframework.boot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CRUDOperationsSmokeTest {
    private CRUDOperations crud;

    @Before
    public void setUp() {
        crud = new CRUDOperations();
    }

    @After
    public void tearDown() throws SQLException {
        crud.deleteAllRecords();
    }

    @Test
    public void testCRUDOperations() throws SQLException {
        int id = 1;
        String name = "Эдди";
        crud.createRecord(name, id);

        String data = crud.readRecord(1);
        assertEquals("Эдди", data);

        crud.updateRecord(1, "Мэтью");
        String updatedData = crud.readRecord(1);
        assertEquals("Мэтью", updatedData);

        crud.deleteRecord(1);
        String deletedData = crud.readRecord(1);
        assertNull(deletedData);
    }

    @Test
    public void testGetRecordCount() throws SQLException {
        int initialCount = crud.getRecordCount();

        crud.createRecord("Name 1", 1);

        int newCount = crud.getRecordCount();
        assertEquals(initialCount + 1, newCount);
    }
}
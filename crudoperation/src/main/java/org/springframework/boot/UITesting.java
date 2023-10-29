package org.springframework.boot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UITesting {

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
    public void testCreateRecord() {
        try {
            crud.createRecord("TestName", 1);
            String data = crud.readRecord(1);
            assertEquals("TestName", data);
        } catch (SQLException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testReadRecord() {
        try {
            crud.createRecord("ReadTestName", 2);
            String data = crud.readRecord(2);
            assertEquals("ReadTestName", data);
        } catch (SQLException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateRecord() {
        try {
            crud.createRecord("BeforeUpdateName", 3);
            crud.updateRecord(3, "AfterUpdateName");
            String data = crud.readRecord(3);
            assertEquals("AfterUpdateName", data);
        } catch (SQLException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteRecord() {
        try {
            crud.createRecord("ToDeleteName", 4);
            crud.deleteRecord(4);
            String data = crud.readRecord(4);
            assertNull(data);
        } catch (SQLException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetRecordCount() {
        try {
            int countBefore = crud.getRecordCount();
            crud.createRecord("CountName", 5);
            int countAfter = crud.getRecordCount();
            assertEquals(countBefore + 1, countAfter);
        } catch (SQLException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}

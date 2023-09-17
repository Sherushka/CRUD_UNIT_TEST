package org.springframework.boot;

import org.junit.jupiter.api.*;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class CRUDOperationsTest {

    private CRUDOperations crud;

    @BeforeEach
    public void setUp() {
        crud = new CRUDOperations();
    }

    @Test
    public void testCreateRecord() throws SQLException {
        crud.createRecord("Тестовое имя", 1);
        String data = crud.readRecord(1);
        assertEquals("Тестовое имя", data);
    }

    @Test
    public void testReadRecord() throws SQLException {
        // Предположим, что в базе данных уже есть запись с id=1 и именем "Мэтью"
        String data = crud.readRecord(1);
        assertEquals("Тестовое имя", data);
    }

    @Test
    public void testUpdateRecord() throws SQLException {
        // Предположим, что в базе данных уже есть запись с id=1 и именем "Мэтью"
        crud.updateRecord(1, "Новое имя");
        String data = crud.readRecord(1);
        assertEquals("Новое имя", data);
    }

    @Test
    public void testDeleteRecord() throws SQLException {
        // Предположим, что в базе данных есть запись с id=1
        crud.deleteRecord(1);
        String data = crud.readRecord(1);
        assertNull(data);
    }
}
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
        //тест функции Create
        crud.createRecord("Тестовое имя", 1);
        String data = crud.readRecord(1);
        assertEquals("Тестовое имя", data);
    }

    @Test
    public void testReadRecord() throws SQLException {
        //предполагаем, что первая функция создала в базе данных нужную нам строку и сравниваем, находится ли она там по id.
        String data = crud.readRecord(1);
        assertEquals("Тестовое имя", data);
    }

    @Test
    public void testUpdateRecord() throws SQLException {
        //тест функции Update
        crud.updateRecord(1, "Новое имя");
        String data = crud.readRecord(1);
        assertEquals("Новое имя", data);
    }

    @Test
    public void testDeleteRecord() throws SQLException {
        //тест функции Delete
        crud.deleteRecord(1);
        String data = crud.readRecord(1);
        assertNull(data);
    }
}

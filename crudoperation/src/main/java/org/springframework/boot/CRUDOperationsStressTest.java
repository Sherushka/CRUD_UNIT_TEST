package org.springframework.boot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CRUDOperationsStressTest {

    private static final int THREAD_COUNT = 10;
    private CRUDOperations crud;
    private AtomicInteger currentId = new AtomicInteger(20);

    @BeforeEach
    public void setUp() {
        crud = new CRUDOperations();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        crud.deleteAllRecords();
    }

    @Test
    public void stressTestCRUDOperations() throws InterruptedException, SQLException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                try {
                    for (int j = 1; j <= 100; j++) {
                        int id = currentId.getAndIncrement();
                        crud.createRecord("Name" + id, id);
                        String name = crud.readRecord(id);
                        assertEquals("Name" + id, name);
                        crud.updateRecord(id, "UpdatedName" + id);
                        name = crud.readRecord(id);
                        assertEquals("UpdatedName" + id, name);
                        crud.deleteRecord(id);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);

        assertEquals(0, crud.getRecordCount());
    }
}
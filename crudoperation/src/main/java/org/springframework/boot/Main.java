package org.springframework.boot;
import java.sql.SQLException;



public class Main {

    public static void main(String[] args) {
        CRUDOperations crud = new CRUDOperations();

        // Create
        try {
            int id = 1; 
            String name = "Эдди";
            crud.createRecord(name, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Read
        try {
            String data = crud.readRecord(1);
            System.out.println("Читаю первое имя: " + data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Update
        try {
            crud.updateRecord(1, "Мэтью");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Delete
     try {
            crud.deleteRecord(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package ru.senla.javacourse.tarasov.hotel.impl.service;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ru.senla.javacourse.tarasov.hotel.db.entity.Room;

public class PersistenceService {
    private static final String SAVE_FILE = "state.ser";

    public static void saveState(List<Room> rooms) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(rooms);
            System.out.println("State saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Room> loadState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            return (List<Room>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. Starting with empty state.");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package service;

import java.io.*;
import java.util.List;
import model.Room;

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

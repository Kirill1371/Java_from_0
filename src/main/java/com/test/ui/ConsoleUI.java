package com.test.ui;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final List<MenuItem> menuItems;

    public ConsoleUI(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println((i + 1) + ". " + menuItems.get(i).getTitle());
            }
            System.out.println((menuItems.size() + 1) + ". Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == menuItems.size() + 1) {
                System.out.println("Exiting...");
                return;
            }

            if (choice > 0 && choice <= menuItems.size()) {
                menuItems.get(choice - 1).execute(scanner);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}

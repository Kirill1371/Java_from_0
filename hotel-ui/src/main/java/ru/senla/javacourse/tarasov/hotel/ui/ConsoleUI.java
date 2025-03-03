package ru.senla.javacourse.tarasov.hotel.ui;

import java.util.List;
import java.util.Scanner;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.ioc.annotations.Inject;

@Component
public class ConsoleUI {

    private final List<MenuItem> menuItems;

    @Inject
    public ConsoleUI(MenuBuilder menuBuilder) {
        this.menuItems = menuBuilder.buildMenuItems();
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

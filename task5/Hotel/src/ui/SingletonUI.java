package ui;

public class SingletonUI {
    private static SingletonUI instance;
    private ConsoleUI consoleUI;

    private SingletonUI() {
        IUIFactory uiFactory = new UIFactory();
        consoleUI = uiFactory.createConsoleUI();
    }

    public static SingletonUI getInstance() {
        if (instance == null) {
            instance = new SingletonUI();
        }
        return instance;
    }

    public ConsoleUI getConsoleUI() {
        return consoleUI;
    }
}

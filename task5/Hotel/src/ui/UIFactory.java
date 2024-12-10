package ui;

public class UIFactory implements IUIFactory {
    @Override
    public ConsoleUI createConsoleUI() {
        return new ConsoleUI();
    }
}

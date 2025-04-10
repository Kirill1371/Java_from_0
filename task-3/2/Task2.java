package Task3;

public class Task2 {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(1000);

        // Создаем товары
        Product laptop = new Electronics("Laptop", 2.5);
        Product phone = new Electronics("Phone", 0.5);
        Product apple = new Food("Apple", 0.2);
        Product jeans = new Clothing("Jeans", 1.0);

        // Заполняем склад
        warehouse.addProduct(laptop);
        warehouse.addProduct(phone);
        warehouse.addProduct(apple);
        warehouse.addProduct(jeans);

        for (int i = 0; i < 500; i++) {
            warehouse.addProduct(new Food("Apple", 2.0));
        }

        System.out.println("Total weight of products in the warehouse: " + warehouse.getTotalWeight() + " kg");
    }
}

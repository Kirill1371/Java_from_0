package Task33;

import java.util.ArrayList;
import java.util.List;

abstract class Product {
    private String name;
    private double weight;

    public Product(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}


class Electronics extends Product {
    public Electronics(String name, double weight) {
        super(name, weight);
    }
}

class Food extends Product {
    public Food(String name, double weight) {
        super(name, weight);
    }
}

class Clothing extends Product {
    public Clothing(String name, double weight) {
        super(name, weight);
    }
}


class Sklad {
    private List<Product> products;
    private double maxCapacity;
    private double currentWeight;

    public Sklad(double maxCapacity) {
        this.products = new ArrayList<>();
        this.maxCapacity = maxCapacity;
        this.currentWeight = 0;
    }

    public boolean addProduct(Product product) {
        if (currentWeight + product.getWeight() <= maxCapacity) {
            products.add(product);
            currentWeight += product.getWeight();
            return true;
        } else {
            System.out.println("Cannot add product: " + product.getName() + ". Not enough capacity.");
            return false;
        }
    }

    public double getTotalWeight() {
        return currentWeight;
    }
}

public class t2 {
    public static void main(String[] args) {
        Sklad warehouse = new Sklad(1000);

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

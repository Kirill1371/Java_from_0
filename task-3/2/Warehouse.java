package Task3;

import java.util.ArrayList;
import java.util.List;

class Warehouse {
    private List<Product> products;
    private double maxCapacity;
    private double currentWeight;

    public Warehouse(double maxCapacity) {
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
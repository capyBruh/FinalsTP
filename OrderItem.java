public class OrderItem {
    private final String name;
    private final double price;

    public OrderItem(String name, double price) { //stores and passes the order item name and price
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - P" + String.format("%.2f", price);
    }
}

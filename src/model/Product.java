package model;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private long id;
    private String name;
    private double price;
    private String receiptDate;
    private String category;

    public Product(long id, String name, double price, String receiptDate, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.receiptDate = receiptDate;
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", receiptDate=" + receiptDate +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(receiptDate, product.receiptDate) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, receiptDate, category);
    }
}

package com.tss.model;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date date;
    private List<LineItem> items;

    public Order(int id, Date date, List<LineItem> items) {
        this.id = id;
        this.date = date;
        this.items = items;
    }

    public double calculateOrderPrice() {
        return items.stream().mapToDouble(LineItem::calculateLineItemCost).sum();
    }

    public int getId() { return id; }
    public Date getDate() { return date; }
    public List<LineItem> getItems() { return items; }
}
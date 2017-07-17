package zp.personal.shopping.bean;

import java.util.HashMap;
import java.util.Map;

public class Item {
    private int id;
    private String name;
    private int price;
    private int available;
    private Category category;

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public Item setId(int id) {
        this.id = id;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Item setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getAvailable() {
        if(this.available != 0) {
            return 1;
        }
        return 0;
    }

    public Item setAvailable(int available) {
        this.available = available;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Item setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("uri", "items/" + this.getId());
        map.put("name", this.getName());
        map.put("category", this.getCategory().getName());
        map.put("price", this.getPrice());
        map.put("available", this.getAvailable());
        return map;
    }
}

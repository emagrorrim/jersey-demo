package zp.personal.shopping.bean;

import java.util.HashMap;

public class Item {
    private int id;
    private String name;

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

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("name", this.getName());
        return map;
    }
}

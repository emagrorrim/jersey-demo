package zp.personal.shopping.bean;

import java.util.HashMap;

public class Item {
    private String name;

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", this.getName());
        return map;
    }
}

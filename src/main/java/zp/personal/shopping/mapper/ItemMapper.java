package zp.personal.shopping.mapper;

import zp.personal.shopping.bean.Item;

import java.util.List;

public interface ItemMapper {
    List<Item> getAllItems();
    Item getItemById(int id);
    int insertItem(Item item);
    int deleteItemById(int id);
    int updateItem(Item item);
}

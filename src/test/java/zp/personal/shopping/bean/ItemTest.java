package zp.personal.shopping.bean;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemTest {
    @Test
    public void creating_new_item_should_return_new_item_with_expected_properties() throws Exception {
        Item item = new Item()
            .setId(1)
            .setName("Apple");

        assertThat(item.getName(), is("Apple"));
        assertThat(item.getId(), is(1));
    }

    @Test
    public void toMap_should_return_a_map_contain_all_properties() throws Exception {
        Map<String, Object> itemMap = new Item()
            .setName("Apple")
            .toMap();
        assertThat(itemMap.get("name"), is("Apple"));
    }
}
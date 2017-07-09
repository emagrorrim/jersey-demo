package zp.personal.shopping.resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import zp.personal.shopping.bean.Item;
import zp.personal.shopping.mapper.ItemMapper;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemServiceTest {
    private ItemService itemService;

    @Mock
    private ItemMapper itemMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        itemService = new ItemService(itemMapper);
    }

    @Test
    public void getAllItems_should_return_404_when_no_items() throws Exception {
        when(itemMapper.getAllItems()).thenReturn(null);
        Response response = itemService.getAllItems();

        assertThat(response.getStatus(), is(404));
        assertThat(response.getEntity(), nullValue());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getAllItems_should_return_200_and_items_response() throws Exception {
        when(itemMapper.getAllItems()).thenReturn(mockedItems());
        Response response = itemService.getAllItems();

        assertThat(response.getStatus(), is(200));
        List<Item> responseItems = (ArrayList<Item>) response.getEntity();
        assertThat(responseItems.size(), is(2));
        assertThat(responseItems.get(0).getName(), is("Apple"));
        assertThat(responseItems.get(1).getName(), is("Knife"));
    }

    private List<Item> mockedItems() {
        List<Item> items = new ArrayList<>();
        Item apple = new Item().setName("Apple");
        Item knife = new Item().setName("Knife");
        items.add(apple);
        items.add(knife);
        return items;
    }
}
package zp.personal.shopping.bean;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemTest {
    @Mock
    private Category category;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void creating_new_item_should_return_new_item_with_expected_properties() throws Exception {
        Item item = new Item()
            .setId(1)
            .setName("Apple")
            .setCategory(category);

        assertThat(item.getName(), is("Apple"));
        assertThat(item.getId(), is(1));
        assertThat(item.getCategory(), is(category));
    }
}
package zp.personal.shopping;

import org.apache.ibatis.session.SqlSessionManager;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import zp.personal.shopping.Utils.DBUtil;
import zp.personal.shopping.mapper.ItemMapper;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("resource")
public class App extends ResourceConfig {
    public App() {
        SqlSessionManager session = DBUtil.getSession();
        final ItemMapper itemMapper = session.getMapper(ItemMapper.class);
        packages("zp.personal.shopping.resource")
            .register(new AbstractBinder() {
                @Override
                protected void configure() {
                        bind(itemMapper).to(ItemMapper.class);
                    }
            });
    }
}

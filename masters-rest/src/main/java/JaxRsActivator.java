import com.github.gpor89.masters.data.MemCache;
import com.github.gpor89.masters.rest.ReadInterceptor;
import com.github.gpor89.masters.rest.WriteInterceptor;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class JaxRsActivator extends ResourceConfig {

    private static final Logger LOG = LogManager.getLogger(JaxRsActivator.class.getName());

    public JaxRsActivator() {
        // Register resources and providers using package-scanning.
        packages("com.github.gpor89.masters.rest");
        register(ReadInterceptor.class);
        register(WriteInterceptor.class);
    }
}

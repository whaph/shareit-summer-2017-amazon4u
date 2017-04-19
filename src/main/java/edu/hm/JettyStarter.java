package edu.hm;
import edu.hm.server.*;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;


/**
 * Start the application without an AppServer like tomcat.
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class JettyStarter {

    public static final String APP_URL = "/";
    public static final int PORT = 8082;
    public static final String WEBAPP_DIR = "./src/main/webapp/";

    /**
     * Deploy local directories using Jetty without needing a container-based deployment.
     * @param args unused
     * @throws Exception might throw for several reasons.
     */
    public static void main(String... args) throws Exception {
        Server jetty = new Server(PORT);
        //Amazon4UServer server = new Amazon4UServer();
        //server.listen(8082);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        System.out.println("Jetty listening on port " + PORT);
        jetty.join();

    }
}

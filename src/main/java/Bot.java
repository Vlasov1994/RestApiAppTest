import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Bot {
    public static void main(String[] args) throws Exception {
        Frontend front = new Frontend();

        Server server = new Server(8080);
        ServletContextHandler context  = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);
        context.addServlet(new ServletHolder(front), "/api/webhook");

        server.start();
        server.join();
    }
}

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.FileWriter;
import java.io.IOException;

public class Bot {
    public static void main(String[] args) throws Exception {
            Frontend front = new Frontend();

            Server server = new Server(Integer.parseInt(System.getenv("PORT"))/*8080*/);
            ServletContextHandler context  = new ServletContextHandler(ServletContextHandler.SESSIONS);
            server.setHandler(context);
            context.addServlet(new ServletHolder(front), "/api/webhook");

            server.start();
            server.join();
    }

    public static void printExceptionInFile(String exception) {
        try(FileWriter writer = new FileWriter("notes3.txt", false))
        {
            writer.write(exception);
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

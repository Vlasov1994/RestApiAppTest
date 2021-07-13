import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Bot {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(
                URI.create("https://api.telegram.org/bot1012952375:AAEJXfg7OxuVIa7vzBmkzs9pFa3ZnnkAqWA/setWebhook?url=https://rest-api-app-test.herokuapp.com/api/webhook"))
                //.header("gay", "true")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Frontend front = new Frontend(client);
        front.postBody = response.body();
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

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Frontend extends HttpServlet {
    public String postBody = "Post Body is null";
    private HttpClient client;

    public Frontend(HttpClient client) {
        this.client = client;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.getWriter().print(postBody);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        postBody = "";
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine())!=null) {
            postBody += line + "\n";
        }

        JsonElement element = new JsonParser().parse(postBody);
        String chatId = element.getAsJsonObject().get("message").getAsJsonObject()
                .get("chat").getAsJsonObject()
                .get("id").getAsString();

        HttpRequest httpRequest = HttpRequest.newBuilder(
                URI.create("https://api.telegram.org/bot1012952375:AAEJXfg7OxuVIa7vzBmkzs9pFa3ZnnkAqWA/" +
                        "sendMessage?chat_id="+chatId+"&text=Hello"))
                //.header("gay", "true")
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            postBody += httpResponse.body();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class Frontend extends HttpServlet {
    public String postBody = "Post Body is null";

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
        System.out.println(postBody);
    }
}

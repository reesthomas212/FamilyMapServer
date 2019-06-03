package HANDLERS;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

/** Default handler
 *
 */
public class FileHandler implements HttpHandler
{
    /** Default handle method
     *
     * @param exchange
     * @throws IOException
     *
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        boolean check = false;

        try
        {
            String urlPath = "";
            if (exchange.getRequestMethod().toLowerCase().equals("get"))
            {
                urlPath = exchange.getRequestURI().getPath();
            }
            if (urlPath.length() == 0 || urlPath.equals("/"))
            {
                urlPath = "\\index.html";
            }

            String filePath = Paths.get("").toAbsolutePath() + "\\web" + urlPath;
            File file = new File(filePath);

            if (file.exists() && file.canRead())
            {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream respBody = exchange.getResponseBody();
                Files.copy(file.toPath(), respBody);
                respBody.close();
                check = true;
            }
            if (!check)
            {
                String path404 = Paths.get("").toAbsolutePath() + "\\web\\HTML\\404.html";
                File notfound = new File(path404);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream respBody = exchange.getResponseBody();
                Files.copy(notfound.toPath(), respBody);
                respBody.close();
            }
        }
        catch (IOException e)
        {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.close();
            e.printStackTrace();
        }
    }
}

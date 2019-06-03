package HANDLERS;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/** Class that handles all common code for API handlers
 *
 */
public class CommonHandler
{
    /** Writes something
     *
     * @param respBody
     * @param respData
     * @throws IOException
     *
     */
    public void writeString(OutputStream respBody, String respData) throws IOException
    {
        OutputStreamWriter sw = new OutputStreamWriter(respBody);
        sw.write(respData);
        sw.flush();
    }

    /** Sends response object to the client
     *
     * @param exchange
     * @param respData
     * @throws IOException
     *
     */
    public void sendResponse(HttpExchange exchange, String respData) throws IOException
    {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        OutputStream respBody = exchange.getResponseBody();
        writeString(respBody, respData);
        respBody.close();
    }

    /** Sends bad request response to client
     *
     * @param exchange
     * @throws IOException
     *
     */
    public void sendBadRequest(HttpExchange exchange) throws IOException
    {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        exchange.getResponseBody().close();
    }

    /** Sends server error response to client
     *
     * @param exchange
     * @throws IOException
     *
     */
    public void sendServerError(HttpExchange exchange) throws IOException
    {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        exchange.getResponseBody().close();
    }

    /** Checks if handles contain specific object IDs
     *
     * @param path
     * @return
     *
     */
    public boolean checkForID(String path)
    {
        String[] params = path.split("/");
        if (params.length == 2)
            return true;
        else
            return false;
    }
}

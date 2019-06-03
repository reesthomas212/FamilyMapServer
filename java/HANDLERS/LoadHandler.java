package HANDLERS;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import COMMUNICATION.LoadRequest;
import COMMUNICATION.LoadResult;
import SERVICES.LoadService;

/** Handles the Load API
 *
 */
public class LoadHandler implements HttpHandler
{
    /** CommonHandler instance that calls all shared code
     *
     */
    public CommonHandler c = new CommonHandler();

    /** Handle method for Load API
     *
     * @param exchange
     * @throws IOException
     *
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                Gson gson = new Gson();
                Reader reader = new InputStreamReader(exchange.getRequestBody());
                LoadRequest r = gson.fromJson(reader, LoadRequest.class);

                LoadResult loadResult = new LoadService().load(r);

                String respData = gson.toJson(loadResult);
                c.sendResponse(exchange, respData);

                success = true;
            }
            if (!success)
            {
                c.sendBadRequest(exchange);
            }
        }
        catch (IOException e)
        {
            c.sendServerError(exchange);
            e.printStackTrace();
        }
    }
}

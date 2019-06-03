package HANDLERS;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import COMMUNICATION.ClearResult;
import SERVICES.ClearService;

/** Handles the Clear API
 *
 */
public class ClearHandler implements HttpHandler
{
    /** CommonHandler instance that calls all shared code
     *
     */
    private CommonHandler c = new CommonHandler();

    /** Handle method for Clear API
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
                ClearResult clearResult = new ClearService().clear();

                Gson gson = new Gson();
                String respData = gson.toJson(clearResult);
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



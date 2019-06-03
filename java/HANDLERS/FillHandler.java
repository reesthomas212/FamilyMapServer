package HANDLERS;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import COMMUNICATION.FillRequest;
import COMMUNICATION.FillResult;
import SERVICES.FillService;

/** Handles the Fill API
 *
 */
public class FillHandler implements HttpHandler
{
    /** CommonHandler instance that calls all shared code
     *
     */
    public CommonHandler c = new CommonHandler();

    /** Handle method for Fill API
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
                //determine username and # of generations needed to be generated
                String ID = exchange.getRequestURI().getPath();
                ID = ID.substring(6, ID.length());
                String[] array = ID.split("/");
                String username = array[0];

                int generations = 4;
                FillResult fillResult;
                try {
                    if (array.length > 1)
                    {
                        generations = Integer.parseInt(array[1]);
                    }
                }
                catch (NumberFormatException e)
                {
                    fillResult = new FillResult();
                    fillResult.setMessage("Generations param not a number");
                    writeFillResult(fillResult, exchange);
                }
                finally
                {
                    if (generations < 1)
                    {
                        fillResult = new FillResult();
                        fillResult.setMessage("Generations param less than zero");
                        writeFillResult(fillResult, exchange);
                    }
                    else
                    {
                        FillRequest r = new FillRequest(username, generations);
                        fillResult = new FillService().fill(r);
                        writeFillResult(fillResult, exchange);

                        success = true;
                    }
                }
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

    /** Writes FillResult object to client
     *
     * @param fillResult
     * @param exchange
     * @throws IOException
     *
     */
    private void writeFillResult(FillResult fillResult, HttpExchange exchange) throws IOException
    {
        Gson gson = new Gson();
        String respData = gson.toJson(fillResult);
        c.sendResponse(exchange, respData);
    }
}

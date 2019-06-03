package HANDLERS;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import COMMUNICATION.RegisterRequest;
import COMMUNICATION.RegisterResult;
import SERVICES.RegisterService;

/** Handles the Register API
 *
 */
public class RegisterHandler implements HttpHandler
{
    /** CommonHandler instance that calls all shared code
     *
     */
    public CommonHandler c = new CommonHandler();


    /** Handle method for Register API
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
                //Convert JSON to RegisterResult object
                Gson gson = new Gson();
                Reader reader = new InputStreamReader(exchange.getRequestBody());
                RegisterRequest r = gson.fromJson(reader, RegisterRequest.class);

                RegisterResult registerResult = new RegisterService().register(r);

                String respData = gson.toJson(registerResult);
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




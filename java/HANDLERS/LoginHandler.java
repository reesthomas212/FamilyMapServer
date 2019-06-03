package HANDLERS;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import COMMUNICATION.LoginRequest;
import COMMUNICATION.LoginResult;
import SERVICES.LoginService;

/** Handles the Login API
 *
 */
public class LoginHandler implements HttpHandler
{
    /** CommonHandler instance that calls all shared code
     *
     */
    public CommonHandler c = new CommonHandler();

    /** Handle method for Login API
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
                LoginRequest r = gson.fromJson(reader, LoginRequest.class);

                LoginResult loginResult = new LoginService().login(r);

                String respData = gson.toJson(loginResult);
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

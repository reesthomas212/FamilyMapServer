package HANDLERS;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import COMMUNICATION.PersonRequest;
import COMMUNICATION.PersonResult;
import COMMUNICATION.PersonsRequest;
import COMMUNICATION.PersonsResult;
import SERVICES.AuthTokenCheck;
import SERVICES.PersonService;
import SERVICES.PersonsService;

/** Handles the Person API
 *
 */
public class PersonHandler implements HttpHandler
{
    /** CommonHandler instance that calls all shared code
     *
     */
    public CommonHandler c = new CommonHandler();

    /** Handle method for Person API
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
            if (exchange.getRequestMethod().toLowerCase().equals("get"))
            {
                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization"))
                {
                    String authToken = reqHeaders.getFirst("Authorization");
                    boolean check = new AuthTokenCheck().checkAuthToken(authToken);
                    if (check)
                    {
                        String path = exchange.getRequestURI().getPath();
                        if (c.checkForID(path))
                        {
                            PersonsRequest r = new PersonsRequest(authToken);
                            PersonsResult personsResult = new PersonsService().getPersons(r);

                            Gson gson = new Gson();
                            String respData = gson.toJson(personsResult);
                            c.sendResponse(exchange,respData);
                        }
                        else
                        {
                            String ID = path.substring(8, path.length());
                            PersonRequest r = new PersonRequest(ID);
                            PersonResult personResult = new PersonService().getPerson(r, authToken);

                            Gson gson = new Gson();
                            String respData = gson.toJson(personResult);
                            c.sendResponse(exchange, respData);
                        }

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
}

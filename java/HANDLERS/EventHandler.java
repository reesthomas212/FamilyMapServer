package HANDLERS;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import COMMUNICATION.EventRequest;
import COMMUNICATION.EventResult;
import COMMUNICATION.EventsRequest;
import COMMUNICATION.EventsResult;
import SERVICES.AuthTokenCheck;
import SERVICES.EventService;
import SERVICES.EventsService;

/** Handles the Event API
 *
 */
public class EventHandler implements HttpHandler
{
    /** CommonHandler instance that calls all shared code
     *
     */
    public CommonHandler c = new CommonHandler();

    /** Handle method for Event API
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
                            EventsRequest r = new EventsRequest(authToken);
                            EventsResult eventsResult = new EventsService().getEvents(r);

                            Gson gson = new Gson();
                            String respData = gson.toJson(eventsResult);
                            c.sendResponse(exchange, respData);
                        }
                        else
                        {
                            String ID = path.substring(7, path.length());
                            EventRequest r = new EventRequest(ID);
                            EventResult eventResult = new EventService().getEvent(r, authToken);

                            Gson gson = new Gson();
                            String respData = gson.toJson(eventResult);
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

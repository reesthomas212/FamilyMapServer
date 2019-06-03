package COMMUNICATION;

/** Request object for /event/ handle
 *
 * @author Rees Thomas
 *
 */
public class EventsRequest
{
    /** Create a new EventsRequest object
     *
     */
    public EventsRequest(String authToken)
    {
        this.authToken = authToken;
    }

    /** Current authToken of current user
     *
     */
    public String authToken;


    public String getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }
}

package COMMUNICATION;

/** Request object for /person/
 *
 * @author Rees Thomas
 *
 */
public class PersonsRequest
{
    /** Create a new PersonsRequest object
     *
     * @param authToken
     *
     */
    public PersonsRequest(String authToken)
    {
        this.authToken = authToken;
    }
    /** Current AuthToken of user
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

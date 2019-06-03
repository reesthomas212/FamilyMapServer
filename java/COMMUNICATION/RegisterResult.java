package COMMUNICATION;

/** Response object for /user/register
 *
 * @author Rees Thomas
 *
 */
public class RegisterResult
{
    /** Create a new RegisterResult object
     *
     * @param authToken
     * @param username
     * @param personID
     *
     */
    public RegisterResult(String authToken, String username, String personID)
    {
        this.authToken = authToken;
        this.username = username;
        this.personID = personID;
    }

    /** Current authToken of new user
     *
     */
    public String authToken;

    /** Specified username
     *
     */
    public String username;

    /** Generated ID of user's assigned Person's object
     *
     */
    public String personID;

    /** Failure message
     *
     */
    public String message;

    public String getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPersonID()
    {
        return personID;
    }

    public void setPersonID(String personID)
    {
        this.personID = personID;
    }

    public String getErrorMessage()
    {
        return message;
    }

    public void setErrorMessage(String message)
    {
        this.message = message;
    }
}

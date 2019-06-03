package MODELS;

/** Contains all of an AuthToken's information
 *
 */
public class AuthToken
{
    /** Create a new AuthToken
     *
     * @param authToken
     * @param userName
     *
     */
    public AuthToken(String authToken, String userName) {
        this.authToken = authToken;
        this.userName = userName;
    }

    /** User's unique authorization token
     *
     */
    public String authToken;

    /** Username to whom the authToken belongs
     *
     */
    public String userName;

    public String getAuthToken() {
        return authToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

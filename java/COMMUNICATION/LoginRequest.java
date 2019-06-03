package COMMUNICATION;

/** Request object for /user/login
 *
 * @author Rees Thomas
 *
 */
public class LoginRequest
{
    /** Create a new LoginRequest object
     *
     * @param username
     * @param password
     *
     */
    public LoginRequest(String username, String password)
    {
        this.userName = username;
        this.password = password;
    }

    /** Username of user logging in
     *
     */
    public String userName;

    /** Password of user logging in
     *
     */
    public String password;

    public String getUsername()
    {
        return userName;
    }

    public void setUsername(String username)
    {
        this.userName = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}

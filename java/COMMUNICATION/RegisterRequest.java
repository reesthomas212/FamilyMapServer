package COMMUNICATION;

/** Request object for /user/register
 *
 * @author Rees Thomas
 *
 */
public class RegisterRequest
{
    /** Create a new RegisterRequest object
     *
     * @param username
     * @param password
     * @param emailAddress
     * @param firstName
     * @param lastName
     * @param gender
     *
     */
    public RegisterRequest(String username, String password,
                           String emailAddress, String firstName,
                           String lastName, String gender) {
        this.userName = username;
        this.password = password;
        this.email = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    /** Specified username
     *
     */
    public String userName;

    /** Specified password
     *
     */
    public String password;

    /** Specified email address
     *
     */
    public String email;

    /** Specified first name
     *
     */
    public String firstName;

    /** Specified last name
     *
     */
    public String lastName;

    /** Specified gender
     *
     */
    public String gender;

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

    public String getEmailAddress()
    {
        return email;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.email = emailAddress;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
}

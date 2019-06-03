package MODELS;

/** Contain all of user's information
 *
 * @author Rees Thomas
 *
 */
public class User
{
    /** Create new user
     *
     * @param username
     * @param password
     * @param emailAddress
     * @param firstName
     * @param lastName
     * @param gender
     * @param personID
     *
     */
    public User(String username, String password,
                String emailAddress, String firstName,
                String lastName, String gender,
                String personID) {
        this.userName = username;
        this.password = password;
        this.email = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    /** Username of the current user
     *
     */
    public String userName;

    /** User's password required for login
     *
     */
    public String password;

    /** User's email address
     *
     */
    public String email;

    /** User's first name
     *
     */
    public String firstName;

    /** User's last name
     *
     */
    public String lastName;

    /** User's gender
     *
     */
    public String gender;

    /** User's generated unique ID
     *
     */
    public String personID;

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getPersonID() {
        return personID;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.email = emailAddress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}

package SERVICES;

import java.util.UUID;

import COMMUNICATION.LoginRequest;
import COMMUNICATION.LoginResult;
import DAO.AuthTokenDAO;
import DAO.UserDAO;
import MODELS.AuthToken;
import MODELS.User;

/** Service class for /user/login handle
 *
 *@author Rees Thomas
 *
 */
public class LoginService
{
    /** Instance of Database communication object
     *
     */
    Database db = new Database();

    /** Global LoginResult object
     *
     */
    LoginResult s = null;

    /** PersonID of user logging in
     *
     */
    String personID;

    /** Returns a LoginResult object complete with successful login data or an error message
     *
     * @param r
     * @return LoginResult
     *
     */
    public LoginResult login(LoginRequest r)
    {
        try {
            db.openConnection();

            AuthToken a = checkLogin(r.getUsername(), r.getPassword());
            AuthTokenDAO aDAO = new AuthTokenDAO();
            aDAO.create(db.connection, a);

            s = new LoginResult(a.getAuthToken(), a.getUserName(), personID);

            db.closeConnection(true);
        }
        catch (DatabaseException e)
        {
            s = new LoginResult(null, null, null);
            s.setErrorMessage(e.MESSAGE);
            db.closeConnection(false);
        }
        finally
        {
            return s;
        }
    }

    /** Returns a valid AuthToken object if there's a successful login
     *
     * @param username
     * @param password
     * @return AuthToken
     * @throws DatabaseException
     *
     */
    private AuthToken checkLogin(String username, String password) throws DatabaseException
    {
        UserDAO uDAO = new UserDAO();
        User currUser = uDAO.read(db.connection, username);

        if (currUser == null)
        {
            throw new DatabaseException("User does not exist");
        }
        if (!currUser.getPassword().equals(password))
        {
            throw new DatabaseException("Password incorrect / does not exist");
        }

        String newAuthToken = UUID.randomUUID().toString();
        personID = currUser.getPersonID();
        AuthToken authToken = new AuthToken(newAuthToken, username);

        return authToken;
    }
}

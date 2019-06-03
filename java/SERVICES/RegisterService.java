package SERVICES;

import java.util.UUID;

import COMMUNICATION.RegisterRequest;
import COMMUNICATION.RegisterResult;
import DAO.AuthTokenDAO;
import DAO.PersonDAO;
import DAO.UserDAO;
import MODELS.AuthToken;
import MODELS.Person;
import MODELS.User;

/** Service class for /user/register handle
 *
 *@author Rees Thomas
 *
 */
public class RegisterService
{
    /** Instance of Database communication object
     *
     */
    Database db = new Database();

    /** Global RegisterResult object
     *
     */
    RegisterResult s = null;

    /** FillService object in order to call common code
     *
     */
    FillService f = new FillService();

    /** Returns a RegisterResult object complete with successful registration info or an error message
     *
     * @param r
     * @return RegisterResult
     *
     */
    public RegisterResult register(RegisterRequest r)
    {
        try {
            db.openConnection();

            Person p = createUserAccount(r);

            if (p == null)
            {
                db.closeConnection(false);
                return s;
            }

            createGenerationsForUser(p);

            AuthToken a = logUserIn(r.getUsername());
            s = new RegisterResult(a.getAuthToken(), r.getUsername(), p.getPersonID());

            db.closeConnection(true);
        }
        catch (DatabaseException e)
        {
            s = new RegisterResult(null, null, null);
            s.setErrorMessage(e.MESSAGE);
            db.closeConnection(false);
        }
        finally
        {
            return s;
        }
    }

    /** Creates a new user account for person registering
     *
     * @param r
     * @return Person
     * @throws DatabaseException
     *
     */
    private Person createUserAccount(RegisterRequest r) throws DatabaseException
    {
        UserDAO uDAO = new UserDAO();
        if (checkUser(r.getUsername()))
        {
            throw new DatabaseException("Username already being used");
        }
        String newPersonID = UUID.randomUUID().toString();

        //Create Person and User objects for user
        Person user_person = new Person(newPersonID,
                r.getUsername(),
                r.getFirstName(),
                r.getLastName(),
                r.getGender(),
                null,
                null,
                null);

        PersonDAO pDAO = new PersonDAO();
        pDAO.create(db.connection, user_person);

        User user = new User(r.getUsername(),
                r.getPassword(),
                r.getEmailAddress(),
                r.getFirstName(),
                r.getLastName(),
                r.getGender(),
                newPersonID);

        uDAO.create(db.connection, user);

        return user_person;
    }

    /** Calls FillService's method to fill new user's family tree with persons and events
     *
     * @param p
     * @throws DatabaseException
     *
     */
    private void createGenerationsForUser(Person p) throws DatabaseException//generates 4 generations for new user's family tree
    {
        f.createGenerations(4, p, db.connection);
    }

    /** Logs the user into the application, and returns a generated AuthToken object
     *
     * @param username
     * @return AuthToken
     * @throws DatabaseException
     *
     */
    private AuthToken logUserIn(String username) throws DatabaseException
    {
        String newAuthToken = UUID.randomUUID().toString();
        AuthToken authToken = new AuthToken(newAuthToken, username);
        AuthTokenDAO aDAO = new AuthTokenDAO();
        aDAO.create(db.connection, authToken);
        return authToken;
    }

    public boolean checkUser(String username) throws DatabaseException
    {
        boolean check = false;
        UserDAO dao_A = new UserDAO();
        User temp = dao_A.read(db.connection, username);
        if (temp != null)
        {
            check = true;
        }
        return check;
    }
}

package SERVICES;

import COMMUNICATION.PersonsRequest;
import COMMUNICATION.PersonsResult;
import DAO.AuthTokenDAO;
import DAO.PersonDAO;
import MODELS.AuthToken;
import MODELS.Person;

/** Service class for /person/ handle
 *
 *@author Rees Thomas
 *
 */
public class PersonsService
{
    /** Instance of Database communication object
     *
     */
    Database db = new Database();

    /** Global PersonsResult object
     *
     */
    PersonsResult s = new PersonsResult();

    /** Returns a PersonsResult object complete with the user's persons data or an error message
     *
     * @param r
     * @return PersonsResult
     *
     */
    public PersonsResult getPersons(PersonsRequest r)
    {
        try {
            db.openConnection();

            AuthTokenDAO aDAO = new AuthTokenDAO();
            AuthToken a = aDAO.read(db.connection, r.getAuthToken());

            if (a == null)
            {
                s.setPersonArray(null);
                s.setErrorMessage("Not logged in");
                return s;
            }

            PersonDAO pDAO = new PersonDAO();
            Person[] data = pDAO.readAll(db.connection, a.getUserName());

            if (data.length == 0)
            {
                s.setPersonArray(null);
                s.setErrorMessage("No event objects returned");
                return s;
            }

            s.setErrorMessage(null);
            s.data = data;

            db.closeConnection(true);
        }
        catch (DatabaseException e)
        {
            db.closeConnection(false);
            s.setErrorMessage(e.MESSAGE);
            s.setPersonArray(null);
            e.printStackTrace();
        }
        finally
        {
            return s;
        }
    }
}

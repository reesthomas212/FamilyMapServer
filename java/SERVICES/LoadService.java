package SERVICES;

import COMMUNICATION.LoadRequest;
import COMMUNICATION.LoadResult;
import DAO.EventDAO;
import DAO.PersonDAO;
import DAO.UserDAO;
import MODELS.Event;
import MODELS.Person;
import MODELS.User;

/** Service class for /load/ handle
 *
 *@author Rees Thomas
 *
 */
public class LoadService
{
    /** Instance of Database communicator object
     *
     */
    Database db = new Database();

    /** Global LoadResult variable
     *
     */
    LoadResult s = null;

    /** Loads data and returns a LoadResult object
     *
     * @param r
     * @return LoadResult
     *
     */
    public LoadResult load(LoadRequest r)
    {
        try {
            db.openConnection();

            new ClearService().clear();

            loadUsers(r.getUsers());
            loadPersons(r.getPersons());
            loadEvents(r.getEvents());

            s = new LoadResult("Successfully added " + r.getUsers().length +
                               " users, " + r.getPersons().length +
                               " persons, and " + r.getEvents().length +
                               " events to the database.");

            db.closeConnection(true);
        }
        catch (DatabaseException e)
        {
            e.printStackTrace();
            s = new LoadResult(e.MESSAGE);
            db.closeConnection(false);
        }
        finally
        {
            return s;
        }
    }

    /** Loads all posted User data into database
     *
     * @param users
     * @throws DatabaseException
     *
     */
    private void loadUsers(User[] users) throws DatabaseException
    {
        UserDAO uDAO = new UserDAO();
        for (int i = 0; i < users.length; i++)
        {
            uDAO.create(db.connection, users[i]);
        }
    }

    /** Loads all posted Persons data into database
     *
     * @param persons
     * @throws DatabaseException
     *
     */
    private void loadPersons(Person[] persons)throws DatabaseException
    {
        PersonDAO pDAO = new PersonDAO();
        for (int i = 0; i < persons.length; i++)
        {
            pDAO.create(db.connection, persons[i]);
        }
    }

    /** Loads all posted Event data into database
     *
     * @param events
     * @throws DatabaseException
     *
     */
    private void loadEvents(Event[] events) throws DatabaseException
    {
        EventDAO eDAO = new EventDAO();
        for (int i = 0; i < events.length; i++)
        {
            eDAO.create(db.connection, events[i]);
        }
    }
}

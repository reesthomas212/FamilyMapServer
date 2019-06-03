package SERVICES;

import COMMUNICATION.EventsRequest;
import COMMUNICATION.EventsResult;
import DAO.AuthTokenDAO;
import DAO.EventDAO;
import MODELS.AuthToken;
import MODELS.Event;

/** Service class for /event/ handle
 *
 * @author Rees Thomas
 *
 */
public class EventsService
{
    /** Instance of Database communicator object
     *
     */
    public Database db = new Database();

    /** Global EventsResult object
     *
     */
    public EventsResult s = new EventsResult();

    /** Returns an EventsResult object complete with an event's info or an error message
     *
     * @param r
     * @return EventsResult
     *
     */
    public EventsResult getEvents(EventsRequest r)
    {
        try {
            db.openConnection();

            AuthTokenDAO aDAO = new AuthTokenDAO();
            AuthToken a = aDAO.read(db.connection, r.getAuthToken());

            if (a == null)
            {
                s.setEventArray(null);
                s.setErrorMessage("Not logged in");
                return s;
            }

            EventDAO eDAO = new EventDAO();
            Event[] data = eDAO.readAll(db.connection, a.getUserName());

            if (data.length == 0)
            {
                s.setEventArray(null);
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
            s.setEventArray(null);
            e.printStackTrace();
        }
        finally
        {
            return s;
        }
    }
}

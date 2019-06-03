package SERVICES;

import COMMUNICATION.EventRequest;
import COMMUNICATION.EventResult;
import DAO.AuthTokenDAO;
import DAO.EventDAO;
import MODELS.AuthToken;
import MODELS.Event;

/** Service class for /event/[eventID] handle
 *
 * @author Rees Thomas
 *
 */
public class EventService
{
    /** Instance of Database communicator object
     *
     */
    public Database db = new Database();

    /** EventResult instance to be returned
     *
     */
    public EventResult s = null;

    /** Returns an EventResult object complete with an event's info or an error message
     *
     * @param r
     * @param authToken
     * @return EventResult
     *
     */
    public EventResult getEvent(EventRequest r, String authToken) {
        try {
            db.openConnection();

            Event event = readEvent(r.getEventID());

            checkEvent_AuthToken(event, authToken);

            setEventResult(event);

            db.closeConnection(true);
        }
        catch (DatabaseException e)
        {
            s = new EventResult(null, null, null, 0, 0, null, null, null, null);
            s.setMessage(e.MESSAGE);
            db.closeConnection(false);
        }
        finally
        {
            return s;
        }
    }

    /** Sets EventResult object with the returned event's info
     *
     * @param event
     *
     */
    private void setEventResult(Event event)
    {
        s = new EventResult(event.getDescendant(), event.getEventID(),
                event.getPerson(), event.getLatitude(),
                event.getLongitude(), event.getCountry(),
                event.getCity(), event.getEventType(),
                event.getYear());
    }

    /** Checks if the authToken of current user belongs to the event's descendant
     *
     * @param event
     * @param authToken
     * @throws DatabaseException
     *
     */
    private void checkEvent_AuthToken(Event event, String authToken) throws DatabaseException
    {
        AuthTokenDAO aDAO = new AuthTokenDAO();
        AuthToken a = aDAO.read(db.connection, authToken);
        if (!event.getDescendant().equals(a.getUserName()))
        {
            throw new DatabaseException("Invalid Access");
        }
    }

    /** Reads in event and checks if it's in the database
     *
     * @param eventID
     * @return Event
     * @throws DatabaseException
     *
     */
    private Event readEvent(String eventID) throws DatabaseException
    {
        EventDAO dao = new EventDAO();
        Event event = dao.read(db.connection, eventID);
        if (event == null)
        {
            throw new DatabaseException("Event not found");
        }
        return event;
    }
}

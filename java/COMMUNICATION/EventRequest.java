package COMMUNICATION;

/** Request object for /event/[eventID]
 *
 * @author Rees Thomas
 *
 */
public class EventRequest
{
    /** Create an EventRequest object
     *
     * @param eventID
     *
     */
    public EventRequest(String eventID)
    {
        this.eventID = eventID;
    }

    /** EventID
     *
     */
    public String eventID;

    public String getEventID()
    {
        return eventID;
    }

    public void setEventID(String eventID)
    {
        this.eventID = eventID;
    }
}

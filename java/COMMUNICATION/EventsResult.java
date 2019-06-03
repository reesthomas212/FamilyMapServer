package COMMUNICATION;

import MODELS.Event;

/** Response object for /event/
 *
 * @author Rees Thomas
 *
 */
public class EventsResult
{
    /** Create a new EventsResult object
     *
     */
    public EventsResult() {}

    /** Array of all events for all family members of current user
     *
     */
    public Event[] data;

    /** Failure message
     *
     */
    public String message;

    public Event[] getEventArray()
    {
        return data;
    }

    public void setEventArray(Event[] data)
    {
        this.data = data;
    }

    public String getErrorMessage()
    {
        return message;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.message = errorMessage;
    }
}

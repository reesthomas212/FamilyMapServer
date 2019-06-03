package COMMUNICATION;

/** Response object for /event/[eventID]
 *
 * @author Rees Thomas
 *
 */
public class EventResult
{
    /** Create a new EventResult object
     *
     * @param descendant
     * @param eventID
     * @param personID
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param eventType
     * @param year
     *
     */
    public EventResult(String descendant, String eventID,
                       String personID, double latitude,
                       double longitude, String country,
                       String city, String eventType,
                       String year)
    {
        this.descendant = descendant;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /** Name of user this event belongs to
     *
     */
    public String descendant;

    /** Event's unique ID
     *
     */
    public String eventID;

    /** ID of the person this event belongs to
     *
     */
    public String personID;

    /** Latitude of the event's location
     *
     */
    public double latitude;

    /** Longitude of the event's location
     *
     */
    public double longitude;

    /** Name of country where event occurred
     *
     */
    public String country;

    /** Name of city where event occurred
     *
     */
    public String city;

    /** Type of event
     *
     */
    public String eventType;

    /** Year the event occurred
     *
     */
    public String year;

    /** Failure message
     *
     */
    public String message;

    public String getDescendant()
    {
        return descendant;
    }

    public void setDescendant(String descendant)
    {
        this.descendant = descendant;
    }

    public String getEventID()
    {
        return eventID;
    }

    public void setEventID(String eventID)
    {
        this.eventID = eventID;
    }

    public String getPersonID()
    {
        return personID;
    }

    public void setPersonID(String personID)
    {
        this.personID = personID;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}

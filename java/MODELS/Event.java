package MODELS;

import java.util.List;

/** Contains all of an event's info
 *
 * @author Rees Thomas
 *
 */
public class Event implements Comparable<Event>
{
    /** Create a new Event
     *
     * @param eventID
     * @param descendant
     * @param person
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param eventType
     * @param year
     *
     */
    public Event(String eventID, String descendant,
                 String person, double latitude,
                 double longitude, String country,
                 String city, String eventType, String year) {
        this.eventID = eventID;
        this.descendant = descendant;
        this.personID = person;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /** Unique ID for an event
     *
     */
    public String eventID;

    /** User's username to which this person belongs
     *
     */
    public String descendant;

    /** ID of person to which this event belongs
     *
     */
    public String personID;

    /** Latitude of event's location
     *
     */
    public double latitude;

    /** Longitude of event's location
     *
     */
    public double longitude;

    /** Country in which event occurred
     *
     */
    public String country;

    /** City in which event occurred
     *
     */
    public String city;

    /** Type of event (birth, baptism, marriage, death, etc)
     *
     */
    public String eventType;

    /** Year in which event occurred
     *
     */
    public String year;

    public String getEventID() {
        return eventID;
    }

    public String getDescendant() {
        return descendant;
    }

    public String getPerson() {
        return personID;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEventType() {
        return eventType;
    }

    public String getYear() {
        return year;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public void setPerson(String person) {
        this.personID = person;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int compareTo(Event event)
    {
        if (this.getEventType().toLowerCase().equals("birth"))
        {
            return -1;
        }
        if (event.getEventType().toLowerCase().equals("birth"))
        {
            return 1;
        }

        if (this.getEventType().toLowerCase().equals("death"))
        {
            return 1;
        }
        if (event.getEventType().toLowerCase().equals("death"))
        {
            return -1;
        }

        int year1 = Integer.parseInt(this.getYear());
        int year2 = Integer.parseInt(event.getYear());
        if (year1 < year2)
        {
            return -1;
        }
        else if (year2 < year1)
        {
            return 1;
        }
        else
        {
            return this.getEventType().toLowerCase().compareTo(event.getEventType().toLowerCase());
        }
    }
}

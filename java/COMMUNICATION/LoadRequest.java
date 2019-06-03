package COMMUNICATION;

import MODELS.Event;
import MODELS.Person;
import MODELS.User;

/** Request object for /load/
 *
 * @author Rees Thomas
 *
 */
public class LoadRequest
{
    /** Create a new LoadRequest object
     *
     */
    public LoadRequest() {}

    /** Array of User objects
     *
     */
    public User[] users;

    /** Array of Person objects
     *
     */
    public Person[] persons;

    /** Array of Event objects
     *
     */
    public Event[] events;

    public User[] getUsers()
    {
        return users;
    }

    public void setUsers(User[] users)
    {
        this.users = users;
    }

    public Person[] getPersons()
    {
        return persons;
    }

    public void setPersons(Person[] persons)
    {
        this.persons = persons;
    }

    public Event[] getEvents()
    {
        return events;
    }

    public void setEvents(Event[] events)
    {
        this.events = events;
    }
}

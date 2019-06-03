package COMMUNICATION;

/** Request object for /person/[personID]
 *
 * @author Rees Thomas
 *
 */
public class PersonRequest
{
    /** Create a new PersonRequest object
     *
     * @param personID
     *
     */
    public PersonRequest(String personID) {
        this.personID = personID;
    }

    /** Person ID of specified person within database
     *
     */
    public String personID;

    public String getPersonID()
    {
        return personID;
    }

    public void setPersonID(String personID)
    {
        this.personID = personID;
    }
}

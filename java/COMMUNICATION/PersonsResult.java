package COMMUNICATION;

import MODELS.Person;

/** Response object for /person/
 *
 * @author Rees Thomas
 *
 */
public class PersonsResult
{
    /** Create a new PersonsResult object
     *
     */
    public PersonsResult() {}

    /**Array of Person objects
     *
     */
    public Person[] data;

    /** Failure message
     *
     */
    public String message;

    public Person[] getPersonArray()
    {
        return data;
    }

    public void setPersonArray(Person[] personArray)
    {
        this.data = personArray;
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

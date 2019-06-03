package COMMUNICATION;

/** Response object for /person/[personID]
 *
 * @author Rees Thomas
 *
 */
public class PersonResult
{
    /** Create a new PersonResult object
     *
     * @param descendant
     * @param personID
     * @param firstName
     * @param lastName
     * @param gender
     * @param father
     * @param mother
     * @param spouse
     *
     */
    public PersonResult(String descendant, String personID,
                        String firstName, String lastName,
                        String gender, String father,
                        String mother, String spouse)
    {
        this.descendant = descendant;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    /** Name of user account this person belongs to
     *
     */
    public String descendant;

    /** Person's unique ID
     *
     */
    public String personID;

    /** Person's first name
     *
     */
    public String firstName;

    /** Person's last name
     *
     */
    public String lastName;

    /** Person's gender
     *
     */
    public String gender;

    /** ID of person's father
     *
     */
    public String father;

    /** ID of person's mother
     *
     */
    public String mother;

    /** ID of person's spouse
     *
     */
    public String spouse;

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

    public String getPersonID()
    {
        return personID;
    }

    public void setPersonID(String personID)
    {
        this.personID = personID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getFather()
    {
        return father;
    }

    public void setFather(String father)
    {
        this.father = father;
    }

    public String getMother()
    {
        return mother;
    }

    public void setMother(String mother)
    {
        this.mother = mother;
    }

    public String getSpouse()
    {
        return spouse;
    }

    public void setSpouse(String spouse)
    {
        this.spouse = spouse;
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

package SERVICES;

import COMMUNICATION.PersonRequest;
import COMMUNICATION.PersonResult;
import DAO.AuthTokenDAO;
import DAO.PersonDAO;
import MODELS.AuthToken;
import MODELS.Person;

/** Service class for /person/[personID] handle
 *
 *@author Rees Thomas
 *
 */
public class PersonService
{
    /** Instance of Database communication object
     *
     */
    Database db = new Database();

    /** Global PersonResult object
     *
     */
    PersonResult s = new PersonResult(null,null,null,null,null,null,null,null);

    /** Returns a PersonResult object complete with a Person's data or an error message
     *
     * @param r
     * @param authToken
     * @return PersonResult
     *
     */
    public PersonResult getPerson(PersonRequest r, String authToken)
    {
        try {
            db.openConnection();

            Person person = checkPerson(r);

            checkCurrAuthToken(authToken, person);

            setData(person);

            db.closeConnection(true);
        }
        catch (DatabaseException e)
        {
            db.closeConnection(false);
            s.setErrorMessage(e.MESSAGE);
        }
        finally
        {
            return s;
        }
    }

    /** Checks person object read in from database
     *
     * @param r
     * @return Person
     * @throws DatabaseException
     *
     */
    private Person checkPerson(PersonRequest r) throws DatabaseException
    {
        PersonDAO dao = new PersonDAO();
        Person person = dao.read(db.connection, r.getPersonID());

        if (person == null)
        {
            throw new DatabaseException("Person not found");
        }

        return person;
    }

    /** Checks if AuthToken being used belongs to person's descendant
     *
     * @param authToken
     * @param person
     * @throws DatabaseException
     *
     */
    private void checkCurrAuthToken(String authToken, Person person) throws DatabaseException
    {
        AuthTokenDAO aDAO = new AuthTokenDAO();
        AuthToken a = aDAO.read(db.connection, authToken);

        if (!person.getDescendant().equals(a.getUserName()))
        {
            throw new DatabaseException("Invalid Access");
        }
    }

    /** Sets PersonResult's members to person's members
     *
     * @param person
     *
     */
    private void setData(Person person)
    {
        s.setDescendant(person.getDescendant());
        s.setPersonID(person.getPersonID());
        s.setFirstName(person.getFirstName());
        s.setLastName(person.getLastName());
        s.setGender(person.getGender());
        s.setFather(person.getFather());
        s.setMother(person.getMother());
        s.setSpouse(person.getSpouse());
    }
}

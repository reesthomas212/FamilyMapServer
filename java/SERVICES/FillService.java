package SERVICES;

import java.sql.Connection;
import java.util.TreeSet;
import java.util.UUID;

import COMMUNICATION.FillRequest;
import COMMUNICATION.FillResult;
import DAO.EventDAO;
import DAO.PersonDAO;
import DAO.UserDAO;
import DATA.Fnames;
import DATA.LocationData;
import DATA.Mnames;
import DATA.Snames;
import MODELS.Event;
import MODELS.Person;
import MODELS.User;

/** Service class for /fill/[username] handle
 *
 * @author Rees Thomas
 *
 */
public class FillService
{
    /** Instance of Database communication object
     *
     */
    Database db = new Database();

    /** Global FillResult object
     *
     */
    FillResult s = null;

    /** Count of number of persons added to database due to fill()
     *
     */
    public int numOfPersonsAdded = 0;

    /** Count of number of events added to database due to fill()
     *
     */
    public int numOfEventsAdded = 0;

    /** Global String for user's username
     *
     */
    public String username;

    /** Current year
     *
     */
    private final int year = 2018;

    /** Modifiable variable to determine random generated data's year and age
     *
     */
    private int modify_year = 25;

    /** Person's father's age
     *
     */
    private int father_age = 0;

    /** Person's mother's age
     *
     */
    private int mother_age = 0;

    /** Generates random data for specified username with the specified number of generations
     *
     * @param r
     * @return FillResult
     *
     */
    public FillResult fill(FillRequest r)
    {
        try {
            db.openConnection();

            if (!checkUser(r.getUsername()))
            {
                throw new DatabaseException("User does not exist");
            }
            deleteAncestors(r.getUsername());
            deleteAncestorEvents(r.getUsername());
            Person p = createPersonForUser(r.getUsername());

            createGenerations(r.getGenerations(), p, db.connection);

            s = new FillResult();
            s.setMessage("Successfully added " + numOfPersonsAdded +
                            " persons and " + numOfEventsAdded + " events to the database.");

            db.closeConnection(true);
        }
        catch (DatabaseException e)
        {
            s = new FillResult();
            s.setMessage(e.MESSAGE);
            db.closeConnection(false);
        }
        finally
        {
            return s;
        }
    }

    /** Deletes user's ancestors' data
     *
     * @param username
     * @throws DatabaseException
     *
     */
    private void deleteAncestors(String username) throws DatabaseException
    {
        String sql = "DELETE FROM personTable WHERE descendant = '" + username + "'";
        PersonDAO pDAO = new PersonDAO();
        pDAO.deleteORupdate(db.connection, sql);
    }

    /** Deletes user's ancestors' events' data
     *
     * @param username
     * @throws DatabaseException
     *
     */
    private void deleteAncestorEvents(String username) throws DatabaseException
    {
        String sql = "DELETE FROM eventTable WHERE descendant = '" + username + "'";
        EventDAO eDAO = new EventDAO();
        eDAO.deleteORupdate(db.connection, sql);
    }

    /** Creates a new person object for user
     *
     * @param username
     * @return
     * @throws DatabaseException
     *
     */
    private Person createPersonForUser(String username) throws DatabaseException
    {
        UserDAO uDAO = new UserDAO();
        User user = uDAO.read(db.connection, username);
        String newPersonID = UUID.randomUUID().toString();

        Person p = new Person(newPersonID, user.getUsername(),
                user.getFirstName(), user.getLastName(),
                user.getGender(), null, null, null);

        new PersonDAO().create(db.connection, p);
        numOfPersonsAdded++;

        return p;
    }

    /** Creates the specified number of generations for the user
     *
     * @param generations
     * @param p
     * @param connection
     * @throws DatabaseException
     *
     */
    public void createGenerations(int generations, Person p, Connection connection) throws DatabaseException
    {
        username = p.getDescendant();
        db.connection = connection;
        EventDAO eDAO = new EventDAO();

        createBirthEventForUser(p);

        TreeSet<String> temp = new TreeSet<>();
        TreeSet<String> personIDs = new TreeSet<>();
        temp.add(p.getPersonID());

        for (int i = 0; i < generations; i++)
        {
            personIDs.addAll(temp);
            temp.removeAll(personIDs);
            while (!personIDs.isEmpty())
            {
                createParents(personIDs.first(), temp);
                personIDs.remove(personIDs.first());
            }
            modify_year = modify_year + 20;
        }
    }

    /** Creates a Birth Event for the user's person object
     *
     * @param p
     * @throws DatabaseException
     *
     */
    private void createBirthEventForUser(Person p) throws DatabaseException
    {
        String eventID = UUID.randomUUID().toString();
        EventDAO eDAO = new EventDAO();
        LocationData.Location location = new LocationData().randomLocation();

        Event birthEvent = new Event(eventID, p.getDescendant(), p.getPersonID(),
                                     Double.parseDouble(location.latitude),
                                     Double.parseDouble(location.longitude),
                                     location.country, location.city, "Birth",
                                     Integer.toString(1993));

        eDAO.create(db.connection, birthEvent);
        numOfEventsAdded++;
    }

    /** Creates Parent person objects for their child
     *
     * @param childID
     * @param temp
     * @throws DatabaseException
     *
     */
    private void createParents(String childID, TreeSet<String> temp) throws DatabaseException
    {
        String motherID = UUID.randomUUID().toString();
        String fatherID = UUID.randomUUID().toString();
        temp.add(motherID);
        temp.add(fatherID);

        PersonDAO pDAO = new PersonDAO();
        String maleName = new Mnames().randomMaleName();
        String surname = new Snames().randomSurname();

        Person father = new Person( fatherID, username, maleName, surname,
                                    "m", null, null, motherID);

        pDAO.create(db.connection, father);
        numOfPersonsAdded++;

        String femaleName = new Fnames().randomFemaleName();

        Person mother = new Person(motherID, username, femaleName, surname,
                                   "f", null, null, fatherID);

        pDAO.create(db.connection, mother);
        numOfPersonsAdded++;

        updateChild(pDAO, fatherID, motherID, childID);

        createAncestorBirthEvents(mother, father);
        createAncestorMarriageEvents(mother, father);
        createAncestorDeathEvents(mother, father);
    }

    /** Updates child's fatherID and motherID
     *
     * @param pDAO
     * @param fatherID
     * @param motherID
     * @param childID
     * @throws DatabaseException
     *
     */
    private void updateChild(PersonDAO pDAO, String fatherID, String motherID, String childID) throws DatabaseException
    {
        String sql = "UPDATE personTable " +
                     "SET father = '" + fatherID + "', mother = '" + motherID + "'" +
                     "WHERE personID = '" + childID + "'";
        pDAO.deleteORupdate(db.connection, sql);
    }

    /** Creates Birth Events for child's parents
     *
     * @param mother
     * @param father
     * @throws DatabaseException
     *
     */
    private void createAncestorBirthEvents(Person mother, Person father) throws DatabaseException
    {
        EventDAO eDAO = new EventDAO();

        int birth_year_father = year - (modify_year + 27);
        father_age = modify_year + 27;

        String eventID = UUID.randomUUID().toString();
        LocationData.Location location = new LocationData().randomLocation();

        Event fatherbirth = new Event(eventID, username, father.getPersonID(),
                                      Double.parseDouble(location.latitude),
                                      Double.parseDouble(location.longitude),
                                      location.country, location.city,
                                      "Birth", Integer.toString(birth_year_father));

        eDAO.create(db.connection, fatherbirth);
        numOfEventsAdded++;

        int birth_year_mother = year - (modify_year + 23);
        mother_age = modify_year + 23;

        eventID = UUID.randomUUID().toString();
        location = new LocationData().randomLocation();

        Event motherbirth = new Event(eventID, username, mother.getPersonID(),
                                      Double.parseDouble(location.latitude),
                                      Double.parseDouble(location.longitude),
                                      location.country, location.city,
                                      "Birth", Integer.toString(birth_year_mother));

        eDAO.create(db.connection, motherbirth);
        numOfEventsAdded++;
    }

    /** Creates marriage events for child's parents
     *
     * @param mother
     * @param father
     * @throws DatabaseException
     *
     */
    private void createAncestorMarriageEvents(Person mother, Person father) throws DatabaseException
    {
        EventDAO eDAO = new EventDAO();

        String eventID = UUID.randomUUID().toString();
        LocationData.Location location = new LocationData().randomLocation();

        Event fathermarriage = new Event(eventID, username, father.getPersonID(),
                Double.parseDouble(location.latitude),
                Double.parseDouble(location.longitude),
                location.country, location.city,
                "Marriage", Integer.toString(year - (modify_year + 2)));

        eDAO.create(db.connection, fathermarriage);
        numOfEventsAdded++;

        eventID = UUID.randomUUID().toString();

        Event mothermarriage = new Event(eventID, username, mother.getPersonID(),
                Double.parseDouble(location.latitude),
                Double.parseDouble(location.longitude),
                location.country, location.city,
                "Marriage", Integer.toString(year - (modify_year + 2)));

        eDAO.create(db.connection, mothermarriage);
        numOfEventsAdded++;
    }

    /** Creates Death Events for child's parents if applicable
     *
     * @param mother
     * @param father
     * @throws DatabaseException
     *
     */
    private void createAncestorDeathEvents(Person mother, Person father) throws DatabaseException
    {
        EventDAO eDAO = new EventDAO();

        if (father_age > 90)
        {
            String eventID = UUID.randomUUID().toString();
            LocationData.Location location = new LocationData().randomLocation();

            Event fatherdeath = new Event(eventID, username, father.getPersonID(),
                                          Double.parseDouble(location.latitude),
                                          Double.parseDouble(location.longitude),
                                          location.country, location.city,
                                          "Death", Integer.toString(year - (father_age - 90)));

            eDAO.create(db.connection, fatherdeath);
            numOfEventsAdded++;
        }
        if (mother_age > 92)
        {
            String eventID = UUID.randomUUID().toString();
            LocationData.Location location = new LocationData().randomLocation();

            Event motherdeath = new Event(eventID, username, mother.getPersonID(),
                    Double.parseDouble(location.latitude),
                    Double.parseDouble(location.longitude),
                    location.country, location.city,
                    "Death", Integer.toString(year - (mother_age - 92)));

            eDAO.create(db.connection, motherdeath);
            numOfEventsAdded++;
        }
    }

    /** Checks if user is already registered
     *
     * @param username
     * @return boolean
     * @throws DatabaseException
     *
     */
    public boolean checkUser(String username) throws DatabaseException
    {
        boolean check = false;
        UserDAO dao_A = new UserDAO();
        User temp = dao_A.read(db.connection, username);
        if (temp != null)
        {
            check = true;
        }
        return check;
    }
}

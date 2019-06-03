package DAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MODELS.Person;
import SERVICES.Database;
import SERVICES.DatabaseException;

import static org.junit.Assert.assertEquals;

public class PersonDAOTest
{
    Database db;
    Person PERSON;
    PersonDAO pDAO;
    @Before
    public void setUp() throws DatabaseException
    {
        db = new Database();
        db.openConnection();
        db.createTables();

        pDAO = new PersonDAO();
        PERSON = new Person("1234567890", "fred", "Fred", "Andrews", "m", "12345", "67890", "susan123");
        pDAO.create(db.connection, PERSON);
    }

    @After
    public void tearDown() throws DatabaseException
    {
        db.closeConnection(false);
    }

    @Test
    public void testCreateAndRead() throws DatabaseException
    {
        Person person = pDAO.read(db.connection, PERSON.getPersonID());
        assertEquals("1234567890", person.getPersonID());
        assertEquals("fred", person.getDescendant());
        assertEquals("Fred", person.getFirstName());
        assertEquals("Andrews", person.getLastName());
        assertEquals("m", person.getGender());
        assertEquals("12345", person.getFather());
        assertEquals("67890", person.getMother());
        assertEquals("susan123", person.getSpouse());
    }

    @Test
    public void testDelete() throws DatabaseException
    {
        String sql = "DELETE from personTable where personID = '" + PERSON.getPersonID() + "'";

        pDAO.deleteORupdate(db.connection, sql);

        Person p = pDAO.read(db.connection, PERSON.getPersonID());

        assertEquals(null, p);
    }

    @Test
    public void testReadAll() throws DatabaseException
    {
        Person[] persons = pDAO.readAll(db.connection, PERSON.getDescendant());

        assertEquals(1, persons.length);
        assertEquals("fred", persons[0].getDescendant());
    }
}

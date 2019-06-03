package SERVICES;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import COMMUNICATION.ClearResult;
import DAO.AuthTokenDAO;
import DAO.EventDAO;
import DAO.PersonDAO;
import DAO.UserDAO;
import MODELS.AuthToken;
import MODELS.Event;
import MODELS.Person;
import MODELS.User;

import static org.junit.Assert.assertEquals;

public class ClearServiceTest
{
    Database db;
    UserDAO uDAO = new UserDAO();
    PersonDAO pDAO = new PersonDAO();
    EventDAO eDAO = new EventDAO();
    AuthTokenDAO aDAO = new AuthTokenDAO();
    ClearResult c;

    @Before
    public void setUp()  throws DatabaseException
    {
        db = new Database();
        db.openConnection();

        uDAO.create(db.connection, new User("","","","","","",""));
        pDAO.create(db.connection, new Person("","","","","","","",""));
        eDAO.create(db.connection, new Event("","","",0,0,"","","",""));
        aDAO.create(db.connection, new AuthToken("",""));
    }

    @After
    public void tearDown() throws DatabaseException
    {
        db.closeConnection(false);
    }

    @Test
    public void testLoadDictionaryFromDatabase() throws DatabaseException
    {
        c = new ClearService().clear();

        User u = uDAO.read(db.connection, "");
        Person p = pDAO.read(db.connection, "");
        Event e = eDAO.read(db.connection, "");
        AuthToken a = aDAO.read(db.connection, "");

        assertEquals("Clear Succeeded", c.getMessage());
        assertEquals(null, u.getUsername());
        assertEquals(null, p.getPersonID());
        assertEquals(null, e.getEventID());
        assertEquals(null, a.getAuthToken());
    }
}

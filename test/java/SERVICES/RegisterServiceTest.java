package SERVICES;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import COMMUNICATION.RegisterRequest;
import COMMUNICATION.RegisterResult;
import DAO.AuthTokenDAO;
import DAO.UserDAO;

import static org.junit.Assert.assertEquals;

public class RegisterServiceTest
{
    Database db;
    RegisterRequest r;
    RegisterResult s;
    @Before
    public void setUp()  throws DatabaseException
    {
        r = new RegisterRequest("fred", "fred123", "email@byu.edu", "Fred", "Andrews", "m");
    }

    @After
    public void tearDown() throws DatabaseException
    {
        db.closeConnection(false);
    }

    @Test
    public void testRegister() throws DatabaseException
    {
        s = new RegisterService().register(r);

        db = new Database();
        db.openConnection();

        String authToken = new AuthTokenDAO().read(db.connection, "fred").getAuthToken();
        String personID = new UserDAO().read(db.connection, "fred").getPersonID();

        assertEquals("fred", s.getUsername());
        assertEquals(authToken, s.getAuthToken());
        assertEquals(personID, s.getPersonID());
        assertEquals(null, s.getErrorMessage());

        db.closeConnection(false);
    }
}

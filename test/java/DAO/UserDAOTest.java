package DAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MODELS.User;
import SERVICES.Database;
import SERVICES.DatabaseException;

import static org.junit.Assert.assertEquals;

public class UserDAOTest
{
    private Database db;
    private UserDAO uDAO;
    private User USER;

    @Before
    public void setUp() throws DatabaseException
    {
        db = new Database();
        db.openConnection();

        uDAO = new UserDAO();
        USER = new User("fred", "fred123", "email@yahoo.com", "Fred", "Andrews", "m", "1234567890");
        uDAO.create(db.connection, USER);
    }

    @After
    public void tearDown() throws DatabaseException
    {
        db.closeConnection(false);
    }

    @Test
    public void testCreateAndRead() throws DatabaseException
    {
        User user = uDAO.read(db.connection, USER.getUsername());
        assertEquals(USER.getUsername(), user.getUsername());
        assertEquals(USER.getPassword(), user.getPassword());
        assertEquals(USER.getEmailAddress(), user.getEmailAddress());
        assertEquals(USER.getFirstName(), user.getFirstName());
        assertEquals(USER.getLastName(), user.getLastName());
        assertEquals(USER.getGender(), user.getGender());
        assertEquals(USER.getPersonID(), user.getPersonID());
    }
}

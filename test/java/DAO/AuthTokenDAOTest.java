package DAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MODELS.AuthToken;
import SERVICES.Database;
import SERVICES.DatabaseException;

import static org.junit.Assert.assertEquals;

public class AuthTokenDAOTest
{
    Database db;
    AuthTokenDAO aDAO;
    AuthToken AUTHTOKEN;

    @Before
    public void setUp()  throws DatabaseException
    {
        db = new Database();
        db.openConnection();
        db.createTables();

        aDAO = new AuthTokenDAO();
        AUTHTOKEN = new AuthToken("yes", "fred");
    }

    @After
    public void tearDown() throws DatabaseException
    {
        db.closeConnection(false);
    }

    @Test
    public void testCreateAndRead() throws DatabaseException
    {
        aDAO.create(db.connection, AUTHTOKEN);
        AuthToken a = aDAO.read(db.connection, AUTHTOKEN.getAuthToken());

        assertEquals("yes", a.getAuthToken());
        assertEquals("fred", a.getUserName());
    }
}

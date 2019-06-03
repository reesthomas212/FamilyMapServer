package SERVICES;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonServiceTest
{
    Database db;

    @Before
    public void setUp()  throws DatabaseException
    {
        db = new Database();
        db.openConnection();
        db.createTables();
    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void testLoadDictionaryFromDatabase()
    {

    }
}

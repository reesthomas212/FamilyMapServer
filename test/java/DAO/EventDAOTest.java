package DAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import MODELS.Event;
import SERVICES.Database;
import SERVICES.DatabaseException;

import static org.junit.Assert.assertEquals;

public class EventDAOTest
{
    Database db;
    EventDAO eDAO;
    Event EVENT;

    @Before
    public void setUp() throws DatabaseException
    {
        db = new Database();
        db.openConnection();
        db.createTables();

        eDAO = new EventDAO();
        EVENT = new Event("EVENT_ID", "fred", "1234567890", 20.2, 30.7, "India", "Mumbai", "Birth", "1977");
        eDAO.create(db.connection, EVENT);
    }

    @After
    public void tearDown() throws DatabaseException
    {
        db.closeConnection(false);
    }

    @Test
    public void testCreateAndRead() throws DatabaseException
    {
        Event event = eDAO.read(db.connection, EVENT.getEventID());
        assertEquals("EVENT_ID", event.getEventID());
        assertEquals("fred", event.getDescendant());
        assertEquals("1234567890", event.getPerson());
        assertEquals(20.2, event.getLatitude(), 0.1);
        assertEquals(30.7, event.getLongitude(), 0.1);
        assertEquals("India", event.getCountry());
        assertEquals("Mumbai", event.getCity());
        assertEquals("Birth", event.getEventType());
        assertEquals("1977", event.getYear());
    }

    @Test
    public void testDelete() throws DatabaseException
    {
        String sql = "DELETE from eventTable where eventID = '" + EVENT.getEventID() + "'";

        eDAO.deleteORupdate(db.connection, sql);

        Event e = eDAO.read(db.connection, EVENT.getEventID());

        assertEquals(null, e);
    }

    @Test
    public void testReadAll() throws DatabaseException
    {
        Event[] events = eDAO.readAll(db.connection, EVENT.getDescendant());

        assertEquals(1, events.length);
        assertEquals("fred", events[0].getDescendant());
    }
}

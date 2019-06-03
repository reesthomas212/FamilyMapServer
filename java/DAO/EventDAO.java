package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MODELS.Event;
import SERVICES.DatabaseException;

/** Events Database-Access Objects
 *
 * @author Rees Thomas
 *
 */
public class EventDAO
{
    /** Creates new event with specific eventID
     *
     * @param conn
     * @param event
     * @throws DatabaseException
     *
     */
    public void create(Connection conn, Event event) throws DatabaseException
    {
        try {
            String sql = "INSERT INTO eventTable (eventID, descendant, person, " +
                         "latitude, longitude, country, city, eventType, year) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getDescendant());
            stmt.setString(3, event.getPerson());
            stmt.setString(4, Double.toString(event.getLatitude()));
            stmt.setString(5, Double.toString(event.getLongitude()));
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setString(9, event.getYear());

            stmt.executeUpdate();
            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new DatabaseException("Event Insert failed");
        }
    }

    /** Returns an event object of specified eventID
     *
     * @param conn
     * @param eventID
     * @return Event
     * @throws DatabaseException
     *
     */
    public Event read(Connection conn, String eventID) throws DatabaseException
    {
        try {
            String sql = "select * from eventTable where eventID = '" + eventID + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Event temp = null;
            while (rs.next())
            {
                temp = new Event("","","",0,0,"","","","");
                temp.setEventID(rs.getString("eventID"));
                temp.setDescendant(rs.getString("descendant"));
                temp.setPerson(rs.getString("person"));
                temp.setLatitude(rs.getDouble("latitude"));
                temp.setLongitude(rs.getDouble("longitude"));
                temp.setCountry(rs.getString("country"));
                temp.setCity(rs.getString("city"));
                temp.setEventType(rs.getString("eventType"));
                temp.setYear(rs.getString("year"));
            }

            stmt.close();
            rs.close();

            return temp;
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Event Read failed");
        }
    }

    /** Deletes/updates event of specified eventID
     *
     * @param conn
     * @param sql
     * @throws DatabaseException
     *
     */
    public void deleteORupdate(Connection conn, String sql) throws DatabaseException
    {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Event Delete failed");
        }
    }

    /** Returns all events in database for specified user
     *
     * @param conn
     * @param descendant
     * @return Event[]
     * @throws DatabaseException
     *
     */
    public Event[] readAll(Connection conn, String descendant) throws DatabaseException
    {
        try {
            int size_of_table = getSize(conn, descendant, "eventTable");

            String sql = "select * from eventTable where descendant = '" + descendant + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int i = 0;
            Event[] events = new Event[size_of_table];
            while (rs.next())
            {
                Event temp = new Event("","","",0,0,"","","","");
                temp.setEventID(rs.getString("eventID"));
                temp.setDescendant(rs.getString("descendant"));
                temp.setPerson(rs.getString("person"));
                temp.setLatitude(rs.getDouble("latitude"));
                temp.setLongitude(rs.getDouble("longitude"));
                temp.setCountry(rs.getString("country"));
                temp.setCity(rs.getString("city"));
                temp.setEventType(rs.getString("eventType"));
                temp.setYear(rs.getString("year"));
                events[i] = temp;
                i++;
            }

            stmt.close();
            rs.close();

            return events;
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Event ReadAll failed");
        }
    }

    /** Returns number of rows in eventTable for the specified user
     *
     * @param conn
     * @param descendant
     * @return int
     * @throws SQLException
     *
     */
    public int getSize(Connection conn, String descendant, String table) throws SQLException
    {
        String sql = "select count(*) as size from " + table + " where descendant = '" + descendant + "'";
        int size = 0;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next())
        {
            size = rs.getInt("size");
        }
        return size;
    }
}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MODELS.Person;
import SERVICES.DatabaseException;

/** Person Database-Access Object
 *
 * @author Rees Thomas
 *
 */
public class PersonDAO
{
    /** Creates a new Person object in database
     *
     * @param conn
     * @param person
     * @throws DatabaseException
     *
     */
    public void create(Connection conn, Person person) throws DatabaseException
    {
        try {
            String sql = "INSERT INTO personTable (personID, descendant, firstName, " +
                         "lastName, gender, father, mother, spouse) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getDescendant());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFather());
            stmt.setString(7, person.getMother());
            stmt.setString(8, person.getSpouse());

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Person Insert failed");
        }
    }

    /** Returns a Person object with specified personID
     *
     * @param conn
     * @param personID
     * @return Person
     * @throws DatabaseException
     *
     */
    public Person read(Connection conn, String personID) throws DatabaseException
    {
        try {
            String sql = "select * from personTable where personID = '" + personID + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Person temp = null;
            while (rs.next())
            {
                temp = new Person("","","","","","","","");
                temp.setPersonID(rs.getString("personID"));
                temp.setDescendant(rs.getString("descendant"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setGender(rs.getString("gender"));
                temp.setFather(rs.getString("father"));
                temp.setMother(rs.getString("mother"));
                temp.setSpouse(rs.getString("spouse"));
            }

            stmt.close();
            rs.close();

            return temp;
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Person Read failed");
        }
    }

    /** Deletes/updates person at specified personID
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
            throw new DatabaseException("Person Delete failed");
        }
    }

    /** Returns all persons that belong to the specified user
     *
     * @param conn
     * @param descendant
     * @return Person[]
     * @throws DatabaseException
     *
     */
    public Person[] readAll(Connection conn, String descendant) throws DatabaseException
    {
        try {
            int size_of_table = new EventDAO().getSize(conn, descendant, "personTable");

            String sql = "select * from personTable where descendant = '" + descendant + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int i = 0;
            Person[] persons = new Person[size_of_table];
            while (rs.next())
            {
                Person temp = new Person("","","","","","","","");
                temp.setPersonID(rs.getString("personID"));
                temp.setDescendant(rs.getString("descendant"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setGender(rs.getString("gender"));
                temp.setFather(rs.getString("father"));
                temp.setMother(rs.getString("mother"));
                temp.setSpouse(rs.getString("spouse"));
                persons[i] = temp;
                i++;
            }

            stmt.close();
            rs.close();

            return persons;
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Person ReadAll failed");
        }
    }
}

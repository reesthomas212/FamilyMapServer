package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MODELS.User;
import SERVICES.DatabaseException;

/** User Database-Access Object
 *
 * @author Rees Thomas
 *
 */
public class UserDAO
{
    /** Creates new user
     *
     * @param conn
     * @param user
     * @throws DatabaseException
     *
     */
    public void create(Connection conn, User user) throws DatabaseException
    {
        try {
            String sql = "INSERT INTO userTable(username, password, emailAddress, " +
                         "firstName, lastName, gender, personID) " +
                         "VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmailAddress());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e)
        {
            throw new DatabaseException("User Insert failed");
        }
    }

    /** Returns user object
     *
     *  @param conn
     *  @param username
     *  @return User
     *
     */
    public User read(Connection conn, String username) throws DatabaseException
    {
        try {
            String sql = "select * from userTable where username = '" + username + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            User temp = null;
            while (rs.next())
            {
                temp = new User("", "", "", "", "", "", "");
                temp.setUsername(rs.getString("username"));
                temp.setPassword(rs.getString("password"));
                temp.setEmailAddress(rs.getString("emailAddress"));
                temp.setFirstName(rs.getString("firstName"));
                temp.setLastName(rs.getString("lastName"));
                temp.setGender(rs.getString("gender"));
                temp.setPersonID(rs.getString("personID"));
            }

            stmt.close();
            rs.close();

            return temp;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new DatabaseException("User Read failed");
        }
    }
}

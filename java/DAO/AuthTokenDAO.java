package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MODELS.AuthToken;
import SERVICES.DatabaseException;

/** AuthToken Database-Access Object
 *
 * @author Rees Thomas
 *
 */
public class AuthTokenDAO
{
    /** Creates new AuthToken object
     *
     * @param conn
     * @param authtoken
     * @throws DatabaseException
     *
     */
    public void create(Connection conn, AuthToken authtoken) throws DatabaseException
    {
        try {
            String sql = "INSERT INTO authTokenTable (authToken, userName) " +
                         "VALUES (?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, authtoken.getAuthToken());
            stmt.setString(2, authtoken.getUserName());

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e)
        {
            throw new DatabaseException("AuthToken Insert failed");
        }
    }

    /** Returns a specified authToken object
     *
     * @param conn
     * @param authToken
     * @return AuthToken
     * @throws DatabaseException
     *
     */
    public AuthToken read(Connection conn, String authToken) throws DatabaseException
    {
        try {
            String sql = "select * from authTokenTable where authToken = '" + authToken + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            AuthToken temp = null;
            while (rs.next())
            {
                temp = new AuthToken("", "");
                temp.setAuthToken(rs.getString("authToken"));
                temp.setUserName(rs.getString("userName"));
            }

            stmt.close();
            rs.close();

            return temp;
        }
        catch (SQLException e)
        {
            throw new DatabaseException("AuthToken Read failed");
        }
    }
}

package SERVICES;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.AuthTokenDAO;
import MODELS.AuthToken;

/** Opens, runs, and closes transactions with the database
 *
 */
public class Database
{
    static
    {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /** Global connection instance
     *
     */
    public Connection connection;

    /** Opens connection to the database
     *
     * @throws DatabaseException
     *
     */
    public void openConnection() throws DatabaseException
    {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:familymapserver.sqlite";
            connection = DriverManager.getConnection(CONNECTION_URL);
            connection.setAutoCommit(false);
            createTables();
        }
        catch (SQLException e)
        {
            throw new DatabaseException("openConnection failed");
        }
    }

    /** Closes connection and determines whether to commit or rollback changes
     *
     * @param commit
     * @throws DatabaseException
     *
     */
    public void closeConnection(boolean commit) throws DatabaseException
    {
        try {
            if (commit)
            {
                connection.commit();
            }
            else
            {
                connection.rollback();
            }

            connection.close();
            connection = null;
        }
        catch (SQLException e)
        {
            throw new DatabaseException("closeConnection failed");
        }
    }

    /** Creates SQL tables if they don't already exist
     *
     * @throws DatabaseException
     *
     */
    public void createTables() throws DatabaseException
    {
        try {
            Statement stmt = null;
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(readFileAsString("schema.txt"));
            }
            finally
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("createTables failed");
        }
    }

    /** Checks database to see if the authToken exists
     *
     * @param authToken
     * @param connection
     * @return boolean
     * @throws DatabaseException
     *
     */
    public boolean checkAuthToken(String authToken, Connection connection) throws DatabaseException
    {
        boolean check = false;

        AuthTokenDAO dao = new AuthTokenDAO();
        try {
            AuthToken a = dao.read(connection, authToken);
            if (a != null)
            {
                check = true;
            }
            else
            {
                throw new DatabaseException("Invalid authToken");
            }
        }
        finally
        {
            return check;
        }
    }

    /** Reads a file and returns it as a String
     *
     * @param fileName
     * @return
     *
     */
    private static String readFileAsString(String fileName)
    {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return text;
    }
}
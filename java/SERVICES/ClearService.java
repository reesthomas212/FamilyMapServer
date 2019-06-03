package SERVICES;

import java.sql.Statement;

import COMMUNICATION.ClearResult;

/** Service class for /clear/ handle
 *
 * @author Rees Thomas
 *
 */
public class ClearService
{
    /** Instance of Database communicator object
     *
     */
    public Database db = new Database();

    /** Message used to communicate success or failure
     *
     */
    public String message;

    /** Drops tables and re-creates them
     *
     * @return ClearResult
     *
     */
    public ClearResult clear()
    {
        try {
            Database db = new Database();
            db.openConnection();

            String sql = "DROP TABLE userTable;\n" +
                         "DROP TABLE personTable;\n" +
                         "DROP TABLE eventTable;\n" +
                         "DROP TABLE authTokenTable;";

            Statement stmt = db.connection.createStatement();
            stmt.executeUpdate(sql);

            db.createTables();

            db.closeConnection(true);
            message = "Clear Succeeded";
        }
        catch (DatabaseException e)
        {
            message = e.MESSAGE;
            db.closeConnection(false);
            e.printStackTrace();
        }
        finally
        {
            ClearResult rslt = new ClearResult(message);
            return rslt;
        }
    }
}

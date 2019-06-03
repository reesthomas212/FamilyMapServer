package SERVICES;

/** Class used to validate AuthTokens
 *
 */
public class AuthTokenCheck
{
    /** Checks to see if AuthToken is in the database
     *
     * @param authToken
     * @return boolean
     *
     */
    public boolean checkAuthToken(String authToken)
    {
        Database db = new Database();
        boolean check = false;
        try {
            db.openConnection();

            if (db.checkAuthToken(authToken, db.connection))
                check = true;

            db.closeConnection(false);
        }
        catch (DatabaseException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return check;
        }
    }
}

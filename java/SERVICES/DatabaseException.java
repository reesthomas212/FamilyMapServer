package SERVICES;

/** Exception class used for database error messages
 *
 */
public class DatabaseException extends Exception
{
    /** String for error messages
     *
     */
    public String MESSAGE;

    /** DatabaseException constructor
     *
     * @param message
     *
     */
    public DatabaseException(String message)
    {
        MESSAGE = message;
    }
}

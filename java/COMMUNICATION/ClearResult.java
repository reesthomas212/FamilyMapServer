package COMMUNICATION;

/** Response communication object for /clear/
 *
 * @author Rees Thomas
 *
 */
public class ClearResult
{
    /** Create a ClearResult object
     *
     * @param message
     *
     */
    public ClearResult(String message)
    {
        this.message = message;
    }

    /** Success/Failure message
     *
     */
    public String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}

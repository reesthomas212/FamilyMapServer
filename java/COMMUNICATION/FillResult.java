package COMMUNICATION;

/** Response object for /fill/[username]
 *
 * @author Rees Thomas
 *
 */
public class FillResult
{
    /** Create a new FillResult object
     *
     */
    public FillResult() {}

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

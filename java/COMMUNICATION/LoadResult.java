package COMMUNICATION;

/** Response object for /load/
 *
 * @author Rees Thomas
 *
 */
public class LoadResult
{
    /** Description of LoadResult()
     *
     * @param message
     */
    public LoadResult(String message)
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

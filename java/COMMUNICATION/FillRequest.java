package COMMUNICATION;

/** Request object for /fill/[username]/
 *
 * @author Rees Thomas
 *
 */
public class FillRequest
{
    /** Create a new FillRequest object
     *
     * @param username
     * @param generations
     */
    public FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    /** Username of user to generate data to
     *
     */
    public String username;

    /** Number of generations to fill user's ancestry with
     *
     */
    public int generations;


    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getGenerations()
    {
        return generations;
    }

    public void setGenerations(int generations)
    {
        this.generations = generations;
    }
}

package DATA;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Random;

import SERVICES.DatabaseException;

/** Data object for choosing random surnames for database
 *
 * @author Rees Thomas
 *
 */
public class Snames
{
    /** Array of surnames
     *
     */
    public String[] data;

    public String[] getSurnames()
    {
        return data;
    }

    /** Returns a random surname as a string
     *
     * @return String
     * @throws DatabaseException
     *
     */
    public String randomSurname() throws DatabaseException
    {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json\\snames.json");
            Snames SNAMES = gson.fromJson(reader, Snames.class);
            data = SNAMES.getSurnames();
        }
        catch (FileNotFoundException e)
        {
            throw new DatabaseException("SNAMES.JSON NOT FOUND");
        }
        finally
        {
            int i = new Random().nextInt(data.length);
            return data[i];
        }
    }
}


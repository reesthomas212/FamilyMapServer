package DATA;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Random;

import SERVICES.DatabaseException;

/** Data object for choosing random female names for database
 *
 * @author Rees Thomas
 *
 */
public class Fnames
{
    /** Array of female names
     *
     */
    public String[] data;

    public String[] getFemaleNames()
    {
        return data;
    }

    /** Returns a random female name as a string
     *
     * @return String
     * @throws DatabaseException
     *
     */
    public String randomFemaleName() throws DatabaseException
    {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json\\fnames.json");
            Fnames FNAMES = gson.fromJson(reader, Fnames.class);
            data = FNAMES.getFemaleNames();
        }
        catch (FileNotFoundException e)
        {
            throw new DatabaseException("FNAMES.JSON NOT FOUND");
        }
        finally
        {
            int i = new Random().nextInt(data.length);
            return data[i];
        }
    }
}


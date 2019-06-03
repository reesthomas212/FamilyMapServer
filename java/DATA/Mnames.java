package DATA;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Random;

import SERVICES.DatabaseException;

/** Data object for choosing random male names for database
 *
 * @author Rees Thomas
 *
 */
public class Mnames
{
    /** Array of male names
     *
     */
    public String[] data;

    public String[] getMaleNames()
    {
        return data;
    }

    /** Returns a random male name as a string
     *
     * @return String
     * @throws DatabaseException
     *
     */
    public String randomMaleName() throws DatabaseException
    {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json\\mnames.json");
            Mnames MNAMES = gson.fromJson(reader, Mnames.class);
            data = MNAMES.getMaleNames();
        }
        catch (FileNotFoundException e)
        {
            throw new DatabaseException("MNAMES.JSON NOT FOUND");
        }
        finally
        {
            int i = new Random().nextInt(data.length);
            return data[i];
        }
    }
}

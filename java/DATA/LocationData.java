package DATA;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Random;

import SERVICES.DatabaseException;

/** Data object for getting random locations
 *
 * @author Rees Thomas
 *
 */
public class LocationData
{
    /** Data object for holding location data
     *
     */
    public class Location
    {
        public String latitude;
        public String longitude;
        public String city;
        public String country;
    }

    public Location[] getData()
    {
        return data;
    }

    /** Array of Locations
     *
     */
    public Location[] data;

    /** Returns a random Location object
     *
     * @return Location
     * @throws DatabaseException
     *
     */
    public Location randomLocation() throws DatabaseException
    {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json\\locations.json");
            LocationData locData = gson.fromJson(reader, LocationData.class);
            data = locData.getData();
        }
        catch (FileNotFoundException e)
        {
            throw new DatabaseException("LOCATIONS.JSON NOT FOUND");
        }
        finally
        {
            int i = new Random().nextInt(data.length);
            return data[i];
        }
    }
}

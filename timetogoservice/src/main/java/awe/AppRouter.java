package awe;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.schildbach.pte.MvvProvider;
import de.schildbach.pte.dto.Location;
import de.schildbach.pte.dto.LocationType;
import de.schildbach.pte.dto.NearbyLocationsResult;
import de.schildbach.pte.dto.SuggestLocationsResult;
import de.schildbach.pte.dto.QueryDeparturesResult;

import java.io.IOException;
import java.util.Date;
import java.util.EnumSet;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class AppRouter {

    private final MvvProvider provider = new MvvProvider();
    
    /**
     * Query upcoming departures for a station.
     * 
     * <b>Note for Javascript devs:</b>
     * The epochTime can be easily generated on the client with
     * <code>Math.floor((new Date).getTime()/1000);</code>
     * 
     * Example for Plattlinger Straße
     * http://127.0.0.1:8080/departures?stopId=1001403
     * 
     * @param stopId - Id of the stop to query for
     * @param epochTime - Time since Unix Epoch (optional)
     * @param maxDepartures - Max number of departure entries to return (optional)
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/departures", method = RequestMethod.GET)
    public QueryDeparturesResult departures(
            @RequestParam(value = "stopId", defaultValue = "-1") final String stopId,
            @RequestParam(value = "time", defaultValue = "-1") final long epochTime,
            @RequestParam(value = "maxDepartures", defaultValue = "16") final int maxDepartures)
            throws IOException {

        Date dateTime = (epochTime == -1) ? new Date() : new Date(epochTime); 
        boolean equivs = false;
        return provider.queryDepartures(stopId, dateTime, maxDepartures, equivs);
    }

    /**
     * Find station id based on coordinates from Google Maps, multiplied by 1000000 
     * Example:
     * http://127.0.0.1:8080/near-point?lat=48078804&long=11512663&range=100&maxResults=100
     * 
     * @param lat - Latitude, multiplied by 1000000
     * @param lon - Longitude, multiplied by 1000000
     * @param rangeInMeters
     * @param maxResults
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/near-point", method = RequestMethod.GET)
    public NearbyLocationsResult nearPoint(@RequestParam("lat") final int lat, @RequestParam("long") final int lon,
            @RequestParam(value = "range", defaultValue = "1000") final int rangeInMeters,
            @RequestParam(value = "maxResults", defaultValue = "10") final int maxResults)
            throws IOException {
        final Location coord = Location.coord(lat, lon); // lat and lon are regular coords multiplied by 1000000
        return provider.queryNearbyLocations(EnumSet.of(LocationType.STATION), coord, rangeInMeters, maxResults);
    }

}

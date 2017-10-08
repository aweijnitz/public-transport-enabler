package awe;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.schildbach.pte.RtProvider;
import de.schildbach.pte.dto.Location;
import de.schildbach.pte.dto.LocationType;
import de.schildbach.pte.dto.NearbyLocationsResult;
import de.schildbach.pte.dto.SuggestLocationsResult;

import awe.TimeLeft;
import java.io.IOException;
import java.util.EnumSet;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class TimeToGoController {

    private final RtProvider provider = new RtProvider();
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/timeleft", method = RequestMethod.GET)
    public TimeLeft timeleft(@RequestParam(value = "stopId", defaultValue = "-1") final int stopId) {
        // TODO: Implement functions that returns the time left until next departure
        return new TimeLeft(10, stopId);
    }

    @RequestMapping(value = "/near-point", method = RequestMethod.GET)
    public NearbyLocationsResult nearPoint(@RequestParam("lat") final int lat, @RequestParam("long") final int lon,
            @RequestParam(value = "range", defaultValue = "1000") final int rangeInMeters,
            @RequestParam(value = "maxResults", defaultValue = "10") final int maxResults)
            throws IOException {
        final Location coord = Location.coord(lat, lon); // lat and lon are regular coords multiplied by 1000000
        return provider.queryNearbyLocations(EnumSet.of(LocationType.STATION), coord, rangeInMeters, maxResults);
    }


}

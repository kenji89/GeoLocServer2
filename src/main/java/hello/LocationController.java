package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {
	@Autowired
	private LocationRepository locationRepository;
	
	@RequestMapping("/latest")
	public Location getLatest(){
		List<Location> locations = locationRepository.findAll();
		if (locations.isEmpty())
			return null;
		Location newest = locations.get(0);
		for (Location l : locations){
			if (l.getTimestamp().after(newest.getTimestamp()))
				newest = l;
		}
		return newest;
	}
}

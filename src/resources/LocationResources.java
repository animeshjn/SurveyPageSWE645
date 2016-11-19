/*Animesh Jain MSCS| Class of 2018 | G01043755 |ajain13@masonlive.gmu.edu*/
package resources;

import java.util.*; 


import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/locations")
public class LocationResources {
	
	Map <Integer,Location> locations;

	public LocationResources() {
		super();
		//Construct Resources
		locations=new HashMap<Integer,Location>();
		locations.put(22312,new Location("VA", "Alexandria", 22312));
		locations.put(22030,new Location("VA", "Fairfax", 22030));
		locations.put(22301,new Location("MD", "Tysons Corner", 22301));
		locations.put(20148,new Location("VA", "Aushburn", 20148));
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("{zip}")
	public String LookUp(@PathParam("zip") int zip){
		
		Location matchedLocation=locations.get(zip);
			
		String cityState=new String(matchedLocation.getCity()+","+matchedLocation.getState());
		
		return cityState;
	}
	

	
	
}

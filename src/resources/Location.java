/*Animesh Jain MSCS| Class of 2018 | G01043755 |ajain13@masonlive.gmu.edu*/
package resources;




public class Location {
	private String state;
	private String city;
	private int zip;
	
	public Location(String state,String city, int zip)
	{
		this.state=state;
		this.zip=zip;
		this.city=city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	
	
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

		
	
	
}
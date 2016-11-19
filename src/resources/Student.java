/*Animesh Jain MSCS| Class of 2018 | G01043755 |ajain13@masonlive.gmu.edu*/
/*Student.java POJO to store all the data form form*/

package resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@NamedQueries(value = { @NamedQuery(name = "selectall", query = "SELECT s FROM Student s"),
		@NamedQuery(name = "searchfirstname", query = "SELECT s FROM Student s WHERE s.firstname=:fname"),
		@NamedQuery(name = "searchlastname", query = "SELECT s FROM Student s WHERE s.lastname=:lname"),
		@NamedQuery(name = "searchcity", query = "SELECT s FROM Student s WHERE s.city=:city"),
		@NamedQuery(name = "searchstate", query = "SELECT s FROM Student s WHERE s.state=:state"),
		@NamedQuery(name = "searchall", query = "SELECT s FROM Student s WHERE s.city=:city AND s.state=:state AND s.firstname=:fname AND s.lastname=:lname"),
		@NamedQuery(name = "searchfnamelname", query = "SELECT s FROM Student s WHERE s.firstname=:fname AND s.lastname=:lname"),
		@NamedQuery(name = "searchfnamecity", query = "SELECT s FROM Student s WHERE s.firstname=:fname AND s.city=:city"),
		@NamedQuery(name = "searchfnamestate", query = "SELECT s FROM Student s WHERE s.state=:state AND s.firstname=:firstname")

})
@Entity
@Table
public class Student implements Serializable {
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int sid;
	String firstname;
	String lastname;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	String streetaddress;
	String city;
	String state;
	int zip;
	String telephone;
	String mail;
	Date date;
	List<String> liked;
	String interest;
	String recommend;
	String raffle;
	String comments;
	double mean;
	double diviation;
	Date semesterDate;

	@Column
	public Date getSemesterDate() {
		return semesterDate;
	}

	public void setSemesterDate(Date semesterDate) {
		this.semesterDate = semesterDate;
	}

	@Column
	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	@Column
	public double getDiviation() {
		return diviation;
	}

	@Column
	public void setDiviation(double diviation) {
		this.diviation = diviation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sid")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Column
	@ElementCollection(fetch = FetchType.EAGER)
	public List<String> getLiked() {

		return liked;
	}

	public void setLiked(List<String> liked) {
		this.liked = liked;
	}

	@Column
	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	@Column
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	@Column
	public String getRaffle() {
		return raffle;
	}

	public void setRaffle(String raffle) {
		// seperate commas
		// add to List
		// set value
		// ArrayList<String> raffleList = new ArrayList<String>();
		// String raffles[] = raffle.split(",");
		// for (String string : raffles) {
		// raffleList.add(string);
		// }

		this.raffle = raffle;
	}

	@Column
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column
	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	@Column
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column
	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;

	}

	@Column
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

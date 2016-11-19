/*Animesh Jain MSCS| Class of 2018 | G01043755 |ajain13@masonlive.gmu.edu*/
/*SurveyBean.java is the Managed Bean also stores output data*/
package resources;

import java.io.Serializable;

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ManagedBean

public class SurveyBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SearchBean searchBean=new SearchBean();
	
	public SearchBean getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final String[] recoArray = { "Very Likely", "Likely", "Unlikely" };

	Student student = new Student();
	String pages[] = { "Acknowledgement" };
	WinningResult ws = new WinningResult();
	ArrayList<Student> sArray = new ArrayList<Student>();
	
	
	public List<String> completeReco(String recoPrefix) {
		List<String> matches = new ArrayList<String>();
		for (String possible : recoArray) {
			if (possible.toUpperCase().startsWith(recoPrefix.toUpperCase())) {
				matches.add(possible);
			}
		}
		return (matches);
	}

	public static String[] getRecoArray() {
		return recoArray;
	}

	public ArrayList<Student> getsArray() {
		return sArray;
	}

	public void setsArray(ArrayList<Student> sArray) {
		this.sArray = sArray;
	}

	public WinningResult getWs() {
		return ws;
	}

	public void setWs(WinningResult ws) {
		this.ws = ws;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void validateSemesterStartDate(FacesContext context, UIComponent componentToValidate, Object value)
			throws ValidatorException {

		Date semStartDate = ((Date) value);
		Object surveyDateValue = componentToValidate.getAttributes().get("dateOfSurvey");
		Date surveyDate = (Date) ((org.primefaces.component.calendar.Calendar) surveyDateValue).getValue();

		if (semStartDate.before(surveyDate)) {

			FacesMessage message = new FacesMessage("Semester start date cannot be before date of survey");
			throw new ValidatorException(message);
		}

	}


		
	public String[] getPages() {
		return pages;
	}

	public void setPages(String[] pages) {
		this.pages = pages;
	}

	public static String[] getRecoarray() {
		return recoArray;
	}



	
	public void renderStateCity(){
		
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/HW3/webresources/locations/");
        WebTarget resourceWebTarget;
        resourceWebTarget = target.path(new String(this.student.getZip()+""));
       
       
        Invocation.Builder invocationBuilder;
        invocationBuilder = resourceWebTarget.request(MediaType.TEXT_PLAIN);
        System.out.println(resourceWebTarget.getUri());
        Response response = invocationBuilder.get();
        System.out.println(response.getStatus());
       
        String s = response.readEntity(String.class);
        System.out.println("City"+s.split("-")[0]);
        System.out.println("State"+s.split("-")[1]);
        
        student.setCity(s.split(",")[0]);
        student.setState(s.split(",")[1]);

		
	}



	public String save() {

		// handle database code here
//		System.out.println("bean  " + student.getDate());
//		System.out.println(student.getRaffle());
		
		
		StudentService service = new StudentService();
		
		
		
		double mean=service.calculateMean(student);
		double deviation=service.calculateStandardDiviation(student);
		
		ws.setMean(mean);
		student.setMean(mean);
		ws.setStandardDeviation(deviation);
		student.setDiviation(deviation);
		student.setDiviation(ws.getStandardDeviation());
		student.setMean(ws.getMean());
		
		System.out.println("Persisting Data... ");
		try{
		Data.storeData(student);}
		catch(Exception e){
			System.out.println("Persisting Unsucessful");
			e.printStackTrace();
		
		}
		System.out.println("Data Persisted ");
		
//		service.processData(student);
		
		
		
		
		if (ws.getMean() > 90) {
			return "winningResult";
		} else
			return "SimpleAcknowledgement";
	}

public ArrayList<Student> getsArrayFetch() {

		EntityManager em= Data.getEntityManager();
		
		
		return (ArrayList<Student>) em.createNamedQuery("selectall",Student.class).getResultList();
	}

public void renderAll(){
		

			LocationResources lr=new LocationResources();
			String s=lr.LookUp(student.getZip());
			student.setCity(s.split(",")[0]);
	        student.setState(s.split(",")[1]);

		
	}


//public List<Student> getsearch()
//{EntityManager em= Data.getEntityManager();
//
//return em.createNamedQuery("searchfirstname",Student.class).setp.getResultList();	
//}

}

package resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@ManagedBean(name="searchBean")
@SessionScoped
public class SearchBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String city;
	private String state;
	
	ArrayList<Student> studentList=new ArrayList<Student>();
	
	public ArrayList<Student> getStudentList() {
		return studentList;
	}


		
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	//Action controller
	@SuppressWarnings("unchecked")
	public String searchSurvey(){
		
		EntityManager em=Data.getEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Student> criteria = builder.createQuery(Student.class);

	    Root<Student> student = criteria.from(Student.class);
	    
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    
	    
	    System.out.println(firstname);
	    System.out.println(lastname);
	    
	    if(!(firstname.trim().equals("")))
	    {	System.out.println("fname");
	    	predicates.add(builder.equal(student.get("firstname"), getFirstname()));
	    }
	    
	    if(!(lastname.trim().equals("")))
	    {	System.out.println("lname");
	    	predicates.add(builder.equal(student.get("lastname"), getLastname()));
	    }
	     
	    if(!(city.trim().equals("")))
	    {	System.out.println("city");
	    	predicates.add(builder.equal(student.get("city"), getCity()));
	    }
	    if(!(state.trim().equals("")))
	    {	System.out.println("state");
	    	predicates.add(builder.equal(student.get("state"), getState()));
	    }
	      
	    criteria.select(student)
        .where(predicates.toArray(new Predicate[]{}));
	    //execute query and do something with result
	    studentList= (ArrayList<Student>) em.createQuery(criteria).getResultList();
		for (Student stud : studentList) {
			System.out.println(stud.getFirstname());
		}
	    
		return "searchResult";
	}
	
	
	public void delete(int sid){
		System.out.println("called delete button");
		EntityManager em=Data.getEntityManager();
	    
		Map<String,String> params =
				 FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		  String action = params.get("student");
		//int sid=Integer.parseInt(action);
		//int sid= studentparam.getSid();
		System.out.println("SID DELETE ACTION:"+sid);
		Student student = em.find(Student.class, sid);
		System.out.println("begin");		 
		  em.getTransaction().begin();
		  em.remove(student);
		  em.getTransaction().commit();		
		 
		 System.out.println("sid"+sid);
		 searchSurvey();
//		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//		    try {
//				ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
		 
	}

	
	


}

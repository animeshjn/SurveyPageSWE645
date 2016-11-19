/*Animesh Jain MSCS| Class of 2018 | G01043755 |ajain13@masonlive.gmu.edu*/
/*StudentService.java contains conectivity code to RDS: Mysql retrieval and Insertion of data 
 * as well as Methods to calculate the Mean and Standard deviation*/
package resources;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

	String url="jdbc:mysql://survey.cvfj71f4z2w1.us-west-2.rds.amazonaws.com:3306/";
	String username="animeshj";
	String password="animeshj";

	

	public double calculateStandardDiviation(Student student){
		String raffles[]= (student.getRaffle()).split(",");	
		int raffleInts[]=new int[20];
		int i=0;
		int sum=0;
		double diviation;
		for (String string : raffles) {
			raffleInts[i]=Integer.parseInt(string);
			i++;
		}
		  
		 		
		double mean= calculateMean(student);
		for (int j = 0; j < raffles.length; j++) {
			sum+=Math.pow((raffleInts[j]-mean), 2);
			
		}
		diviation=Math.pow(sum/(raffles.length-1),1/2);
		
		
		return diviation;
	}
	
public double calculateMean(Student student){
	String raffles[]= (student.getRaffle()).split(",");	

  int sum=0;

  int i;
  for (i = 0; i < raffles.length; i++) 
  {
	  int currentRaffle=Integer.parseInt(raffles[i]);
	  sum+=currentRaffle;
	    }
  
	
  return sum/i;

}


}
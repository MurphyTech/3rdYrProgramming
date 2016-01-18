/*
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign3_part1;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudentData implements Serializable {
	private String Name;
	private String CourseCode;
	private int ID;
	//Constructors
	public StudentData(String name, String coursecode, int id) {
		setName(name);
		setCourseCode(coursecode);
		setID(id);
	}
	//Getter
	public String getName(){
		return this.Name;
	}
	//Setter
	public void setName(String name){
		this.Name=name;
	}
	//Getter
	public String getCpurseCode(){
		return this.CourseCode;
	}
	//Setter
	public void setCourseCode(String CC){
		this.CourseCode=CC;
	}
	//Getter
	public int getID(){
		return this.ID;
	}
	//Setter
	public void setID(int id){
		this.ID=id;	
	}
	//toString()
	public String toString(){
		return("Name: " + this.Name + "   CourseCode: " + this.CourseCode + "   ID: " +  this.ID);
	}
}

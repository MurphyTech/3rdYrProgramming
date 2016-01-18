/*
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign3_part2;

import java.io.IOException;
import java.io.Serializable;

@SuppressWarnings("serial")
public class StudentData_2 implements Serializable {
	private String Name;
	private String CourseCode;
	private int ID;
	//Constructors
	public StudentData_2(String name, String coursecode, int id) {
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
	//Custom writeObject() method
	private void writeObject(java.io.ObjectOutputStream stream) throws IOException{
		stream.writeObject(Name);//Serializing Name
		stream.writeObject(CourseCode);//Serializing CourseCode
		stream.writeInt(ID);//Serializing Id Number
		//Print Statement to make sure the program uses the custom method
		//System.out.println("Hey it's me, your custom writeObject()!");
	}
	
	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException{
		Name = (String) stream.readObject();//De-Serializing Name
		CourseCode = (String) stream.readObject();//De-Serializing CourseCode
		ID = stream.readInt();//De-Serializing ID Number
		//Print Statement to make sure the program uses the custom method
		//System.out.println("Hey it's me, your custom readObject()!");
	}
}

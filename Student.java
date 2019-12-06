import java.util.ArrayList;

public class Student extends Person {

	ArrayList<Integer> certificates;
	boolean enrolled;

	Student(String name, char gender, Integer age){
		super(name, gender, age);
		certificates = new ArrayList<Integer>();
		enrolled = false;
	}

	Student(Person person){
		this(person.getName(), person.getGender(), person.getAge());
	}
	
	void graduate(Subject subject){
		certificates.add(subject.getID());
		enrolled = false;
	}

	void dropCourse(){
		enrolled = false;
	}

	ArrayList<Integer> getCertificates(){
		return certificates;
	}

	boolean hasCertificate(Subject subject){
		return certificates.contains(subject.getID());
	}

	void enrol(){
		enrolled = true;
	}

	boolean isEnrolled(){
		return enrolled;
	}

	boolean completedAll(School school){
		if(certificates.size() == school.getSubjects().size()){
			return true;
		}
		return false;
	}
	public String toString() {
		String temp = super.toString();

		temp += "\n Completed courses: " + certificates.size();
		temp += "\n Currently in course: " + (enrolled ? "True" : "False") + "\n";

		return temp;
	}

	String toSave(){
		String temp = "student:" + super.toSave() + ":";
		temp += (enrolled ? "1" : "0") + ":";

		for(Integer id : certificates){
			temp += id + ",";
		}

		if(temp.endsWith(",")){
		  temp = temp.substring(0,temp.length() - 1);
		}

		return temp + "\n";
	}
}
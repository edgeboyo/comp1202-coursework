import java.util.ArrayList;

public class Student extends Person {

	ArrayList<Integer> certificates;
	boolean enrolled;

	Student(String name, char gender, Integer age){
		super(name, gender, age);
		certificates = new ArrayList<Integer>();
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
}
import java.util.ArrayList;

public class Student extends Person {

	ArrayList<Integer> certificates;

	Student(String name, char gender, Integer age){
		super(name, gender, age);
		certificates = new ArrayList<Integer>();
	}

	void graduate(Subject subject){
		certificates.add(subject.getID());
	}

	ArrayList<Integer> getCertificates(){
		return certificates;
	}

	boolean hasCertificate(Subject subject){
		return certificates.contains(subject.getID());
	}

}
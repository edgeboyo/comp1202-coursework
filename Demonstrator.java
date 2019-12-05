
public class Demonstrator extends Instructor {
	
	Demonstrator(String name, char gender, Integer age){
		super(name, gender, age);
	}

	boolean canTeach(Subject subject){
		if(subject.getSpecialism() == 2){
			return true;
		}
		return false;
	}
}
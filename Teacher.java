
public class Teacher extends Instructor {
	
	Teacher(String name, char gender, Integer age){
		super(name, gender, age);
	}

	boolean canTeach(Subject subject){
		if(subject.getSpecialism() < 3){
			return true;
		}
		return false;
	}
}
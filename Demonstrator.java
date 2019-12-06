
public class Demonstrator extends Instructor {
	
	Demonstrator(String name, char gender, Integer age){
		super(name, gender, age);
	}

	Demonstrator(Person person){
		this(person.getName(), person.getGender(), person.getAge());
	}

	boolean canTeach(Subject subject){
		if(subject.getSpecialism() == 2){
			return true;
		}
		return false;
	}
}
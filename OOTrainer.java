
public class OOTrainer extends Teacher {
	
	OOTrainer(String name, char gender, Integer age){
		super(name, gender, age);
	}

	OOTrainer(Person person){
		this(person.getName(), person.getGender(), person.getAge());
	}

	boolean canTeach(Subject subject){
		if(subject.getSpecialism() == 3 || super.canTeach(subject)){
			return true;
		}
		return false;
	}
}

public class GUITrainer extends Teacher {
	
	GUITrainer(String name, char gender, Integer age){
		super(name, gender, age);
	}

	GUITrainer(Person person){
		this(person.getName(), person.getGender(), person.getAge());
	}

	boolean canTeach(Subject subject){
		if(subject.getSpecialism() == 4 || super.canTeach(subject)){
			return true;
		}
		return false;
	}
}
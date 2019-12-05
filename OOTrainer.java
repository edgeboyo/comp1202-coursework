
public class OOTrainer extends Teacher {
	
	OOTrainer(String name, char gender, Integer age){
		super(name, gender, age);
	}

	boolean canTeach(Subject subject){
		if(subject.getSpecialism() == 3 || super.canTeach(subject)){
			return true;
		}
		return false;
	}
}
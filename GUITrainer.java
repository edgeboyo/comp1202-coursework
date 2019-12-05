
public class GUITrainer extends Teacher {
	
	GUITrainer(String name, char gender, Integer age){
		super(name, gender, age);
	}

	boolean canTeach(Subject subject){
		if(subject.getSpecialism() == 4 || super.canTeach(subject)){
			return true;
		}
		return false;
	}
}
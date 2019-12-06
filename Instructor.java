
public abstract class Instructor extends Person {
	Course assignedCourse;

	Instructor(String name, char gender, Integer age){
		super(name, gender, age);
	}

	void assignCourse(Course course){
		assignedCourse = course;
	}

	void unassignCourse(){
		assignedCourse = null;
	}

	Course getAssignedCourse(){
		return assignedCourse;
	}
	
	abstract boolean canTeach(Subject subject);

	public String toString(){
		String temp = super.toString();
		if(assignedCourse == null)
			temp += " No course assigned\n";
		else
			temp += "\n Assigned course: " + assignedCourse.getSubject().getDescription() + "\n";
		return temp;
	}

	String toSave(School school){
		String temp = super.toSave();

		temp+= ":" + (assignedCourse == null ? -1 : school.getCourses().indexOf(assignedCourse));

		String type = new String();

		if(this instanceof Teacher){
			type = "Teacher:";
		}
		if(this instanceof Demonstrator){
			type = "Demonstrator:";
		}
		if(this instanceof GUITrainer){
			type = "GUITrainer:";
		}
		if(this instanceof OOTrainer){
			type = "OOTrainer:";
		}


		return type + temp + "\n";
	}
}

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
}
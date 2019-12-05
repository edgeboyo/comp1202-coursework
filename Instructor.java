
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
}
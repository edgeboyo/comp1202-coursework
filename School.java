import java.util.ArrayList;

/**
 * School class
 * Contains all the Subjects, Courses, Students
 * and instructors and runs a single timestep on them
 */
public class School {
	
	String name;

	ArrayList<Subject> subjects;
	ArrayList<Course> courses;
	ArrayList<Student> students;
	ArrayList<Instructor> instructors;

	School(){
		name = "PLACEHOLDER HIGH";
		subjects = new ArrayList<Subject>();
		courses = new ArrayList<Course>();
		students = new ArrayList<Student>();
		instructors = new ArrayList<Instructor>();
	}

	School(String name){
		this();
		this.name = name;
	}

	/**
	 * Overloaded adder function for each type of array
	 */
	void add(Subject subject){
		subjects.add(subject);
	}
	
	void add(Course course){
		courses.add(course);
	}

	void add(Student student){
		students.add(student);
	}

	void add(Instructor instructor){
		instructors.add(instructor);
	}

	/**
	 * Overloaded remover function for each type of array
	 */
	void remove(Subject subject){
		subjects.remove(subject);
	}
	
	void remove(Course course){
		courses.remove(course);
	}

	void remove(Student student){
		students.remove(student);
	}

	void remove(Instructor instructor){
		instructors.remove(instructor);
	}

	/**
	 * Getter function for each type of array
	 */
	ArrayList<Subject> getSubjects(){
		return subjects;
	}

	ArrayList<Course> getCourses(){
		return courses;
	}

	ArrayList<Student> getStudents(){
		return students;
	}

	ArrayList<Instructor> getInstructors(){
		return instructors;
	}

	/**
	 * Return the name of the School class
	 */
	String getName(){
		return name;
	}

	/**
	 * Generates a status report on all entries in all arrays
	 */
	public String toString(){
		String status = new String();
		String divider = new String("\n");


		status += "\n-COURSES\n";
		for(Course course : courses){
			status += course + divider + "\n";
		}


		status += "\n-STUDENTS\n";
		for(Student student : students){
			status += student + divider + "\n";
		}


		status += "\n-INSTRUCTORS\n";
		for(Instructor instructor : instructors){
			status += instructor + divider + "\n";
		}

		return status + "\n\n";
	}

	/**
	 * Each day do the following actions...
	 */
	public void aDayInSchool(){

		/**
		 * Creates courses for each non open to registration
		 * subject
		 */
		for(Subject subject : subjects){
			if(!subject.checkAssign()){
				courses.add(new Course(subject, 2));
			}
		}

		/**
		 * Assign Instructor to a course he can teach
		 */
		for(Course course : courses){
			if(!course.hasInstructor()){
				for(Instructor instructor : instructors){
					if(course.setInstructor(instructor)){
						break;
					}
				}
			}
		}

		/**
		 * Enroll free students to subjects they have never done
		 */
		for(Student student : students){
			if(!student.isEnrolled()){
				for(Course course : courses){
					if(course.enrolStudent(student)){
						break;
					}
				}
			}
		}

		/**
		 * Mark and remove courses that are cancelled or done
		 */
		ArrayList<Course> forRemoval = new ArrayList<Course>();
		for(Course course : courses){
			course.aDayPasses();
			if(course.toBeRemoved()){
				forRemoval.add(course);
			}
		}

		for(Course course : forRemoval){
			courses.remove(course);
		}
	}
}
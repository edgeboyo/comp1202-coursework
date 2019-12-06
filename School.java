import java.util.ArrayList;

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

	public String toString(){
		String status = new String();
		String divider = new String(" ************");
		status += "-SUBJECTS\n";


		for(Subject subject : subjects){
			status += subject + divider + "\n";
		}


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

	public void aDayInSchool(){
		for(Subject subject : subjects){
			if(!subject.checkAssign()){
				courses.add(new Course(subject, 2));
			}
		}

		for(Course course : courses){
			if(!course.hasInstructor()){
				for(Instructor instructor : instructors){
					if(course.setInstructor(instructor)){
						break;
					}
				}
			}
		}

		for(Student student : students){
			if(!student.isEnrolled()){
				for(Course course : courses){
					Integer id = course.getSubject().getID();
					if(course.enrolStudent(student)){
						break;
					}
				}
			}
		}

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
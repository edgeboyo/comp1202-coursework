import java.util.ArrayList;

/**
 * Course that tracks progress of a Subject
 * being taught to a group of students by an appropriate
 * instructor
 */
public class Course {
	
	//used to identify itself and timestep
	Subject subject;
	Integer daysUntilStarts;
	Integer daysToRun;

	//this class will be affecting these classes when something happenes
	ArrayList<Student> enrolled;
	Instructor instructor;

	//markers to make create easier access
	boolean cancelled;
	boolean finished;
 
 	/**
  	 * Creates a course with a particular subject 
  	 */
  	Course(Subject subject){
  		this.subject = subject;
		daysToRun = subject.getDuration();
		enrolled = new ArrayList<Student>();
		cancelled = false;
		finished = false;
  	}

	Course(Subject subject, int toStart){
		this.subject = subject;
		this.daysUntilStarts = toStart;
		subject.assign();
		daysToRun = subject.getDuration();
		enrolled = new ArrayList<Student>();
		cancelled = false;
		finished = false;
	}

	/**
	 * Tells what subjects is being taught
	 */
	Subject getSubject(){
		return subject;
	}

	/**
	 * Returns 0 if course completed, negative if still waiting start, positive
	 * if registration stopped and course started
	 */
	int getStatus(){
		if(!daysUntilStarts.equals(0)){
			return -1 * daysUntilStarts;
		}
		return daysToRun;
	}

	/**
	 * Timestep by one day
	 */
	void aDayPasses(){
		
		/**
		 * Modify wait time if still exists
		 */
		if(!daysUntilStarts.equals(0)){
			daysUntilStarts--;
			if((!hasInstructor() || getSize() == 0) && daysUntilStarts.equals(0)){
				if(hasInstructor())
					instructor.unassignCourse();
				for(Student student : enrolled){
					student.dropCourse();
				}
				subject.unassign();
				instructor = null;
				enrolled = new ArrayList<Student>();
				cancelled = true;
			} else if (daysUntilStarts.equals(0)) {
				subject.unassign();
			}
			return;
		}

		/**
		 * Modify run time if still exists
		 */
		if(!daysToRun.equals(0)){
			daysToRun--;
		}
		/**
		 * If couse finished un-assigned people from it
		 */
		if(daysToRun.equals(0) && !finished){
			for(Student student : enrolled){
				student.graduate(subject);

			}
			instructor.unassignCourse();
			instructor = null;
			finished = true;
		}
	}

	/**
	 * Check if [student] can be enrolled and if yes return true
	 * and add to the enrollment list
	 */
	boolean enrolStudent(Student student){
		if(daysUntilStarts.equals(0) || enrolled.size() == 3 || student.getCertificates().contains(subject.getID())){
			return false;
		}
		enrolled.add(student);
		student.enrol();
		return true;
	}

	/**
	 * Return amount of prople enrolled in the course
	 */
	int getSize(){
		return enrolled.size();
	}

	/**
	 * Return array of Students
	 */
	Student[] getStudents(){
		return enrolled.toArray(new Student[enrolled.size()]);
	}

	/**
	 * Check if [instructor] can beach course and if yes return true
	 * and assign instructor
	 */
	boolean setInstructor(Instructor instructor){
		if(instructor.canTeach(subject) && instructor.getAssignedCourse() == null){
			this.instructor = instructor;
			instructor.assignCourse(this);
			return true;
		}
		return false;
	}

	/**
	 * Show if course has viable constructor
	 */
	boolean hasInstructor(){
		return instructor == null ? false : true;
	}
	/**
	 * Show if course is cancelled
	 */
	boolean isCancelled(){
		return cancelled;
	}

	/**
	 * Show if course is cancelled or finished
	 */
	boolean toBeRemoved(){
		if(isCancelled() || getStatus() == 0){
			return true;
		}
		return false;
	}

	/**
	 * Paramenters assigned at wait time and work time
	 * Used when using saved session
	 */
	void timeSet(Integer timeOpen, Integer timeWork){
		daysUntilStarts = timeOpen;
		daysToRun = timeWork;
	}

	/**
	 * Show information about the class
	 * Used for status reports
	 */
	public String toString(){
		String temp = new String();

		if(cancelled){
			temp+="WARNING COURSE SHOULD BE CANCELLED!\n";
		}

		temp += " Course subject ID: " + subject.getID();
		temp += "\n " + (getStatus() < 0 ? "Due to start" : "Finishes") + " in: " + Math.abs(getStatus());
		temp += "\n " + (instructor == null ? "Instructor not assigned" : "Instructor: " + instructor.getName());
		temp += "\n Amount of students enrolled: " + enrolled.size() + "\n Enrolled list:\n";

		for(Student student : enrolled){
			temp+= "  * " + student.getName() + "\n";
		}
		return temp;
	}

	/**
	 * Creates a save entry for the class
	 */
	String toSave(School school){
		String temp = new String();

		temp += "course:";

		temp += school.getSubjects().indexOf(subject) + "," + daysUntilStarts + "," + daysToRun + ",";

		temp+=":";

		for(Student student : enrolled){
			temp += school.getStudents().indexOf(student) + ",";
		}

		if(temp.endsWith(",")){
		  temp = temp.substring(0,temp.length() - 1);
		}

		return temp + "\n";
	}
}

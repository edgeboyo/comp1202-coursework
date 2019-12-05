import java.util.ArrayList;

public class Course {
	
	Subject subject;
	Integer daysUntilStarts;
	Integer daysToRun;

	ArrayList<Student> enrolled;
	Instructor instructor;

	boolean cancelled;
	boolean finished;

	Course(Subject subject, int toStart){
		this.subject = subject;
		this.daysUntilStarts = toStart;
		daysToRun = subject.getDuration();
		enrolled = new ArrayList<Student>();
		cancelled = false;
		finished = false;
	}

	Subject getSubject(){
		return subject;
	}

	int getStatus(){
		if(!daysUntilStarts.equals(0)){
			return -1 * daysUntilStarts;
		}
		return daysToRun;
	}


	void aDayPasses(){
		if(!daysUntilStarts.equals(0)){
			daysUntilStarts--;
			if((!hasInstructor() || getSize() == 0) && daysUntilStarts.equals(0)){
				if(hasInstructor())
					instructor.unassignCourse();
				for(Student student : enrolled){
					student.dropCourse();
				}
				instructor = null;
				enrolled = new ArrayList<Student>();
				cancelled = true;
			}
			return;
		}
		if(!daysToRun.equals(0)){
			daysToRun--;
		}
		if(daysToRun.equals(0) && !finished){
			for(Student student : enrolled){
				student.graduate(subject);

			}
			instructor.unassignCourse();
			instructor = null;
			finished = true;
		}
	}

	boolean enrolStudent(Student student){
		if(daysUntilStarts.equals(0) || enrolled.size() == 3){
			return false;
		}
		enrolled.add(student);
		student.enrol();
		return true;
	}

	int getSize(){
		return enrolled.size();
	}

	Student[] getStudents(){
		return enrolled.toArray(new Student[enrolled.size()]);
	}


	boolean setInstructor(Instructor instructor){
		if(instructor.canTeach(subject)){
			this.instructor = instructor;
			instructor.assignCourse(this);
			return true;
		}
		return false;
	}

	boolean hasInstructor(){
		return instructor == null ? false : true;
	}

	boolean isCancelled(){
		return cancelled;
	}
}

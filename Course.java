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
		subject.assign();
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
				subject.unassign();
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
			subject.unassign();
			finished = true;
		}
	}

	boolean enrolStudent(Student student){
		if(daysUntilStarts.equals(0) || enrolled.size() == 3 || student.getCertificates().contains(subject.getID())){
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

	boolean toBeRemoved(){
		if(isCancelled() || getStatus() == 0){
			return true;
		}
		return false;
	}

	public String toString(){
		String temp = new String();

		if(cancelled){
			temp+="WARNING COURSE SHOULD BE CANCELLED!\n";
		}

		temp += " Course subject ID: " + subject.getID();
		temp += "\n " + (getStatus() < 0 ? "Due to start" : "Finishes") + " in: " + Math.abs(getStatus());
		temp += "\n " + (instructor == null ? "Instructor not assigned" : "Instructor: " + instructor.getName());
		temp += "\n Amount of students enrolled: " + enrolled.size() + "\n";
		return temp;
	}
}

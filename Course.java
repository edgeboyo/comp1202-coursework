import java.util.ArrayList;

public class Course {
	
	Subject subject;
	Integer daysUntilStarts;
	Integer daysToRun;

	ArrayList<Student> enrolled;

	Course(Subject subject, int toStart){
		this.subject = subject;
		this.daysUntilStarts = toStart;
		daysToRun = subject.getDuration();
		enrolled = new ArrayList<Student>();
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
		}
		else if(!daysToRun.equals(0)){
			daysToRun--;
		}
		if(daysToRun.equals(0)){
			for(Student student : enrolled){
				if(!student.hasCertificate(subject))
					student.graduate(subject);
			}
		}
	}

	boolean enrolStudent(Student student){
		if(daysUntilStarts.equals(0) || enrolled.size() == 3){
			return false;
		}
		enrolled.add(student);
		return true;
	}

	int getSize(){
		return enrolled.size();
	}

	Student[] getStudents(){
		return enrolled.toArray(new Student[enrolled.size()]);
	}
}

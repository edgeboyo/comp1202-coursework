
public class Course {
	
	Subject subject;
	Integer daysUntilStarts;
	Integer daysToRun;

	Course(Subject subject, int toStart){
		this.subject = subject;
		this.daysUntilStarts = toStart;
		daysToRun = subject.getDuration();
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
	}

}

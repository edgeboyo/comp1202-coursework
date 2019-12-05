
public class Subject {
	
	Integer id;
	Integer specialism;
	Integer duration;
	String description;

	Integer getID(){
		return id;
	}

	Integer getSpecialism(){
		return specialism;
	}

	Integer getDuration(){
		return duration;
	}
	
	String getDescription(){
		return description;
	}

	void setDescription(String description){
		this.description = description;
	}
}

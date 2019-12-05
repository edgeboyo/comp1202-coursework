
public class Subject {
	
	Integer id;
	Integer specialism;
	Integer duration;
	String description;

	Subject(Integer id, Integer specialism, Integer duration){
		this.id = id;
		this.specialism = specialism;
		this.duration = duration;
		this.description = new String("ERR: TO SET");
	}

	Subject(Integer id, Integer specialism, Integer duration, String desc){
		this(id, specialism, duration);
		this.setDescription(desc);
	}

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

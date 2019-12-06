
public class Subject {
	
	Integer id;
	Integer specialism;
	Integer duration;
	String description;

	boolean assigned;

	Subject(){
		assigned = false;
	}

	Subject(Integer id, Integer specialism, Integer duration){
		this();
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

	void assign(){
		assigned = true;
	}

	void unassign(){
		assigned = false;
	}

	boolean checkAssign(){
		return assigned;
	}

	public String toString(){
		String temp = new String();

		temp += " Description: " + description;
		temp += "\n ID: " + id;
		temp += "\n Specialism req: " + specialism;
		temp += "\n Duration: " + duration;
		temp += "\n Currently assigned: " + (assigned ? "True" : "False") + "\n";

		return temp;
	}
}

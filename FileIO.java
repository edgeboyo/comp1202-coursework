import java.io.*;
import java.util.ArrayList;

/**
 * File Input/Output used
 * Reading config, saves and writing saves is implemented here
 */
public class FileIO {
	String[] params = new String[2];

	/**
	 * Header used to recognise 
	 */
	final String header = "//HEADER OF SAVE FILE !!!DO NOT CHANGE THIS!!!\\\\";

	FileIO(String input, String output){
		params[0] = input;
		params[1] = output;
	}

	/**
	 * This function load a school from a saved file
	 * Throws IOException when file is not up to standards
	 */
	School readSave() throws IOException{
		School school = null;

		BufferedReader bufRead = new BufferedReader(new FileReader(params[0]));

		String next = bufRead.readLine();
		next = bufRead.readLine();

		ArrayList<ArrayList<Integer> > toAdd = new ArrayList<ArrayList<Integer> >();
		while(next != null){
			String[] parse = next.split(":");

			if(parse == null){
				break;
			}
			if(parse[0].equals("school")){
				school = new School(parse[1]);
			}
			else if(parse[0].equals("subject")){
				String[] further = parse[1].split(",");
				Integer id = Integer.parseInt(further[1]);
				Integer specialism = Integer.parseInt(further[2]);
				Integer duration = Integer.parseInt(further[3]);
				boolean assigned = (Integer.parseInt(further[4]) == 1 ? true : false);
				Subject sub = new Subject(id, specialism, duration, further[0]);
				if(assigned)
					sub.assign();
				school.add(sub);

				
			}
			else if(parse[0].equals("course")){
 				//System.out.println(next + " : " + school.getSubjects().get(0).toSave());
				String[] further = parse[1].split(",");
				Course course = null;
				Subject sub = school.getSubjects().get(Integer.parseInt(further[0]));
				course = new Course(sub);
				school.add(course);
				course.timeSet(Integer.parseInt(further[1]), Integer.parseInt(further[2]));
				toAdd.add(new ArrayList<Integer>());
				if(parse.length==2){
					next = bufRead.readLine();
					continue;
				}

				further = parse[2].split(",");

				if(further == null){
					toAdd.get(toAdd.size()-1).add(Integer.parseInt(parse[2]));
				}
				else{
					for(int i=0; i<further.length; i++){
						toAdd.get(toAdd.size()-1).add(Integer.parseInt(further[i]));
					}
				}

			}
			else{
				//System.out.println("PROBLEM IS: " + next);
 
				String[] further = parse[1].split(",");
				String name = further[0];
				char gender = further[1].charAt(0);
				Integer age = Integer.parseInt(further[2]);
				Person person = new Person(name, gender, age);
				Instructor inst = null;
				if(parse[0].equals("student")){
					Student stud = new Student(person);
					school.add(stud);
					boolean enrolled = (Integer.parseInt(parse[2]) == 1 ? true : false);
					if(enrolled){
						stud.enrol();
					}
					if(further.length == 3){
						next = bufRead.readLine();
						continue;
					}
					further = parse[3].split(",");
					
					for(int i = 0; i < further.length; i++){
						stud.getCertificates().add(Integer.parseInt(further[i]));
					}
					next = bufRead.readLine();
					continue;
				}
				else if(parse[0].equals("Teacher")){
					inst = new Teacher(person);
				}
				else if(parse[0].equals("Demonstrator")){
					inst = new Demonstrator(person);				
				}
				else if(parse[0].equals("GUITrainer")){
					inst = new GUITrainer(person);
				}
				else if(parse[0].equals("OOTrainer")){
					inst = new OOTrainer(person);
				}
				if(Integer.parseInt(parse[2]) != -1)
					school.getCourses().get(Integer.parseInt(parse[2])).setInstructor(inst);

			}
			next = bufRead.readLine();
		}
		int i = 0;
		for(ArrayList<Integer> list : toAdd){
			for(Integer id : list){
				school.getCourses().get(i).enrolStudent(school.getStudents().get(id));
			}
			i++;
		}


		return school;
	}

	/**
	 * This function load a school from a config file
	 * Throws IOException when file is not up to standards
	 */
	School readConfig() throws IOException{
		School school = null;

		BufferedReader bufRead = new BufferedReader(new FileReader(params[0]));

		String next = bufRead.readLine();
		while(next != null){
			String[] parse = next.split(":");

			if(parse[0].equals("school")){
				school = new School(parse[1]);
			}
			else if(parse[0].equals("subject")){
				String[] further = parse[1].split(",");
				Integer id = Integer.parseInt(further[1]);
				Integer specialism = Integer.parseInt(further[2]);
				Integer duration = Integer.parseInt(further[3]);

				school.add(new Subject(id, specialism, duration, further[0]));
			}
			else{
				//System.out.println("PROBLEM IS: " + next); 
				String[] further = parse[1].split(",");
				String name = further[0];
				char gender = further[1].charAt(0);
				Integer age = Integer.parseInt(further[2]);
				Person person = new Person(name, gender, age);
				if(parse[0].equals("student")){
					school.add(new Student(person));
				}
				else if(parse[0].equals("Teacher")){
					school.add(new Teacher(person));
				}
				else if(parse[0].equals("Demonstrator")){
					school.add(new Demonstrator(person));
				}
				else if(parse[0].equals("GUITrainer")){
					school.add(new GUITrainer(person));
				}
				else if(parse[0].equals("OOTrainer")){
					school.add(new OOTrainer(person));
				}
			}
			next = bufRead.readLine();
		}

		return school;
	}

	/**
	 * Recognises if file is save or config from the header
	 * Throws IOException when file is not up to standards
	 */
	School recon() throws IOException{
		BufferedReader bufRead = new BufferedReader(new FileReader(params[0]));

		String next = bufRead.readLine();

		if(next == null){
			throw new IOException("File is empty!");
		}

		if(next.equals(header)){
			return readSave();
		}
		return readConfig();
	}

	/**
	 * Function that creates a save based on a school Class
	 */
	void buildSave(School school) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(params[1]));
		writer.write(header + "\n");

		writer.write("school:" + school.getName() + "\n");

		for(Subject subject : school.getSubjects()){
			writer.write(subject.toSave());
		}

		for(Course course : school.getCourses()){
			writer.write(course.toSave(school));
		}

		for(Student student : school.getStudents()){
			writer.write(student.toSave());
		}

		for(Instructor instructor : school.getInstructors()) {
			writer.write(instructor.toSave(school));
		}
		writer.close();
	}
}
import java.io.*;

public class FileIO {
	String[] params = new String[2];

	FileIO(String input, String output){
		params[0] = input;
		params[1] = output;
	}

	School readSave(){
		School school = null;

		return school;
	}

	School readConfig() throws IOException{
		School school = null;

		BufferedReader bufRead = null;
		
		bufRead = new BufferedReader(new FileReader(params[0]));

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

	School recon() throws IOException{

		return new School();
	}

	void buildSave(){

	}
}
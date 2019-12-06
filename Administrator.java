import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;

public class Administrator {
	static School school;
	static int choice;
	static Random random;
	static String criticalInfo;
	static String daysLog;
	static FileIO fileInOut;

	static void reRoll(){
		choice = random.nextInt(100);
	}

	static void run(){
		DesignEngine design = new DesignEngine();
		random = new Random();
		criticalInfo = new String();

		reRoll();

		if (choice < 25){
			criticalInfo += "Two students joined the school\n";
			school.add(new Student(design.createPerson(18, 29)));
			school.add(new Student(design.createPerson(18, 29)));

		}
		else if (choice < 75){
			criticalInfo += "One student joined the school\n";
			school.add(new Student(design.createPerson(18, 29)));
		}

		reRoll();

		if (choice < 20){
			criticalInfo += "Teacher joined a school\n";
			school.add(new Teacher(design.createPerson(28, 60)));
		}
		else if (choice < 30){
			criticalInfo += "Demonstrator joined a school\n";
			school.add(new Demonstrator(design.createPerson(23, 34)));
		}
		else if (choice < 35){
			criticalInfo += "GUITrainer joined a school\n";
			school.add(new GUITrainer(design.createPerson(28, 45)));
		}
		else if(choice < 40){
			criticalInfo += "OOTrainer joined a school\n";
			school.add(new OOTrainer(design.createPerson(32, 50)));
		}

		school.aDayInSchool();

		ArrayList<Instructor> toRemove = new ArrayList<Instructor>();

		for(Instructor instructor : school.getInstructors()){
			reRoll();
			if(instructor.getAssignedCourse() == null && choice < 20){
				criticalInfo += "Instructor " + instructor.getName() + " left their job today\n";
				toRemove.add(instructor);
			}
		}

		for(Instructor exEmployee : toRemove){
			school.getInstructors().remove(exEmployee);
		}

		ArrayList<Student> toDropOut = new ArrayList<Student>();

		for(Student student : school.getStudents()){
			if(student.completedAll(school)){
				criticalInfo += student.getName() + " completed all courses and left\n";
				toDropOut.add(student);

				continue;
			}

			reRoll();
			if(student.isEnrolled() && choice < 5){
				criticalInfo += student.getName() + " dropped out\n";
				toDropOut.add(student);
			}
		}

		for(Student exStudent : toDropOut){
			school.getStudents().remove(exStudent);
		}

		daysLog = school.toString();
	}

	static void run(int days){
		for(int i=0; i<days || (days == -1 ? true : false); i++){
			try {
				run();
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				System.out.println("Unexpected error: \n" + e);
				System.exit(-1);
			}

			System.out.println(criticalInfo);

		}

		System.out.println(school);
	}

	public static void main(String[] args){
		if(args.length == 0){
			school = new School();
			run(-1);
		}

		fileInOut = new FileIO(args[0], "sav.txt");
		try{
			school = fileInOut.readConfig();
		} catch (IOException e) {
			System.out.println("Something was wrong with the file.\nMake sure the path is correct!");
			System.exit(-2);
		}

		if(args.length == 1){
			run(-1);
		}


		Integer days = 0;
		try{
			days =Integer.parseInt(args[1]);
		}
		catch (Exception e){
			System.out.println("Not a valid numer");
			System.exit(1);
		}

		run(days);
	}
}
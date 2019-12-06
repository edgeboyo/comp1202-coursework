import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;

public class Administrator {
	static School school;
	
	static String criticalInfo;
	static String daysLog;

	static Random random;
	static int choice;
	static FileIO fileInOut;

	static Integer elapsed;
	static Integer studentsJoined;
	static Integer graduates;
	static Integer dropOuts;
	static Integer profsJoined;
	static Integer profsLeft;

	static void reRoll(){
		choice = random.nextInt(100);
	}

	static void run(){
		DesignEngine design = new DesignEngine();
		criticalInfo = new String();

		elapsed++;

		reRoll();

		if (choice < 33){
			studentsJoined+=2;
			criticalInfo += "Two students joined the school\n";
			school.add(new Student(design.createPerson(18, 29)));
			school.add(new Student(design.createPerson(18, 29)));

		}
		else if (choice < 33){
			studentsJoined++;
			criticalInfo += "One student joined the school\n";
			school.add(new Student(design.createPerson(18, 29)));
		}

		reRoll();
		if (choice < 20){
			profsJoined++;
			criticalInfo += "Teacher joined a school\n";
			school.add(new Teacher(design.createPerson(28, 60)));
		}

		reRoll();
		if (choice < 10){
			profsJoined++;
			criticalInfo += "Demonstrator joined a school\n";
			school.add(new Demonstrator(design.createPerson(23, 34)));
		}

		reRoll();
		if (choice < 5){
			profsJoined++;
			criticalInfo += "GUITrainer joined a school\n";
			school.add(new GUITrainer(design.createPerson(28, 45)));
		}

		reRoll();
		if(choice < 5){
			profsJoined++;
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
				profsLeft++;
			}
		}

		for(Instructor exEmployee : toRemove){
			school.remove(exEmployee);
		}

		ArrayList<Student> toDropOut = new ArrayList<Student>();

		for(Student student : school.getStudents()){
			if(student.completedAll(school)){
				criticalInfo += student.getName() + " completed all courses and left\n";
				toDropOut.add(student);
				graduates++;
				continue;
			}

			reRoll();
			if(student.isEnrolled() && choice < 5){
				criticalInfo += student.getName() + " dropped out\n";
				dropOuts++;
				toDropOut.add(student);
			}
		}

		for(Student exStudent : toDropOut){
			school.remove(exStudent);
		}

		daysLog = school.toString();
	}

	static void run(int days){
		for(int i=0; i<days || (days == -1 ? true : false); i++){
			try {
				run();
				Thread.sleep(0);
			}
			catch (InterruptedException e)
			{
				System.out.println("Unexpected error: \n" + e);
				System.exit(-1);
			}

			System.out.println(daysLog);

			if( i % 10 == 0){
				try{
					fileInOut.buildSave(school);
				}
				catch(IOException e){
					System.out.println("Save file not generated!");
				}
			}
		}

		System.out.println(school);
		try{
			fileInOut.buildSave(school);
		}
		catch(IOException e){
			System.out.println("Save file not generated!");
		}
	}

	static void report(){
		System.out.println("*****************************************");
		System.out.println("Days elapsed: " + elapsed);
		System.out.println("\nStudents joined: " + studentsJoined);
		System.out.println("Students graduated: " + graduates);
		System.out.println("Students dropped out: " + dropOuts);
		System.out.println("\nInstructors joined: " + profsJoined);
		System.out.println("Instructors left: " + profsLeft);
		System.out.println("*****************************************");
	}

	public static void main(String[] args){
		elapsed = 0;

		studentsJoined = 0;
		graduates = 0;
		dropOuts = 0;

		profsJoined = 0;
		profsLeft = 0;

		random = new Random();

		if(args.length == 0){
			fileInOut = new FileIO("", "sav.txt");
			school = new School();
			run(-1);
		}

		fileInOut = new FileIO(args[0], "sav.txt");
		try{
			school = fileInOut.recon();
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

		report();
	}
}
import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;

/** 
 * Administrator class that controls the school
 * It creates it based on argumnets and then manipulates
 * according to specification
 */
public class Administrator {
	
	//School class containing most of the created classes
	static School school;

	//log used for debugging
	static boolean criticalDisplay;
	static String criticalInfo;

	//contains last day's status report
	static String daysLog;

	//utilities used to make the program run smoother
	static Random random;
	static int choice;
	static FileIO fileInOut;

	//variables used in the final and progress reports
	static Integer elapsed;
	static Integer studentsJoined;
	static Integer graduates;
	static Integer dropOuts;
	static Integer profsJoined;
	static Integer profsLeft;

	//variables used to control the output. Can be changed with flags
	static boolean noInputFile;
	static String saveName;
	static String configName;
	static Integer days;
	static Integer waitTime;

	/**
	 * Rolls a rumber from 0 to 99 for luck checks
	 */
	static void reRoll(){
		choice = random.nextInt(100);
	}

	/**
	 * Function that runs the school and controls modification
	 * Since it's one of the most complicated parts I'll go through it block by block
	 */
	static void run(){

		//used to generate names
		DesignEngine design = new DesignEngine();
		criticalInfo = new String();

		elapsed++;

		/**
		 * Roll and check if one new student (33%), if two students (33%) or no students (34%)
		 * and add them to the school
		 */
		reRoll();
		if (choice < 33){
			studentsJoined+=2;
			criticalInfo += "Two students joined the school\n";
			school.add(new Student(design.createPerson(18, 29)));
			school.add(new Student(design.createPerson(18, 29)));

		}
		else if (choice < 66){
			studentsJoined++;
			criticalInfo += "One student joined the school\n";
			school.add(new Student(design.createPerson(18, 29)));
		}

		/**
		 * Roll for each type of Instructor and add to school.
		 * The chances are:
		 *  * Teacher 20%
		 *  * Demonstrator 10%
		 *  * GUITrainer 5%
		 *  * OOTrainer 5%
		 */
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

		//timestep once in the school
		school.aDayInSchool();

		ArrayList<Instructor> toRemove = new ArrayList<Instructor>();

		for(Instructor instructor : school.getInstructors()){

			/**
			 * For every insturctpr check if he hasn't got a course and rolls correctly (20%)
			 * If so remove instructor from the school
			 */
			reRoll();
			if(instructor.getAssignedCourse() == null && choice < 20){
				criticalInfo += "Instructor " + instructor.getName() + " left their job today\n";
				toRemove.add(instructor);
				profsLeft++;
			}
		}

		/**
		 * Remove items contained in the removal list
		 */
		for(Instructor exEmployee : toRemove){
			school.remove(exEmployee);
		}

		ArrayList<Student> toDropOut = new ArrayList<Student>();

		for(Student student : school.getStudents()){
			/**
			 * If student completed all courses mark as graduated and remove
			 */
			if(student.completedAll(school)){
				criticalInfo += student.getName() + " completed all courses and left\n";
				toDropOut.add(student);
				graduates++;
				continue;
			}

			/**
			 * If currently not enrolled and roll is within 5%
			 * Drop that student out of the school
			 */
			reRoll();
			if(!student.isEnrolled() && choice < 5){
				criticalInfo += student.getName() + " dropped out\n";
				dropOuts++;
				toDropOut.add(student);
			}
		}

		/**
		 * Remove items contained in the removal list
		 */
		for(Student exStudent : toDropOut){
			school.remove(exStudent);
		}

		//message displayed each day
		daysLog = "------DAY " + elapsed + "------" +school.toString();
	}

	/**
	 * Function that runs the simulation a certain amount of time
	 *  days : int - amount of days the simulation has to run
	 *   if set to -1 will run indefinitely
	 */
	static void run(int days){

		for(int i=0; i<days || (days == -1 ? true : false); i++){

			try {

				run();
				Thread.sleep(waitTime);
			}
			catch (InterruptedException e)
			{
				System.out.println("Unexpected error: \n" + e);
				System.exit(-1);
			}

			System.out.println(daysLog);

			/**
			 * Every tenth day back up to save file
			 */
			if( i % 10 == 0){
				try{
					fileInOut.buildSave(school);
				}
				catch(IOException e){
					System.out.println("Save file not generated!");
				}
			}
		}

		/**
		 * After concluding the simulation
		 * save to file
		 */
		try{
			fileInOut.buildSave(school);
		}
		catch(IOException e){
			System.out.println("Save file not generated!");
		}
	}

	/**
	 * End of the program report display
	 */
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

	/**
	 * Kills the program if the arguments are not known
	 */
	static void usageError(){
		System.out.println("Incorrect usage! Refer to readme.txt");
		System.exit(-3);
	}

	/**
	 * Checks if the argument if [str] a flag or an argument.
	 * Return true if flag, false if otherwise
	 */
	static boolean isFlag(String str){

		if(str.length() < 1){
			usageError();
		}

		if(str.charAt(0) == '-'){
			return true;
		}
		return false;
	}
	/**
	 * Parses through argument array [args]
	 * and modifies the program flags if necessary
	 */
	static void parseArgs(String[] args){
		for(int i=0; i<args.length; i++){
			if(isFlag(args[i])){
				String arg = args[i];

				//point to where the save file should be stored
				if(arg.equals("-s")){
					if(i+1==args.length || isFlag(args[i+1]))
						usageError();
					saveName = args[i+1];
				}

				//point to where the config file is
				if(arg.equals("-c")){
					if(i+1==args.length || isFlag(args[i+1]))
						usageError();
					configName = args[i+1];
					noInputFile = false;
				}

				//tell how many days the simulatiom should take
				if(arg.equals("-d")){
					if(i+1==args.length || isFlag(args[i+1]))
						usageError();
					try{
						days = Integer.parseInt(args[i+1]);
					}
					catch (Exception e){
						usageError();
					}
				}

				//tell how much time to wait between days
				if(arg.equals("-w")){
					if(i+1==args.length || isFlag(args[i+1]))
						usageError();
					try{
						waitTime = Integer.parseInt(args[i+1]);
					}
					catch (Exception e){
						usageError();
					}
				}
			}
		}
	}

	/**
	 * main function. Initialises and runs the program.
	 */

	public static void main(String[] args){

		//ititialising utility and execution flags
		elapsed = 0;

		studentsJoined = 0;
		graduates = 0;
		dropOuts = 0;

		profsJoined = 0;
		profsLeft = 0;

		saveName = "sav.txt";
		days = -1;

		noInputFile = true;
		criticalDisplay = false;
		waitTime = 500;
		random = new Random();

		parseArgs(args);

		//if no config file provided, run from empty simulation
		if(noInputFile){
			fileInOut = new FileIO("", saveName);
			school = new School();
			run(days);
			report();
			return;
		}

		//otherwise run with config
		fileInOut = new FileIO(configName, saveName);
		try{
			school = fileInOut.recon();
		} catch (IOException e) {
			System.out.println("Something was wrong with the file.\nMake sure the path is correct!");
			System.exit(-2);
		}
		run(days);

		report();
	}
}

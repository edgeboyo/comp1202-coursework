import java.util.Random;

public class DesignEngine {
	
	final String[] firstNamesM = {"Jim", "Angus", "Kirk"};
	final String[] firstNamesF = {"Hannah", "Mindy", "Kyra", "Sue"};
	final String[] secondNames = {"Brown", "Green", "White", "Oak"};
	final String[] courseNames = {"Graphical Interfaces", "Comms configurations", "Advanced networking", ""};
	Random random;

	DesignEngine(){
		random = new Random();
	}

	String pick(String[] arr){
		return arr[random.nextInt(arr.length)];
	}

	String getFirstNameM(){
		return pick(firstNamesM);
	}

	String getFirstNameF(){
		return pick(firstNamesF);
	}

	String nameSelect(char gender){
		if(gender == 'M'){
			return getFirstNameM();
		}
		return getFirstNameF();
	}

	String getSecondName(){
		return pick(secondNames);
	}

	String getCourseName(){
		return pick(courseNames);
	}

	Person createPerson(int ageMin, int ageMax){
		char gender = (random.nextInt(2) == 0 ? 'M' : 'F');
		String name = nameSelect(gender) + " " + getSecondName();
		ageMax++;
		int age = ageMin + random.nextInt(ageMax - ageMin);

		return new Person(name, gender, age);
	}
}
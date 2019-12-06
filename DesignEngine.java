import java.util.Random;

/**
 * Class used to create random persons
 * Adding entries to String[] will expand the namepool
 */
public class DesignEngine {
	
	final String[] firstNamesM = {"Jim", "Angus", "Kirk"};
	final String[] firstNamesF = {"Hannah", "Mindy", "Kyra", "Sue"};
	final String[] secondNames = {"Brown", "Green", "White", "Oak"};

	
	Random random;

	/**
	 * Initialise the random seed generator
	 */
	DesignEngine(){
		random = new Random();
	}

	/**
	 * Pick a random entry from the array 
	 */
	String pick(String[] arr){
		return arr[random.nextInt(arr.length)];
	}

	/**
	 * Return a random male name
	 */
	String getFirstNameM(){
		return pick(firstNamesM);
	}

	/**
	 * Return a random female name
	 */
	String getFirstNameF(){
		return pick(firstNamesF);
	}

	/**
	 * Select a namepool appropriate for the gender
	 */
	String nameSelect(char gender){
		if(gender == 'M'){
			return getFirstNameM();
		}
		return getFirstNameF();
	}

	/**
	 * Pick a random second name
	 */
	String getSecondName(){
		return pick(secondNames);
	}

	/**
	 * Creates a randomised person within a certain age group
	 */
	Person createPerson(int ageMin, int ageMax){
		char gender = (random.nextInt(2) == 0 ? 'M' : 'F');
		String name = nameSelect(gender) + " " + getSecondName();
		ageMax++;
		int age = ageMin + random.nextInt(ageMax - ageMin);

		return new Person(name, gender, age);
	}
}
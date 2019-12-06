
public class Person {

	String name;
	char gender;
	Integer age;


	Person(String name, char gender, Integer age){
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	String getName(){
		return name;
	}

	char getGender(){
		return gender;
	}

	void setAge(int age){
		this.age = age;
	}

	int getAge(){
		return age;
	}

	public String toString(){
		String temp = new String();

		temp += " Name: " + name;
		temp += "\n Gender: " + gender;
		temp += "\n Age: " + age + "\n";

		return temp;
	}

	String toSave(){
		String temp = new String();
		temp += name + "," + gender + "," + age;
		return temp;
	}
}

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
}
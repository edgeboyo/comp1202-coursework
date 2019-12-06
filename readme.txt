# COMP1202 CW 

The coursework is complied easily using javac
 ```
 javac Administrator.java
 ```
Afterwards a class file is prepared and can be used:
```
 java Administrator
 ```
...launches the program with default settings.
To modify it there are several flags:
 * -w <time_in_ms> - modifies the wait time to a particular amount (default: 500)
 * -s <save_location> - modifies save location (default: sav.txt)
 * -c <config/save file> - modifies location of setup file
 * -d <days> - forces the program to run a certain amount of days
For the *best* viewing pelease I would suggest using
```
 java -d 50 -s saveMe.txt -c mySchool.cfg
```
Of course all can be changed according to preference

## Main program

Program has been impmeneted as outlined in the specification. All staff and Student classes
extend the Person class and interact with Subject via Course. Courses control the allocation of Staff and Subjects
School makes sure that everyone has something to do and Administrator manipulates the 
people and adds/removes them based on luck. 

There are two additional classes:
 * DesignEngine - simple randomizer created to help with generating fake names. The pool is quite small but with ages it's large enough to distinguish entries
 * FileIO - a class used to create saves and read saves and configs

The whole algorithm works very fine able to do a million days within seconds (even on a Linux Subystem).

### Extention

#### Saves and additional configuration

Every 10 days a save is initited. The whole School [NOT Administrator] is sotred as line by line entries simillar to how the Config was, except this time it retains things like:
 * Subjects being assigned
 * Whole list of enrolled students in a course
 * Associeted courses for Staff

This way all can be retained and reused at a later time. Provided that the save occured correctly (if it doesn't a suitable message will appear)

The file can be modified to imply particular things, but it needs to be done carefully.
The syntax is most important as that can though an Unknown Expection, but otherwise all afterations and additions will have an effect on the Simulation.

Both original configs and new saves work as a HEADER line is added to the top of any save file to help with recognition.
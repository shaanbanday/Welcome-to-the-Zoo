/*
Java™ Project: ICS4U
Package: zoo
Class: ZooMain
Programmer: Shaan Banday

Date Created: Thursday, May 19th, 2022.
Date Completed: Friday, May 20th, 2022.

Description: The following program/class has a main method, and creates the GUI object by making a reference to the Zoo class. Since the main method
is always ran first, and since the Zoo class does not have a main method, then there needs to be one to create the object. Otherwise, the code would
run, but not do anything, since Eclipse wouldn't know which method to run first. So this main method has to be here.
*/

package zoo; //Launch the class from this package named "zoo"

@SuppressWarnings("unused") //Suppress any warnings of unused objects

public class ZooMain //The name of the class that will reference the "Zoo" class.
{
	public static void main(String[] args) //Main method which creates the Zoo object as a GUI
	{
		Zoo zooGUI = new Zoo(); //Create the SortingDemonstration object as a new GUI
	}
}
/*
Java™ Project: ICS4U
Package: zoo
Class: Animal
Programmer: Shaan Banday

Date Created: Thursday, May 12th, 2022.
Date Completed: Saturday, May 14th, 2022.

Description: This Animal abstract class inherits from JButton, essentially just making it a special type of button. This means it has all the
methods, and functions of a button, but additional attributes and methods. This makes an Animal a unique type of button in that it has an image as
its display, it makes a noise when pressed, and it can move to a given location (which in this case is a cage). An animal moves to a cage when
it is pressed, just like for making a noise, so it only makes a noise when in the cage, and there is a boolean cageState attribute which handles
this. Since the Animal class is abstract, it means a reference to it cannot be created and instead, the 4 sub-classes which inherit from the animal
class (Gibbon, Snow Leopard, Elephant, and Kookaburra) are the only ones that can be actually referenced in some GUI or main method. As a result,
the Animal class serves as a template for the rest of the classes. It is abstract, and not an interface because while some methods are abstract and 
undefined, others like the action listener and the get methods has a body and are the same regardless of the type of animal as the child class. The
other methods though, are all abstract, meaning only the name, parameters, and return type is defined, whereas any class that inherits from an Animal
MUST define those classes. 
*/

package zoo; //Launch the class from this package named "zoo"

//Import Classes

import java.awt.event.ActionEvent; //Import the ActionEvent Class for the actual button press that occurs
import java.awt.event.ActionListener; //Import the ActionListener Class to listen to the event
import javax.swing.ImageIcon; //Import the ImageIcon class to set the image of an animal in the child classes
import javax.swing.JButton; //Import the JButton class to inherit from

public abstract class Animal extends JButton implements ActionListener //The class inherits from JButton and uses the ActionListener as an interface
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1853843957144188699L;
	
	//Declare variable attributes of the class. They are private since only this class has access to them
	private ImageIcon animalImage; //ImageIcon for the animal, which is set by the child class
	private int imageHeight, imageWidth; //Int to hold the height and width of the image which is set for the animal
	private boolean cageState; //Boolean to hold whether an animal is in the cage or not
	
	//Declare Constant Attributes of the class
	protected static final boolean INSIDE = false, OUTSIDE = true; //Boolean to show if the animal is inside a cage or not. 
	//They are protected so the child classes can have access to them
	
	public Animal(ImageIcon inputImage) //Constructor for the Animal, which takes the parameter of an ImageIcon
	//Since this class is abstract, this constructor is never called and is instead passed information by the child classes
	{
		this.animalImage = inputImage; //Set the image of the animal, which is passed by the child class constructor
		this.imageHeight = inputImage.getIconHeight(); //Set the height of the button, based on the image
		this.imageWidth = inputImage.getIconWidth(); //Set the width of the button, based on the image
		
		this.cageState = OUTSIDE; //By default, set the animal outside of the cage
		
		this.addActionListener(this); //Add an action listener to the button, so it knows when it is pressed
		this.setIcon(this.animalImage); //Set an image as the icon for the button, again determined by the child constructor
	}
	
	@Override
	public int getHeight() //Public method that overrides the inherited getHeight() method from the JButton which returns an int in pixels
	{
		return this.imageHeight; //Return the height of the image, which is the height of the button
	}
	
	@Override
	public int getWidth() //Public method that overrides the inherited getWidth() method from the JButton which returns an int in pixels
	{
		return this.imageWidth; //Return the width of the image, which is the height of the button
	}
	
	public void actionPerformed(ActionEvent e)  //Public method to respond to a button click, which is inherited from the ActionListener Interface
	{
		if (this.cageState) //If the Animal is outside of the cage
		{
			this.moveAnimal(); //Call the moveAnimal() method, to move the given child class animal to a cage
			this.cageState = INSIDE; //Since it is now in the cage, update the cage state
		}
		
		else //Otherwise, if the Animal is inside the cage
		{ 
			this.makeNoise(); //Call the makeNoise() method, to get the given child class to play their noise
		}
	}
	
	protected abstract void moveAnimal(); 
	/*Protected abstract method that sub-classes can see, which defines the return type and parameters for the moveAnimal() method. This method has 
	  no body because it is abstract, but because of this, the sub-classes MUST define these methods*/
	
	
	protected abstract void makeNoise();
	/*Protected abstract method that sub-classes can see, which defines the return type and parameters for the makeNoise() method. This method has no
	  body because it is abstract, but because of this, the sub-classes MUST define these methods*/
}
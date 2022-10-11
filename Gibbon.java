/*
Java™ Project: ICS4U
Package: zoo
Class: Gibbon
Programmer: Shaan Banday

Date Created: Saturday, May 14th, 2022.
Date Completed: Sunday, May 15th, 2022.

Description: This Gibbon class inherits from Animal, which itself inherits from JButton, making Gibbon just an even more specific type of button. As
a result, it has access to all the inherited methods from Animal and JButton essentially just making it a special type of button. Unlike the Animal
class, a Gibbon can be created by a GUI or main method, and it's constructor simply passes the image of a Gibbon to the animal class. Gibbon also
defines the abstract methods moveAnimal() and makeNoise() because a Gibbon has to be moved to the first cage and makes a specific howling noise.
*/

package zoo; //Launch the class from this package named "zoo"

//Import Audio elements
import javax.sound.sampled.AudioInputStream; //Import the AudioInputStream class which calls the right audio file
import javax.sound.sampled.AudioSystem; //Import the AudioSystem class which connects the AudioInputStream to the Clip
import javax.sound.sampled.Clip; //Import the Clip class to play and stop the music
import java.io.File; //Import the File class to actually open the Audio files

//Import Exceptions that must be handled
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.ImageIcon; //Import the ImageIcon class to set the image

public class Gibbon extends Animal //The class inherits from Animal
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4734361171873297094L;
	
	//Declare static attributes of the class. They are private since only this class has access to them
	private static ImageIcon gibbonImage = new ImageIcon("gibbon.jpg"); //ImageIcon for the Gibbon, which is a jpeg image
	private static AudioInputStream gibbonStream; //Audio Stream for the Gibbon
	private static Clip gibbonHowl; //Sound Clip for the Gibbon (comes from a WAV file)
	
	public Gibbon() //Constructor for the Gibbon, which is called to create a Gibbon Object in the Zoo class
	{
		super(gibbonImage); //Call the constructor for the parent (super) class, by passing the image for the Gibbon
	}	
	
	@Override
	public void moveAnimal() //Public method that overrides and defines the inherited moveAnimal() method from the Animal class
	{
		this.setBounds(Zoo.getCageLocation(this.getClass().getName())); //Move the Gibbon to it's cage, by getting the location from the Zoo class
		//This is done by calling the static getCageLocation method, by passing the name of this class
	}
	
	@Override
	public void makeNoise() //Public method that overrides and defines the inherited makeNoise() method from the Animal class
	{
		//Try and Catch statement
		try //Try to start the Howl
		{
			gibbonStream = AudioSystem.getAudioInputStream(new File("gibbonHowl.wav").getAbsoluteFile()); //Get the Audio file
			gibbonHowl = AudioSystem.getClip(); //Initialise the audio clip for the Gibbon Howl
			gibbonHowl.open(gibbonStream); //Open the audio file in the clip for the Gibbon Howl
			gibbonHowl.start(); //Start playing the clip for the Gibbon Howl
		}
		
		catch (IOException | UnsupportedAudioFileException | LineUnavailableException g) {} //Catch the 3 imported exceptions and do nothing
	}
}
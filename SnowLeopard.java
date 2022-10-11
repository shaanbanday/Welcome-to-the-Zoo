/*
Java™ Project: ICS4U
Package: zoo
Class: SnowLeopard
Programmer: Shaan Banday

Date Created: Saturday, May 14th, 2022.
Date Completed: Sunday, May 15th, 2022.

Description: This SnowLeopard class inherits from Animal, which itself inherits from JButton, making SnowLeopard just an even more specific type of
button. As a result, it has access to all the inherited methods from Animal and JButton essentially just making it a special type of button. Unlike 
the Animal class, a SnowLeopard can be created by a GUI or main method, and it's constructor simply passes the image of a SnowLeopard to the Animal 
class. SnowLeopard also defines the abstract methods moveAnimal() and makeNoise() because a SnowLeopard has to be moved to the second cage and makes
a specific roaring noise.
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

public class SnowLeopard extends Animal //The class inherits from Animal
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5366106023816606734L;
	
	//Declare static attributes of the class. They are private since only this class has access to them
	private static ImageIcon leopardImage = new ImageIcon("snowLeopard.jpg"); //ImageIcon for the SnowLeopard, which is a jpeg image
	private static AudioInputStream leopardStream; //Audio Stream for the SnowLeopard
	private static Clip leopardRoar; //Sound Clip for the SnowLeopard (comes from a WAV file)
	
	public SnowLeopard() //Constructor for the SnowLeopard, which is called to create a SNowLeopard Object in the Zoo class
	{
		super(leopardImage); //Call the constructor for the parent (super) class, by passing the image for the SnowLeopard
	}
	
	@Override
	public void moveAnimal() //Public method that overrides and defines the inherited moveAnimal() method from the Animal class
	{
		this.setBounds(Zoo.getCageLocation(this.getClass().getName())); //Move the SnowLeopard to it's cage, by getting location from the Zoo class
		//This is done by calling the static getCageLocation method, by passing the name of this class
	}
	
	@Override
	public void makeNoise() //Public method that overrides and defines the inherited makeNoise() method from the Animal class
	{
		//Try and Catch statement
		try //Try to start the Roar
		{
			leopardStream = AudioSystem.getAudioInputStream(new File("leopardRoar.wav").getAbsoluteFile()); //Get the Audio file
			leopardRoar = AudioSystem.getClip();  //Initialise the audio clip for the SnowLeopard Roar
			leopardRoar.open(leopardStream); //Open the audio file in the clip for the SnowLeopard Roar
			leopardRoar.start(); //Start playing the clip for the SnowLeopard Roar
		}
		
		catch (IOException | UnsupportedAudioFileException | LineUnavailableException l) {} //Catch the 3 imported exceptions and do nothing
	}
}
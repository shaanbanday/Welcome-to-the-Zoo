/*
Java™ Project: ICS4U
Package: zoo
Class: Zoo
Programmer: Shaan Banday

Date Created: Monday, May 15th, 2022.
Date Completed: Wednesday, May 17th, 2022.

Description: This program serves as a virtual zoo, to test the functionalities of the Animal Class, and its subclasses, in a GUI format. This class
simply displays the 4 animal JButtons into a JPanel, with 4 corresponding cages. When a animal is pressed, the object classes take care of moving
themselves, which means this Zoo class has almost no code in its ActionListener. Aside from the constructors, the Zoo class also has a method to get
the location of a cage and pass it to an animal.
*/

package zoo; //Launch the class from this package named "zoo"

//Import graphical elements
import java.awt.*; //Import the package with all the graphical objects
import javax.swing.*; //Import the package with more graphical objects
import java.awt.event.*; //Import the Action Listener Class
import javax.swing.border.EtchedBorder; //Import the border class
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Zoo extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8598351548108317884L;
	
	//Declare static graphical attributes of the class. They are private since only this class has access to them
	//Moreover, they are static because every instance of this class will have the same values for these variables
	private static JPanel zooPanel; //Panel to hold all the graphical elements
	private static Animal gibbon, snowLeopard, elephant, kookaburra; //4 animals that need to be created
	private static JTextPane [] cages; //Array of JTextPanes, which represent the cages
	private static StyledDocument zooTextStyle; //The style for the JTextPanes to centre the text always, no matter the size
	private static SimpleAttributeSet centerAlignment; //The aforementioned centre alignment
	private static Rectangle [] animalLocations,cageLocations; //Array of Rectangles to hold the locations of the animals and cages
	private static Font cageFont, titleFont; //Fonts for the cages and the title
	private static JLabel zooTitle; //JButton to display the title
	
	//Declare constant attributes of the class They are private since only this class has access to them
	private static final short F_WIDTH = 1150, F_HEIGHT = 700; //Width and Height of the JFrame
	private static final int GAP = 15; //Gap between the cages and animals
	private static final int CAGE_Y = 100, ANIMAL_Y = 380; //The Y-coordinate of the cages, and the animal buttons
	private static final int DIMMENSION = 264; //Dimension (height and width) for the buttons
	
	public Zoo() //Constructor for the Zoo. Called to create a Zoo object in the ZooMain class
	{
		super("Welcome to the Port Elgin Zoo!"); //Name of JFrame/window
		
		//Call extension methods for the constructor
		constructGUI(); //Construct the GUI
		constructFonts(); //Construct the fonts
		constructTitle(); //Construct the Title
		constructAnimalRectangles(); //Construct Rectangles for the animals
		constructCageRectangles(); //Construct Rectangles for the Cages
		constructCages(); //Construct the actual cages, themselves
		constructGibbon(); //Construct the Gibbon
		constructLeopard(); //Construct the SnowLeopard
		constructElephant(); //Construct the Elephant
		constructKookaburra(); //Construct the Kookaburra
		
		this.repaint(); //Repaint the screen
	}
	
	private void constructGUI() //Private non-static constructor method which is an extension of the original constructor to set the GUI
	//Since it set's all the GUI methods, and refers to "this" as the JFrame it cannot be referenced statically
	{
		//GUI Initialisations
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate the program, and close the window if the close button is hit
        this.setSize(F_WIDTH, F_HEIGHT); //Set the size of the window (JFrame) in pixels to the constants initialised outside the constructor
        this.setResizable(false); //Window is unable to be resized.
        this.setVisible(true); //Everything in the JFrame is visible, unless otherwise specified  
		this.setFocusable(true); //Make it the focal point if it is used
		
		//Initialise JPanel
        zooPanel = (JPanel)this.getContentPane(); //Create a JPanel to organise contents in the JFrame/Window  
        zooPanel.setLayout(null); //Assign no layout (null) to the JPanel
        
        zooPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK)); //Set a matte black border for the JPanel
        zooPanel.setBackground(new Color (1, 50, 32)); //Set the colour of the background to a lush green
	}
	
	private static void constructFonts() //Private static constructor method which is an extension of the original constructor to create the Fonts
	//This constructor method (and every subsequent one) is static since it references static objects that do not change for every instance of Zoo
	{
		cageFont = new Font("Copperplate Gothic Bold", Font.BOLD, 30); //Font for the cages (to show cage 1, cage 2, etc.)
		titleFont = new Font("Copperplate Gothic Bold", Font.BOLD, 70); //Font for the title
	}
	
	private static void constructTitle() //Private static constructor method which is an extension of the original constructor to create the Title
	{
		zooTitle = new JLabel("Port Elgin Zoo"); //Set string for the title label
		zooTitle.setHorizontalAlignment(SwingConstants.CENTER); //Make the title centre aligned
		zooTitle.setBounds(new Rectangle(0, 10, 1130, 90)); //Set the bounds of the title within the JFrame
		zooTitle.setForeground(Color.YELLOW); //Set the colour of the title to the default yellow
		zooTitle.setFont(titleFont); //Set the font of the title to the one created for it
		zooPanel.add(zooTitle); //Add the title to the JPanel
	}
	
	private static void constructAnimalRectangles() //Private static constructor method to create the locations for the 4 animals, as rectangles
	{
		//Declare all variables
		animalLocations = new Rectangle [4]; //Set the size of the array which holds the locations of the animals to 4 (number of animals)
		
		//Loops
		for (int p = 0; p < animalLocations.length; p++) //Start the loop at 0, and iterate through every element of the array
		{
			//Decisions
			if (p == 0) //If it the index is 0 (first element)
			{
				animalLocations[p] = new Rectangle(GAP, ANIMAL_Y, 
						DIMMENSION, DIMMENSION); //Create a new Rectangle to hold the location of the animal at the first element
			}
			
			else //Otherwise, for every other element
			{
				animalLocations[p] = new Rectangle((int) (animalLocations[p-1].getX() + GAP + animalLocations[p-1].getWidth()), 
						ANIMAL_Y, DIMMENSION, DIMMENSION); //Create a new Rectangle to hold the location of the animal at every other element p
			}
		}
	}
	
	private static void constructCageRectangles() //Private static constructor method to create the locations for the 4 cages, as rectangles
	{
		//Declare all variables
		cageLocations = new Rectangle [4]; //Set the size of the array which holds the locations of the cages to 4 (number of animals)
		
		//Loops
		for (int i = 0; i < cageLocations.length; i++) //Start the loop at 0, and iterate through every element of the array
		{
			//Decisions
			if (i == 0) //If it the index is 0 (first element)
			{
				cageLocations[i] = new Rectangle(GAP, CAGE_Y, 
						DIMMENSION, DIMMENSION); //Create a new Rectangle to hold the location of the cage at the first element
			}
			
			else //Otherwise, for every other element
			{
				cageLocations[i] = new Rectangle((int) (cageLocations[i-1].getX() + GAP + cageLocations[i-1].getWidth()), 
						CAGE_Y, DIMMENSION, DIMMENSION); //Create a new Rectangle to hold the location of the cage at every other element i
			}
		}
	}
	
	private static void constructCages() //Private static constructor method to create the actual cages themselves
	{
		//Declare all variables
		cages = new JTextPane[4]; //Set the size of the array which holds the cages themselves to 4 (number of animals)
		
		//Loops
		for (int j = 0; j < cages.length; j++) //Start the loop at 0, and iterate through every element of the array
		{
			//Set basic properties of the Cage
			cages[j] = new JTextPane(); //Declare a new text pane for the cage at element j
			cages[j].setText("Cage #" + (j + 1)); //Set the text at element j to it's index + 1
			cages[j].setBounds(cageLocations[j]); //Set the bounds for the cage within the JFrame
			cages[j].setForeground(Color.BLACK); //Set the colour of the cage's text at index j to black
			cages[j].setBackground(Color.GRAY); //Set the cage at index j's background to gray
			cages[j].setFont(cageFont); //Set the font of the cage at index j to a custom one
			cages[j].setVisible(true); //Make the cage at index j visible
			cages[j].setHighlighter(null); //Do not allow the user to highlight the text for the cage at index j
			cages[j].setEditable(false); //Do not allow the user to edit the text for the cage at index j
			
			//Set Text Style of the Cage
			zooTextStyle = cages[j].getStyledDocument(); //Declare the text style for the text pane message
			centerAlignment = new SimpleAttributeSet(); //Declare the attribute for the text pane message
			StyleConstants.setAlignment(centerAlignment, StyleConstants.ALIGN_CENTER); //Set the attribute to a centre alignment
			zooTextStyle.setParagraphAttributes(0, zooTextStyle.getLength(),
					centerAlignment, false); //Apply this attribute to the text style for the cage
			
			//Add it to the panel
			zooPanel.add(cages[j]); //Add the cage to the JPanel
		}
	}
	
	private static void constructGibbon() //Private static constructor method to create the Gibbon
	{
		gibbon = new Gibbon(); //Create a new Gibbon Object
		gibbon.setBounds(animalLocations[0]); //Set the bounds of the Gibbon within the JFrame by giving it it's designated Rectangle
		gibbon.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK)); //Add a black border to the Gibbon
		gibbon.setEnabled(true); //Enable the Gibbon
		gibbon.setVisible(true); //Make the Gibbon Visible
        gibbon.setFocusable(false); //Set the Gibbon to not be focusable with a tab pressed
        zooPanel.add(gibbon); //Add the Gibbon to the panel
	}
	
	private static void constructLeopard() //Private static constructor method to create the SnowLeopard
	{
		snowLeopard = new SnowLeopard(); //Create a new SnowLeopard Object
		snowLeopard.setBounds(animalLocations[1]); //Set the bounds of the SnowLeopard within the JFrame by giving it it's designated Rectangle
		snowLeopard.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK)); //Add a black border to the Leopard
		snowLeopard.setEnabled(true); //Enable the SnowLeopard
		snowLeopard.setVisible(true); //Make the SnowLeopard Visible
        snowLeopard.setFocusable(false); //Set the SnowLeopard to not be focusable with a tab pressed
        zooPanel.add(snowLeopard); //Add the SnowLeopard to the panel
	}
	
	private static void constructElephant() //Private static constructor method to create the Elephant
	{
		elephant = new Elephant(); //Create a new Elephant Object
		elephant.setBounds(animalLocations[2]); //Set the bounds of the Elephant within the JFrame by giving it it's designated Rectangle
		elephant.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK)); //Add a black border to the button
		elephant.setEnabled(true); //Enable the Elephant
		elephant.setVisible(true); //Make the Elephant Visible
		elephant.setFocusable(false); //Set the Elephant to not be focusable with a tab pressed
        zooPanel.add(elephant); //Add the Elephant to the panel
	}
	
	private static void constructKookaburra() //Private static constructor method to create the Kookaburra
	{
		kookaburra = new Kookaburra(); //Create a new Kookaburra Object
		kookaburra.setBounds(animalLocations[3]); //Set the bounds of the Kookaburra within the JFrame by giving it it's designated Rectangle
		kookaburra.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK)); //Add a black border to the button
		kookaburra.setEnabled(true); //Enable the Kookaburra
		kookaburra.setVisible(true); //Make the Kookaburra Visible
		kookaburra.setFocusable(false); //Set the Kookaburra to not be focusable with a tab pressed
        zooPanel.add(kookaburra); //Add the Kookaburra to the panel
	}
	
	public static Rectangle getCageLocation (String className) //Private static method which takes the parameter of a specific Animal's class name
	//This method returns the location of the corresponding cage as a rectangle, so the Animal can be moved with a button press
	{
		//Declare all objects
		Rectangle result; //Rectangle object to be returned 
		
		//Decisions
		switch (className) //Switch statement based on the class name
		{
			case "zoo.Gibbon": //If the Gibbon is calling this method
				result = cageLocations[0]; //Set the rectangle to be returned to the first cage
				cages[0].setVisible(false); //Make the first cage invisible
				break;
				
			case "zoo.SnowLeopard": //Otherwise, if the SnowLeopard is calling this method
				result = cageLocations[1]; //Set the rectangle to be returned to the second cage
				cages[1].setVisible(false); //Make the second cage invisible
				break;
				
			case "zoo.Elephant": //Otherwise, if the Elephant is calling this method
				result = cageLocations[2]; //Set the rectangle to be returned to the third cage
				cages[2].setVisible(false); //Make the third cage invisible
				break;
				
			case "zoo.Kookaburra": //Otherwise, if the Kookaburra is calling this method
				result = cageLocations[3]; //Set the rectangle to be returned to the fourth cage
				cages[3].setVisible(false); //Make the fourth cage invisible
				break;
				
			default: //Otherwise, if there is some other class calls this method
				result = new Rectangle(); //Set the result as a void Rectangle
				break;
		}
		
		return result; //Return the result
	}
	
	@Override
	public void actionPerformed(ActionEvent e) //Inherited ActionListener method, to be invoked when a button is pressed
	{	
		this.repaint(); //Repaint the screen, but do nothing else as moving and making noises is taken care by the animals themselves
	}
	
	@Override
	public void paint(Graphics g) //Inherited Paint method to paint and update the JPanel. This method is invoked numerous times when relevant
	{
		super.paint(g); //Enable panel to be painted. This is done to reduce how glitchy it is and things only showing up when hovered over
	}
}
STUDENT INFORMATION
	Name: Ben Turner-Theijsmeijer
	student ID: 1152536
	
ASSIGNMENT INFORMATION
	Assignment 2
	
COURSE
	CIS*2430 Object Oriented Programming; Fall 2021

DATE OF LAST REVISION
	November 8, 2021

PROBLEM TO BE SOLVED / PURPOSE OF PROGRAM
	The general problem is that investors need a simple and easy way to manage their investment all in one place: 
    An ePortfolio if you will. Therefore, the purpose of this program is to make an easy way to buy investments, 
    sell investments, update investment prices, calculate the theoretical gain on owned investment, and search 
    for relevant investments.

ASSUMPTIONS AND LIMITATIONS
	The assumptions and limitations on my program include the following:
		The program can only operate on stock and mutual fund investments.
		The program cannot search for investments that are not currently owned by the user.
		Users must manually update investment prices when normally this would be done automatically.
		Users cannot search for investments based on book value or quantity owned.
		All actions must be performed in the terminal.

USER GUIDE
	To build the ePortfolio program the user must run the following
	javac *.java 
		From withing the ePortfolio package.
	To run the program the user must run the following
	java bturnert_a2.ePortfolio.Portfolio
	or
	java bturnert_a2.ePortfolio.Portfolio FILENAME (if you wish to load information from a previously created file, where FILENAME is the name of the file you wish to have read)
		From outside the file bturnert_a2.

	Once in the program the user can test the functionality of the command loop operation 
    by entering the operation they would like to perform into the terminal window.
	For example when the menu is displayed the user may enter the word “buy” causing the buy method to execute.

TEST PLAN
	Testing the command loop
		Entering nothing when prompted to enter the command loop option: Causes user to be re prompted
            example:
                Choose option:
                Sorry: choice not recognized, try again
                Choose menu option: 
		Entering a jiberish or unrecognized string when prompted to enter the command loop option: Causes user to be re prompted
            example:
                Choose option: asdasdasdasdasd
                Sorry: choice not recognized, try again
                Choose menu option: 
		Entering a recognized option such as “buy”, “b” or “1”: causes program to execute appropriate method 
            example:
                Choose option: 1
                =====================================Invetestment Types=====================================
                1) stock
                2) Mutual Fund
                ============================================================================================

	Testing the buy method: choose investment type
		Entering nothing when prompted to enter the investment type: Causes user to be re prompted
		Entering a jiberish or unrecognized string when prompted to enter the investment type: Causes user to be re prompted
		Entering a recognized option such as “STOCK”, “s” or “1”: causes program to execute appropriate section of method
	Testing the buy method: enter symbol
		a string: symbol is accepted
	Testing the buy method: after symbol entered
		Checks in both array lists if the symbol is linked to an object, if it is and the type was specified correctly 
        runs the update on the found object, if found but of the other investment type prints an error message and returns 
        to the command loop. if not in either creates a new object
	Testing the buy method: creating a new object
		Prompts the user for name, quantity and price.
		Name can be anything.
		If the wrong data type is entered for quantity or price, prompts user for input again
		Adds new object to proper array list
	Testing the buy method: adding to an existing object
		Calls the object from the appropriate list.
		Updates the quantity and price.
		If the wrong data type is entered for quantity or price, prompts user for input again

	Testing the sell method: check if array lists are empty
		If array lists are empty prints an error message and returns to the command loop
	Testing the sell method: object not in array lists
		Prints error message and returns to command loop
	Testing the sell method: object found
		Calls the object from the appropriate list.
		Gets sell quantity and price.
		If the wrong data type or invalid value is entered for quantity or price, prompts user for input again
	Testing sell method: sell all of investment
		Prints the gain from the object
		Removes the object from the array list
	Testing sell method: sell part
		Updates book value

	Testing update method: check if array lists are empty
		If array lists are empty prints an error message and returns to the command loop
	Testing update method: run through all objects
		Runs through all objects prompting for new price
		If new price is invalid reprompts user
	
	Testing GetGain method: check if array lists are empty
		If array lists are empty prints an error message and returns to the command loop
	Testing getGain method: run through all objects
		Sums gain of all objects and prints it

	Testing search method: check if array lists are empty
		If array lists are empty prints an error message and returns to the command loop
    Testing search method: leaving all peramiters blank
        displays all objects current in array lists
	Testing search method: element is in the list and search paramater match
		displays the elements that match the search terms

	Testing write to file method: no file given as command line argument
		prompts user for a filename to write to and writes to it
	Testing write to file method: file given as command line argument
		writes back to the same file overwriting anyhting that was previously there
	Testing write to file method: no file given as command line argument and no investments in Portfolio
		does not make a file

POSSIBLE IMPROVEMENTS
	Decrease the length of the program's code by implementing better more concise methods. 
	Add additional functionality.
	Fix some edge cases that can cause program failure.
    Improving uppon current search method.

/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS. Saad Akhtar
*/

//I have included some comments in the following code to show what I'm doing (or think I'm doing) in each method.
//From my trials, the computer usually guesses my secret number within 11 to 13 tries.
//I haven't seen my program take more than 15 tries in the 20 trials I did.
//The least number of tries it took was 9.
//The main method is untouched.

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class GuessingGame {

	// fill in code here
	// define data members
	/*The 3 variables below are used in more than one method, so they are initialized here. The numbers variable is an integer ArrayList that is altered according to the number
	  of matching digits. The computerGuess variable is obviously the computer's random guess. The numGuesses variable is the number of guesses made by the computer.
	*/
	public ArrayList<Integer> numbers = new ArrayList<Integer>();
	public int computerGuess = 0;
	public int numGuesses = 0;

	//The GuessingGame method fills in the ArrayList and is called upon to create the gamer object of type GuessingGame.
	public GuessingGame ( ) {
		// fill in code here
		// initialization	
		int i = 0;
		
		for(i = 1000; i < 10000; i++) {
			numbers.add(i);
		}
	}

	/*The myGuessIs method first checks if the ArrayList has any elements. If not, it returns -1. Otherwise, it proceeds to randomly pick a number from the ArrayList and increments
	  the number of guesses it made before returning its guess. This method of guessing means that the computer will never pick an invalid number that it has already eliminated
	  as a possible guess.
	*/
	public int myGuessIs() {
		// fill in code here
		if (numbers.size() == 0) {
			return -1;
		} else {
			Random r = new Random();
			computerGuess = numbers.get(r.nextInt(numbers.size()));
			numGuesses += 1;
			return computerGuess;
		}
	}
	
	//The totalNumGuesses method merely return the total number of guesses that had been counted already.
	public int totalNumGuesses() {
		// fill in code here
		// this should return the total number of guesses taken
		return numGuesses;
	}
 
	/*The updateMyGuess method first converts the computer's guess into a string. It then enters a for loop that will go through all the numbers in the ArrayList. Note that the
	  loop takes removed numbers into account as well. It does so by decreasing the variable x (an index) by 1 whenever a variable is removed in order to counteract the for loop
	  automatically incrementing x. This results in x staying in the same spot and not skipping the next number. Anyways, each number in the ArrayList that the outer for loop
	  goes through is converted into a string in order to allow character-by-character comparison. This is done by 2 different loops are entered depending on how many matches
	  there are. However, they are similar in that they basically remove any numbers from the ArrayList that cannot possibly be the correct guess.
	*/
	public void updateMyGuess(int nmatches) {
		// fill in code here
		String s1 = Integer.toString(computerGuess);
		int x = 0;
		int pos = 0;
		int match = 0;

		for(x = 0; x < numbers.size(); x++) {
			String s2 = Integer.toString(numbers.get(x));
			
			if (nmatches == 0) {
				for(pos = 0; pos < 4; pos++) {
					if (s1.charAt(pos) == s2.charAt(pos)) {
						numbers.remove(x);
						x -= 1;
						break;
					}
				}		
			} else {
				match = 0;
				
				for(pos = 0; pos < 4; pos++) {
					if (s1.charAt(pos) == s2.charAt(pos)) {
						match += 1;
					}
				}
				
				if (nmatches == 1 && match != 1) {
					numbers.remove(x);
					x -= 1;
				}
				
				if (nmatches == 2 && match != 2) {
					numbers.remove(x);
					x -= 1;
				}
				
				if (nmatches == 3 && match != 3) {
					numbers.remove(x);
					x -= 1;
				}
			}
		}		
	}
	
	// fill in code here (optional)
	// feel free to add more methods as needed
	
	// you shouldn't need to change the main function
	public static void main(String[] args) {

		GuessingGame gamer = new GuessingGame( );
  
		JOptionPane.showMessageDialog(null, "Think of a number between 1000 and 9999.\n Click OK when you are ready...", "Let's play a game!", JOptionPane.INFORMATION_MESSAGE);
		int numMatches = 0;
		int myguess = 0;
		
		do {
			myguess = gamer.myGuessIs();
			if (myguess == -1) {
				JOptionPane.showMessageDialog(null, "I don't think your number exists.\n I could be wrong though...", ":(", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			String userInput = JOptionPane.showInputDialog("I guess your number is " + myguess + ". How many digits did I guess correctly?");
			// quit if the user input nothing (such as pressed ESC)
			if (userInput == null)
				System.exit(0);
			// parse user input, pop up a warning message if the input is invalid
			try {
				numMatches = Integer.parseInt(userInput.trim());
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(null, "Your input is invalid. Please enter a number between 0 and 4", "Warning", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			// the number of matches must be between 0 and 4
			if (numMatches < 0 || numMatches > 4) {
				JOptionPane.showMessageDialog(null, "Your input is invalid. Please enter a number between 0 and 4", "Warning", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			if (numMatches == 4)
				break;
			// update based on user input
			gamer.updateMyGuess(numMatches);
			
		} while (true);
		
		// the game ends when the user says all 4 digits are correct
		JOptionPane.showMessageDialog(null, "Your number is " + myguess + " and I guessed it in " + gamer.totalNumGuesses() + " turns to boot! Let's play again sometime!", "Aha, I got it!", JOptionPane.INFORMATION_MESSAGE);
	}
}
import java.util.Scanner;

public class HangmanPlayer {
	
	static Scanner myScan = new Scanner(System.in);
	
	public static Hangman buildHangman() {
		System.out.println("Set the word");
		String tempWord = myScan.next().toUpperCase();
		System.out.println("How many chances will you give your opponent?");
		int tempInt = myScan.nextInt();
		return new Hangman(tempWord, tempInt);	
	}
	
	public static boolean displayAndUpdate(Hangman g, String guessed) {
		boolean word_complete = true;
		String word = g.getGuessWord();
		String currentLetter = "";
		System.out.println("You have " + (g.getMaxGuesses() - g.getGuessCount()) + " strikes remaining.");
		for (int i = 0; i < word.length(); i++) {
			currentLetter = word.substring(i, i + 1);
			if (guessed.indexOf(currentLetter) != -1) {
				System.out.print(currentLetter + " ");
			} else {
				System.out.print("_ ");
				word_complete = false;
			}
		}
		System.out.println();
		if (word_complete) {
			g.updateGameStatus(true);		
		} else {
			g.updateGameStatus(false);
		}
		return g.getGameStatus();
	}
	
	public static void main(final String[] args) {
		Hangman game = buildHangman();
		String guessedLetters = "";
		String current = "";
		
		while(!game.getGameStatus()) { //while game is not over
			if(displayAndUpdate(game, guessedLetters)) {
				break;
			}
			System.out.println("Guess a letter or word:");
			current = myScan.next().toUpperCase();
			if (game.guess(current)) {
				guessedLetters += current;
			}
		}
	}
}
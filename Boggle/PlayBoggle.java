/*
 * Stephen Majercik
 * 25 October 2016
 * 
 * Creates a Boggle game and initiates play.
 * 
 */


public class PlayBoggle {

	public static void main(String[] args) {
		System.out.println("Welcome to Boggle!");
		Boggle boggleBoard = new Boggle();
		boggleBoard.play();
	}

}

//
//  WordSearch
//
//  Created by Eric Chown on 9/26/12.
//  Copyright (c) 2012 Eric Chown. All rights reserved.
//
//  Modified, with permission, by Stephen Majercik on 10/11/14  
//


public class TestWordSearch {

	public static void main(String[] args) {

		System.out.println("Welcome to Word Search!");
		System.out.println();

		WordSearch puzzle = new WordSearch();

		if (puzzle.isGoodPuzzle()) {
			System.out.println("Using naive approach:");
			double solutionTime = puzzle.solve(WordSearch.SolutionMethod.NAIVE);
			System.out.println("Solution time: " + solutionTime + " seconds.\n");

			System.out.println("Using binary search approach:");
			solutionTime = puzzle.solve(WordSearch.SolutionMethod.BINARY_SEARCH);
			System.out.println("Solution time: " + solutionTime + " seconds.\n");

			System.out.println("Using binary search approach with prefixes:");
			solutionTime = puzzle.solve(WordSearch.SolutionMethod.USE_PREFIXES);
			System.out.println("Solution time: " + solutionTime + " seconds.\n");
		}

	}

}

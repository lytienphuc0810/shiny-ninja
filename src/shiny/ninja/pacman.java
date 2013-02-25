package hcmut.ai.ass.pacman;

import java.util.ArrayList;

/**
 * 
 * @author Computer Science and Engineering Faculty
 * @version 1.0
 * Copyright(C) 2013. All rights reserved.
 * 
 * 
 * Notes: You can write any additional functions/ methods / classes if you want
 * BUT: everything must be in that pacman.java file only!
 * 
 */

public class pacman {

	/**
	 * Some Global Variable you may use declare here! For example variable for
	 * storing maze 
	 * TODO
	 */

	public void readInput() {
		/*
		  Complete this method to readInput "maze.txt" File, example: 
		 	%%%%%%%%%
			%* %....%
			%%%%%%% %
			%P      %
			%.%%.%%.%
			%.%%...*%
			%%%%%%%%% 
			Notes: 	%: means wall,  
					P: start position of Pacman
					*: Target
		 * TODO
		 */
	}

	public void generateOutput() {
		/**
		 * Write to file "path.txt" the output follow: 
		 * 	   	u: up, d: down, l: left, r: right, 8 lines
		 *  		[u, d, l, r] //just notes, not printed: DFS()
		 * 			[u, d, l, r] //just notes, not printed: BFS()
		 * 			[u, d, l, r] //just notes, not printed: BestFS()
		 * 			[u, d, l, r] //just notes, not printed: AStar()
		 *    		[u, d, l, r] //just notes, not printed: HillClimbing()
		 * 			[u, d, l, r] //just notes, not printed: SteepestHillClimbing()
		 * 			[u, d, l, r] //just notes, not printed: SimulatedAnnealing()
		 * 				   
		 * TODO
		 */	
		
	}
	
	/**
	 * Remember NOT to change the PROTOTYPE of the following methods
	 * 
	 */
	
	
	public ArrayList<Character> HillClimbing() {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Character> SimulatedAnnealing() {
		return null;
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Character> SteepestHillClimbing() {
		return null;
		// TODO Auto-generated method stub
		
	}	

	public ArrayList<Character> AStar() {
		return null;
		// TODO Auto-generated method stub

	}

	public ArrayList<Character> BestFS() {
		return null;
		// TODO Auto-generated method stub

	}

	public ArrayList<Character> BFS() {//find best solution
		return null;
		// TODO Auto-generated method stub

	}

	public ArrayList<Character> DFS() {//like backtracking
		return null;
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		/**
		 * Modify this method if you want
		 */
		pacman pacman = new pacman();
		pacman.readInput();

		pacman.generateOutput();
			
	}
}

package shiny.ninja;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
  public int[][] maze;
    
	public void readInput() {
    FileReader f = null;
    BufferedReader reader = null;
    try {
      f = new FileReader("maze.txt");
    } catch (FileNotFoundException ex) {
      Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
    }

    reader = new BufferedReader(f);
    int m = 0;
    int n = 0;
    
    while(true){
      try {
        String test = reader.readLine();
        if(test == null)
          break;
        if(test.length() > n) 
          n = test.length();
        System.out.println(test);
        m++;
      } catch (IOException ex) {
        Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    System.out.println(m);
    System.out.println(n);

    maze = new int[m][n];
    
    try {
      reader.close();
    } catch (IOException ex) {
      Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try {
      f.close();
    } catch (IOException ex) {
      Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try {
      f = new FileReader("maze.txt");
    } catch (FileNotFoundException ex) {
      Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
    }

    reader = new BufferedReader(f);

    for(int i = 0; i < m; i++){
      String str = null;
      try {
        str = reader.readLine();
      } catch (IOException ex) {
        Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
      }

      for(int j = 0; j < n; j++) {
        switch(str.charAt(j)) {
          case '%':
            maze[i][j] = 1;
            System.out.print(maze[i][j]);
            break;
          case ' ':
            maze[i][j] = 2;
            System.out.print(maze[i][j]);
            break;
          case 'P':
            maze[i][j] = 3;
            System.out.print(maze[i][j]);
            break;
          case '*':
            maze[i][j] = 4;
            System.out.print(maze[i][j]);
            break;
          default:
            break;
        }
      }
      System.out.println();
    }
  }

	public void generateOutput() {

		
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
//
//		pacman.generateOutput();
			
	}
}

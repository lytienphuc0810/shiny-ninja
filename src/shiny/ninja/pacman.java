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

  public char[][] maze;
  public coordinate pacman_coordinate;
  public coordinate temp_coordinate;
  public coordinate target_coordinate;
  
  public class coordinate {
    public int x = 0;
    public int y = 0;
  }
  
  public class coordinate_node{
    public coordinate current;
    public coordinate next;
  }
  
  public class path{
    public coordinate_node head;
    public coordinate_node tail;
  }
  
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
        if(test == null){
          break;
        }
        if(test.length() > n){
          n = test.length();
        }
        System.out.println(test);
        m++;
      } catch (IOException ex) {
        Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    System.out.println(m);
    System.out.println(n);

    maze = new char[m][n];
    
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

    for(int i = m; i > 0; i--){
      String str = null;
      try {
        str = reader.readLine();
      } catch (IOException ex) {
        Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
      }

      for(int j = 0; j < n; j++) {
        if(str.charAt(j) == '%' || str.charAt(j) == ' ' || str.charAt(j) == 'P' || str.charAt(j) == '*') {
          maze[i][j] = str.charAt(j);
          System.out.print(maze[i][j]);
        }
        
        if(str.charAt(j) == 'P'){
          pacman_coordinate.x = i;
          pacman_coordinate.y = j;
          temp_coordinate.x = i;
          temp_coordinate.y = j;
        }
        
        if(str.charAt(j) == '*'){
          target_coordinate.x = i;
          target_coordinate.y = j;
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
		if(temp_coordinate.x == target_coordinate.x && temp_coordinate.y == target_coordinate.y){
      return null;
    }
    else{
      
    }
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

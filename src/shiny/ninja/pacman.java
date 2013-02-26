package shiny.ninja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
  public int row;
  public int column;
  public coordinate pacman_coordinate;
  public coordinate temp_coordinate;
  public coordinate target_coordinate;
  public path path;
  
  public pacman(){
    pacman_coordinate = new coordinate();
    temp_coordinate   = new coordinate();
    target_coordinate = new coordinate();
  }
  
  public class coordinate{
    public int x = 0;
    public int y = 0;
    public coordinate next;
    public coordinate(){
      next = null;
    }
    public coordinate(int x1, int y1){
      x = x1;
      y = y1;
      next = null;
    }
  }
  
  public class path{
    public coordinate head;
    public coordinate tail;
    public path(){
      head = new coordinate(pacman_coordinate.x, pacman_coordinate.y);
      tail = head;
    }
    public void add_to_path(int x, int y){
      if(head == null){
        head = new coordinate(x, y);
        tail = head;
      }
      else{
        coordinate temp = tail;
        tail = new coordinate(x,y);
        temp.next = tail;
      }
    }
    
    public boolean contain(int x, int y){
      coordinate temp = head;
      while(temp != null){
        if(temp.x == x && temp.y == y){
          return true;
        }
        else{
          temp = temp.next;
        }
      }
      return false;
    }
    
    public boolean complete(){
      if(head == null){
        return false;
      }
      else{
        return (tail.x == target_coordinate.x && tail.y == target_coordinate.y);
      }
    }
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
          m = test.length();              // so cot, x
        }
        System.out.println(test);
        n++;                              // so hang, y
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

    for(int i = n-1; i >= 0; i--){
      String str = null;
      try {
        str = reader.readLine();
      } catch (IOException ex) {
        Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
      }

      for(int j = 0; j < m; j++) {
        if(str.charAt(j) == '%' || str.charAt(j) == ' ' || str.charAt(j) == 'P' || str.charAt(j) == '*') {
          maze[j][i] = str.charAt(j);
          System.out.print(maze[j][i]);
        }
        
        if(str.charAt(j) == 'P'){
          pacman_coordinate.x = j;
          pacman_coordinate.y = i;
          temp_coordinate.x = j;
          temp_coordinate.y = i;
          path = new path();
        }
        
        if(str.charAt(j) == '*'){
          target_coordinate.x = j;
          target_coordinate.y = i;
        }
      }
      System.out.println();
    }
    
    row = n;
    column = m;
  }

	public void generateOutput() {
    ArrayList<Character> arr = DFS();
    FileWriter f = null;
    BufferedWriter writer = null;
    try {
      f = new FileWriter("path.txt");
      writer = new BufferedWriter(f);
      writer.write(arr.toString().replace(" ", ""), 0, arr.toString().replace(" ", "").length());
      writer.newLine();
      writer.write("[]",0,2);      
      writer.newLine();
      writer.write("[]",0,2);      
      writer.newLine();
      writer.write("[]",0,2);      
      writer.newLine();
      writer.write("[]",0,2);      
      writer.newLine();
      writer.write("[]",0,2);      
      writer.newLine();
      writer.write("[]",0,2);
      writer.flush();
      writer.close();
      f.close();
    } catch (IOException ex) {
      Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    ArrayList<Character> arr = new ArrayList<>();
    subDFS(arr);
    return arr;
	}
  
  public void subDFS(ArrayList<Character> arr){
    if(!path.complete()){
      coordinate temp = new coordinate(temp_coordinate.x, temp_coordinate.y);
      if(temp_coordinate.y < row-1 && maze[temp_coordinate.x][temp_coordinate.y+1] != '%' && !path.contain(temp_coordinate.x, temp_coordinate.y+1)){
        temp_coordinate.y = temp_coordinate.y + 1;
        path.add_to_path(temp_coordinate.x, temp_coordinate.y);
        System.out.print("u,");
        arr.add('u');
        subDFS(arr);
        if(!path.complete()){
          path.add_to_path(temp.x, temp.y);
          System.out.print("d,");
          arr.add('d');
          temp_coordinate.y = temp_coordinate.y - 1;
        }
      }
      if(temp_coordinate.y > 0 && maze[temp_coordinate.x][temp_coordinate.y-1] != '%' && !path.contain(temp_coordinate.x, temp_coordinate.y-1) && !path.complete()){
        temp_coordinate.y = temp_coordinate.y - 1;
        path.add_to_path(temp_coordinate.x, temp_coordinate.y);
        System.out.print("d,");
        arr.add('d');
        subDFS(arr);
        if(!path.complete()){
          path.add_to_path(temp.x, temp.y);
          System.out.print("u,");
          arr.add('u');
          temp_coordinate.y = temp_coordinate.y + 1;
        }
      }
      if(temp_coordinate.x > 0 && maze[temp_coordinate.x-1][temp_coordinate.y] != '%' && !path.contain(temp_coordinate.x-1, temp_coordinate.y) && !path.complete()){
        temp_coordinate.x = temp_coordinate.x - 1;
        path.add_to_path(temp_coordinate.x, temp_coordinate.y);
        System.out.print("l,");
        arr.add('l');
        subDFS(arr);
        if(!path.complete()){
          path.add_to_path(temp.x, temp.y);
          System.out.print("r,");
          arr.add('r');
          temp_coordinate.x = temp_coordinate.x + 1;
        }
      }
      if(temp_coordinate.x < column-1 && maze[temp_coordinate.x+1][temp_coordinate.y] != '%' && !path.contain(temp_coordinate.x+1, temp_coordinate.y) && !path.complete()){
        temp_coordinate.x = temp_coordinate.x + 1;
        path.add_to_path(temp_coordinate.x, temp_coordinate.y);
        System.out.print("r,");
        arr.add('r');
        subDFS(arr);
        if(!path.complete()){
          path.add_to_path(temp.x, temp.y);
          System.out.print("l,");
          arr.add('l');
          temp_coordinate.x = temp_coordinate.x - 1;
        }
      }
      if(temp_coordinate.x == pacman_coordinate.x && temp_coordinate.y == pacman_coordinate.y && !path.complete()){
        arr.clear();
      }
    }
  }

	public static void main(String[] args) {
		/**
		 * Modify this method if you want
		 */
		pacman pacman = new pacman();
		pacman.readInput();
//
		pacman.generateOutput();
			
	}
}

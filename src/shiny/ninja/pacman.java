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
    public int count;
    public path(){
      head = null;
      tail = null;
      count = 0;
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
      count++;
    }    
    public void add_to_path_reverse(int x, int y){
      if(head == null){
        head = new coordinate(x, y);
        tail = head;
      }
      else{
        coordinate temp = head;
        head = new coordinate(x,y);
        head.next = temp;
      }
      count++;
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
    ArrayList<Character> arr = null;
    FileWriter f = null;
    BufferedWriter writer = null;
    try {
      f = new FileWriter("path.txt");
      writer = new BufferedWriter(f);
      arr = DFS();
      writer.write(arr.toString().replace(" ", ""), 0, arr.toString().replace(" ", "").length());
      writer.newLine();
      arr = BFS();
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
      writer.flush();
      writer.close();
      f.close();
    } catch (IOException ex) {
      Logger.getLogger(pacman.class.getName()).log(Level.SEVERE, null, ex);
    }
	}

  public boolean adjacent(coordinate slot1, coordinate slot2){
    return (Math.abs(slot1.x - slot2.x) + Math.abs(slot1.y - slot2.y) < 2 && Math.abs(slot1.x - slot2.x) + Math.abs(slot1.y - slot2.y) > 0);
  }
  
  public path gettruepath(path path) {
    path truepath = new path();
    
    if(path.tail.x == target_coordinate.x  && path.tail.y == target_coordinate.y){
      coordinate temp;
      coordinate temp2 = path.tail;
      truepath.add_to_path_reverse(path.tail.x, path.tail.y);
              
      while(true){
        temp = path.head;
        while(true){
          if(adjacent(temp, temp2)){
            break;
          }
          temp = temp.next;
        }
        truepath.add_to_path_reverse(temp.x, temp.y);
        temp2 = temp;
        if(temp2.x == path.head.x && temp2.y == path.head.y){
          break;
        }
      }
    }
    
    return truepath;
  }

  public boolean notinqueue(ArrayList<coordinate> queue, int x, int y) {
    int n = queue.size();
    for(int i = 0; i < n; i++){
      coordinate temp = queue.get(i);
      if(temp.x == x && temp.y == y){
        return false;
      }
    }
    return true;
  }
  
  
  public char awayback(char chr){
    switch(chr){
      case 'u':
        return 'd';
      case 'd':
        return 'u';
      case 'l':
        return 'r';
      case 'r':
        return 'l';
      default:
        return 'n';
    }
  }
  
  public ArrayList<Character> getdirection(path somepath){
    ArrayList<Character> arr = new ArrayList();
    coordinate temp = somepath.head;
    
    if(somepath.complete()){
      while(temp != null && temp.next != null){
        if(adjacent(temp, temp.next)){
          if(temp.y - temp.next.y == 1){
            arr.add('d');
          }
          if(temp.y - temp.next.y == -1){
            arr.add('u');
          }
          if(temp.x - temp.next.x == 1){
            arr.add('l');
          }
          if(temp.x - temp.next.x == -1){
            arr.add('r');
          }
        }
        else{
          arr.add('e');
        }
        temp = temp.next;
      }
    }
    return arr;
  }
	
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
    path = new path();
    
    ArrayList<coordinate> queue = new ArrayList();
    queue.add(new coordinate(temp_coordinate.x, temp_coordinate.y));
    
    while(true){
      coordinate temp = queue.get(0);
      queue.remove(0);

      path.add_to_path(temp.x, temp.y);
      
      if(temp.y < row-1 && maze[temp.x][temp.y+1] != '%' && !path.contain(temp.x, temp.y+1) && notinqueue(queue, temp.x, temp.y+1) && !path.complete()){
        queue.add(new coordinate(temp.x, temp.y + 1));
      }
      if(temp.y > 0 && maze[temp.x][temp.y-1] != '%' && !path.contain(temp.x, temp.y-1) && notinqueue(queue, temp.x, temp.y-1) && !path.complete()){
        queue.add(new coordinate(temp.x, temp.y - 1));
      }
      if(temp.x > 0 && maze[temp.x-1][temp.y] != '%' && !path.contain(temp.x-1, temp.y) && notinqueue(queue, temp.x-1, temp.y) && !path.complete()){
        queue.add(new coordinate(temp.x - 1, temp.y));
      } 
      if(temp.x < column-1 && maze[temp.x+1][temp.y] != '%' && !path.contain(temp.x+1, temp.y) && notinqueue(queue, temp.x+1, temp.y) && !path.complete()){
        queue.add(new coordinate(temp.x + 1, temp.y));
      }
      
      if(queue.isEmpty() || path.complete()){
        break;
      }
    }
    System.out.println("BFS the number of slots travelled: " + path.count);
    path true_path = gettruepath(path);
    
    return getdirection(true_path);
	}
  
	public ArrayList<Character> DFS() {//like backtracking
    path = new path();
    ArrayList<Character> arr = new ArrayList<>();
    subDFS();
    System.out.println("DFS the number of slots travelled: " + path.count);
    path truepath = gettruepath(path);
    return getdirection(truepath);
	}
  
  public void subDFS(){
    path.add_to_path(temp_coordinate.x, temp_coordinate.y);
    if(temp_coordinate.y < row-1 && maze[temp_coordinate.x][temp_coordinate.y+1] != '%' && !path.contain(temp_coordinate.x, temp_coordinate.y+1) && !path.complete()){
      temp_coordinate.y++;
      subDFS();
      temp_coordinate.y--; 
    }
    if(temp_coordinate.y > 0 && maze[temp_coordinate.x][temp_coordinate.y-1] != '%' && !path.contain(temp_coordinate.x, temp_coordinate.y-1) && !path.complete()){
      temp_coordinate.y--;
      subDFS();
      temp_coordinate.y++;
    }
    if(temp_coordinate.x > 0 && maze[temp_coordinate.x-1][temp_coordinate.y] != '%' && !path.contain(temp_coordinate.x-1, temp_coordinate.y) && !path.complete()){
      temp_coordinate.x--;
      subDFS();
      temp_coordinate.x++;
    }
    if(temp_coordinate.x < column-1 && maze[temp_coordinate.x+1][temp_coordinate.y] != '%' && !path.contain(temp_coordinate.x+1, temp_coordinate.y) && !path.complete()){
      temp_coordinate.x++;
      subDFS();      
      temp_coordinate.x--;
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

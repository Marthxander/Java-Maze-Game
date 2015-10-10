import java.util.*;
import java.io.PrintStream;
import java.io.*;
/**
 * Maze.java
 * Class that contains all the logic to model a Maze with Treasures, Monsters, and an Explorer.
 * 
 * @author Alex Castanares section 14 
 * @version May 1st, 2015
 */
public class Maze
{
   // named constants
   public static final int ACTIVE = 0;
   public static final int EXPLORER_WIN = 1;
   public static final int MONSTER_WIN = 2;
    
    // instance variables
   private Square[][] squares;
   private ArrayList<RandomOccupant> randOccupants;
   private Explorer explorer;
   private int rows;
   private int cols;

   /**
    * Constructor for objects of class Maze
    */

   public Maze()
   {
      ArrayList<RandomOccupant> rando = new ArrayList<RandomOccupant>();
      randOccupants = rando;
   }

   public Maze(Square[][] squares, int rows, int cols)
   {
      // CHANGE - initialize the squares, rows, and cols instance variables to
      //          what is passed in to the constructor
      this.squares = squares;
      this.rows = rows;
      this.cols = cols;
      randOccupants = new ArrayList<RandomOccupant>();	
      // CHANGE - create the empty ArrayList of RandomOccupants
		
   }
	
   // QUERIES
   public Square getSquare(int row, int col) { return squares[row][col]; }
   public int rows() {return rows;}
   public int cols() {return cols;}
   public String explorerName() {return explorer.name();}
   public Explorer getExplorer() {return explorer;}
    
   // CHANGE - Implement the following two methods.  I have them stubbed to return dummy values just so it will compile.
   //          Your getRandomOccupant should return the occupant from the ArrayList at the specified index.
   public RandomOccupant getRandomOccupant(int index) {return randOccupants.get(index);}
   public int getNumRandOccupants() {return randOccupants.size();}
	
   // COMMANDS
   // CHANGE - implement the following method
   public void addRandomOccupant(RandomOccupant ro) {randOccupants.add(ro);}
	
   public void setExplorer(Explorer e) {explorer = e;}
	
   public void explorerMove(int key)
   {
      explorer.move(key);
   }
	
   public void randMove()
   {
      // CHANGE - instruct each object in the RandomOccupant to move
      int i = 0;
      while (i < randOccupants.size())
      {
         RandomOccupant r = randOccupants.get(i);
         r.move();
         i = i + 1;
      }
   }
	
   /**
    * Returns the status of the game.
    *
    * If all treasures have been found, return EXPLORER_WIN.
    * If not, check each maze occupant, if it is a Monster and
    *    it is in the same location as the Explorer, return
    *    MONSTER_WIN.  Note that you can use == to check locations, do you know why?
    * Otherwise, return ACTIVE.
    */
   public int gameStatus()
   {
      int status = ACTIVE;
      if(foundAllTreasures() == true)
      {
         status = EXPLORER_WIN;
      }
      else
      {
         int i = 0;
         while(i < randOccupants.size())
         {
            RandomOccupant r = randOccupants.get(i);
            if(r instanceof Monster)
            {
               if((r.location() == explorer.location()))
               {
                  status = MONSTER_WIN;
               }
            }
            i = i + 1;
         }
      } 
      // CHANGE - implement
        
      return status;
   }
	
   private boolean foundAllTreasures()
   {
      boolean foundAll = true;
      int i = 0;
      while(i < randOccupants.size())
      {
         RandomOccupant e = randOccupants.get(i);
         if (e instanceof Treasure)
         {
            Treasure tr = (Treasure)randOccupants.get(i);
            boolean f = tr.found();
            if (f == false)
            {
               foundAll = false;
               return foundAll;
            }
         }
         i = i + 1;
      }
      // CHANGE - search through all the occupants to see if the Treasures have been found.  Return false if
      //        - there is a Treasure that hasn't been found.
              
      return foundAll;
   }
    
   public void lookAround(Square s)
   {
      int row = s.row();
      int col = s.col();
        
      // Clear what was previously in view
      resetInView();
        
      // Set the current square so that we are viewing it (obviously)
      s.setInView(true);
      boolean u = s.wall(s.UP);
      boolean r = s.wall(s.RIGHT);
      boolean d = s.wall(s.DOWN);
      boolean l = s.wall(s.LEFT);
      if(u == false)
      {
         Square n = squares[row - 1][col];
         n.setInView(true);
         boolean ri = n.wall(n.RIGHT);
         boolean le = n.wall(n.LEFT);
         if(ri == false)
         { 
            squares[row - 1][col + 1].setInView(true);
         }
         if(le == false)
         {
            squares[row - 1][col - 1].setInView(true);
         }
      }
      if(r == false)
      {
         Square n = squares[row][col + 1];
         n.setInView(true);
         boolean up1 = n.wall(n.UP);
         boolean dow = n.wall(n.DOWN);
         if(up1 == false)
         {
            squares[row - 1][col + 1].setInView(true);
         }
         if(dow == false)
         {
            squares[row + 1][col + 1].setInView(true);
         }
      }
      if(d == false)
      {
         Square n = squares[row + 1][col];
         n.setInView(true);
         boolean ri = n.wall(n.RIGHT);
         boolean le = n.wall(n.LEFT);
         if(ri == false)
         {
            squares[row + 1][col + 1].setInView(true);
         }
         if(le == false)
         {
            squares[row + 1][col - 1].setInView(true);
         }
      }
      if(l == false)
      {
         Square n = squares[row][col - 1];
         n.setInView(true);
         boolean up1 = n.wall(n.UP);
         boolean dow = n.wall(n.DOWN);
         if(up1 == false)
         {
            squares[row - 1][col - 1].setInView(true);
         }
         if(dow == false)
         {
            squares[row + 1][col - 1].setInView(true);
         }
     }      
      // CHANGE - Check the adjacent squares.  If there isn't a wall in the way, set their inview to true.
      //        - Check the diagonal squares.  If there isn't a wall in the way, set their inview to true.

   }
    
   private void resetInView()
   {
      for (int i = 0; i<rows; i++) {
         for (int j = 0; j<cols; j++) {
            squares[i][j].setInView(false);
         }
      }
   }

   public void writeMazeToFile(String fileName) throws IOException
   {
      PrintStream out = new PrintStream(new File(fileName));
      out.print(rows);
      out.print(",");
      out.println(cols);
      for(int i = 0; i < rows; i++)
      {
         for(int j = 0; j < cols; j++)
         {
            out.println(getSquare(i,j).toText(','));
         }
      }
      out.println(explorer.toText(','));
      for(RandomOccupant k: randOccupants)
      {
         out.println(k.toText(','));
      }
      out.close();
   }

   public void readMazeFromFile(String fileName) throws IOException, FileNotFoundException, MazeReadException
   {
      int line = 0;
      Scanner input;
      Scanner lscan;
      try{
      File f = new File(fileName);
      input = new Scanner(f);
      }
      catch(FileNotFoundException e)
      {
         throw e;
      }
      input.useDelimiter(",");
      String cl = input.nextLine();
      lscan = new Scanner(cl);
      line ++;
      lscan.useDelimiter(",");
      if(!lscan.hasNextInt())
      {
         throw new MazeReadException("Rows and columns not specified.", cl, line);
      }
      rows = lscan.nextInt();
      if(!lscan.hasNextInt())
      {
         throw new MazeReadException("Rows and columns not specified.", cl, line);
      }
      cols = lscan.nextInt();
      squares = new Square[rows][cols];
      lscan.close();
      while(input.hasNext())
      {
         line ++;
         cl = input.nextLine();
         lscan = new Scanner(cl);
         lscan.useDelimiter(",");
         if(cl == "")
         {
            throw new MazeReadException("Line format or other error.", cl, line);
         }
         else if(lscan.hasNext("Square"))
         {
            lscan.next();
            int row = lscan.nextInt();
            int col = lscan.nextInt();
            if(squares[row][col] != null)
            {
               throw new MazeReadException("Duplicate square.", cl, line);
            }
            Square tsqu = new Square(row, col);
            try{tsqu.toObject(lscan);}
            catch(Exception e)
            {
               throw new MazeReadException("Line format or other error.", cl, line);
            }
            squares[row][col] = tsqu;
            lscan.close();
          }
          else if(lscan.hasNext("Explorer"))
          {
             lscan.next();
             explorer = new Explorer(this);
             try{explorer.toObject(lscan);}
             catch(Exception e)
             {
                throw new MazeReadException("Line format or other error.", cl, line);
             }
             lscan.close();
          }
          else if(lscan.hasNext("Treasure"))
          {
             lscan.next();
             Treasure tre = new Treasure(this);
             try{tre.toObject(lscan);}
             catch(Exception e)
             {
                throw new MazeReadException("Line format or other error.", cl, line);
             }
             lscan.close();
             randOccupants.add(tre);
          }
          else if(lscan.hasNext("Monster"))
          {
             lscan.next();
             Monster mon = new Monster(this);
             try{mon.toObject(lscan);}
             catch(Exception e)
             {
                throw new MazeReadException("Line format or other error.", cl, line);
             }
             lscan.close();
             randOccupants.add(mon);
          }
          else if(cl.equals(""))
          {
             throw new MazeReadException("Line format or other error.", cl, line);
          }
          else{
          throw new MazeReadException("Unknown type.", cl, line);
          }
      }
      input.close();
   }
}


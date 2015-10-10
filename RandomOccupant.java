import java.util.*;
/**
 * RandomOccupant.java
 * Class that creates an Occupant that behaves randomly in terms of movement throughout the maze
 *
 * @author Alex Castanares section 14
 * @version May 1st, 2015
 */

import java.util.*;

public abstract class RandomOccupant extends Occupant
{
   private Random rand;
   private Maze maze;
   
   public RandomOccupant(Maze maze)
   {
      super();
      this.maze = maze;
      rand = new Random();
      int ro = rand.nextInt(maze.rows());
      int co = rand.nextInt(maze.cols());
      Square squ = maze.getSquare(ro, co);
      super.moveTo(squ);
   }

   public RandomOccupant(Maze maze, long seed)
   {
      super();
      this.maze = maze;
      rand = new Random(seed);
      int ro = rand.nextInt(maze.rows());
      int co = rand.nextInt(maze.cols());
      Square squ = maze.getSquare(ro, co);
      super.moveTo(squ);
   }
    
   public RandomOccupant(Maze maze, Square location)
   {
      super(location);
      this.maze = maze;
      rand = new Random();
   }

   public void move()
   {
      Square s = super.location();
      int row = s.row();
      int col = s.col();
      boolean j = true;
      int dir;
      while(j == true)
      {
         dir = rand.nextInt(4);
         if(s.wall(dir) != true)
         {
            if(dir == super.location().UP)
            {
               row = row - 1;
               j = false;
            }
            else if(dir == super.location().RIGHT)
            {
               col = col + 1;
               j = false;
            }
            else if(dir == super.location().DOWN)
            {
               row = row + 1;
               j = false;
            }
            else if(dir == super.location().LEFT)
            {
               col = col - 1;
               j = false;
            }      
         }
      }
      Square y = maze.getSquare(row, col);
      super.moveTo(y);
   }
  
   public void toObject(Scanner scan)
   {
      int row = scan.nextInt();
      int col = scan.nextInt();
      super.moveTo(maze.getSquare(row, col));
   }
}

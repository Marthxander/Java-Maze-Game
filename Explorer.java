/**
 * Explorer.java
 * Class that creates an Explorer who is controlled by the player. Goal is to collect all treasure in maze.
 *
 * @author Alex Castanares section 14
 * @version May 1st, 2015
 */

import java.util.*;

public class Explorer extends Occupant
{
   private String name;
   private Maze maze;

   public Explorer(Maze maze)
   {
      this.maze = maze;
   }

   public Explorer(Square location, Maze maze, String name)
   {
      super(location);
      this.maze = maze;
      this.name = name;
      maze.lookAround(location);
   }

   public String name()
   {
      return name;
   }

   public void move(int key)
   {
      Square sq = super.location();
      int row = sq.row();
      int col = sq.col();
      if(key == 39 || key == 227)
      {
         if(sq.wall(1) == false)
         {
            col = col + 1;
            moveTo(maze.getSquare(row, col));
         }
      }
      if(key == 37 || key == 226)
      {
         if(sq.wall(3) == false)
         {
            col = col - 1;
            moveTo(maze.getSquare(row, col));
         }
      }
      if(key == 38 || key == 224)
      {
         if(sq.wall(0) == false)
         {
            row = row - 1;
            moveTo(maze.getSquare(row, col));
         }
      }
      if(key == 40 || key == 225)
      {
         if(sq.wall(2) == false)
         {
            row = row + 1;
            moveTo(maze.getSquare(row, col));
         }
      }
   }

   public void moveTo(Square s)
   {
      super.moveTo(s);
      s.enter();
      maze.lookAround(s);
   }

   public String toText(char delimiter)
   {
      return super.toText(delimiter) + delimiter + name;
   }

   public void toObject(Scanner scan)
   {
      int row = scan.nextInt();
      int col = scan.nextInt();
      moveTo(maze.getSquare(row, col));
      name = scan.next();
   }
}

  

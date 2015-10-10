/**
 * Treasure.java
 * Treasure class that creates a Treasure object that is to be searched for by the explorer
 * 
 * @author Alex Castanares section 14
 * @version May 1st, 2015
 */

import java.util.*;

public class Treasure extends RandomOccupant
{
   private boolean found;

   public Treasure(Maze maze)
   {
      super(maze);
      found = false;
      
   }

   public Treasure(Maze maze, long seed)
   {
      super(maze, seed);
      found = false;
   }
   
   public Treasure(Maze maze, Square location)
   {
      super(maze, location);
      location.setTreasure(this);
      found = false;
   }

   public boolean found()
   {
      return found;
   }
 
   public void setFound()
   {
      found = true;
   }
 
   public void move(){}

   public void moveTo(Square square)
   {
      if(super.location().treasure() != null)
      {
         super.location().setTreasure(null);
      }
   square.setTreasure(this);
   }

   public String toText(char delimiter)
   {
      return super.toText(delimiter) + delimiter + found;
   }

   public void toObject(Scanner scan)
   {
      super.toObject(scan);
      found = scan.nextBoolean();
   }
}

/**
 * Occupant.java
 * Abstract class that represents any object that will hold a location within the maze.
 *
 * @author Alex Castanares section 14
 * @version May 1st, 2015
 */

public abstract class Occupant implements DelimitedTextIO
{
   private Square square;
   
   public Occupant(){}

   public Occupant(Square start)
   {
      square = start;
   }

   public Square location()
   {
      return square;
   }

   public void moveTo(Square newLoc)
   {
      square = newLoc;
   }

   public String toText(char delimiter)
   {
      return getClass().getName() + delimiter + square.row() + delimiter + square.col();
   }

}

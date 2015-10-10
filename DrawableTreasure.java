/**
 * DrawableTreasure.java
 * Class that extends Treasure and includes a Draw function to create an image of treasure on the screen
 * 
 * @author Alex Castanares section 14
 * @version May 1st, 2015
 */

public class DrawableTreasure extends Treasure implements Drawable
{
   public DrawableTreasure(Maze maze)
   {
      super(maze);
   }

   public DrawableTreasure(Maze maze, long seed)
   {
      super(maze, seed);
   }

   public DrawableTreasure(Maze maze, Square location)
   {
      super(maze, location);
   }

   public void draw(){}
}

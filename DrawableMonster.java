/**
 * DrawableMonster.java
 * Class that extends Monster and uses a Draw function to create a monster on the screen
 *
 * @author Alex Castanares section 14
 * @version May 1st, 2015
 */

public class DrawableMonster extends Monster implements Drawable
{
   public DrawableMonster(Maze maze)
   {
      super(maze);
   }

   public DrawableMonster(Maze maze, long seed)
   {
      super(maze, seed);
   }

   public DrawableMonster(Maze maze, Square location)
   {
      super(maze, location);
   }

   public void draw(){}
}

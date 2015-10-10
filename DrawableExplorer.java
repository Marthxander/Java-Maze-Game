/**
 * DrawableExplorer.java
 * Class that extends the Explorer class and it implements a draw feature to create an explorer on screen
 *
 * @author Alex Castanares section 14
 * @version May 1st, 2015
 */

public class DrawableExplorer extends Explorer implements Drawable
{
   public DrawableExplorer(Square location, Maze maze, String name)
   {
      super(location, maze, name);
   }

   public void draw(){}
}

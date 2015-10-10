/** 
 * DrawableMaze.java
 * Creates a Maze by extending Maze and has a Draw function that will print the maze ont the screen
 *
 * @author Alex Castanares
 * @version May 1st, 2015
 */

public class DrawableMaze extends Maze implements Drawable
{
   public DrawableMaze(DrawableSquare[][] maze, int rows, int cols)
   {
      super(maze, rows, cols);
   }

   public void draw(){}
}

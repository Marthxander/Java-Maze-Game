/**
 * Monster.java
 * Class that creates a Monster who randomly wanders the maze. If monster is on the same square as explorer, the monster wins.
 *
 * @author Alex Castanares
 * @version May 1st, 2015
 */

public class Monster extends RandomOccupant
{
   public Monster(Maze maze)
   {
      super(maze);
   }

   public Monster(Maze maze, long seed)
   {
      super(maze, seed);
   }
   
   public Monster(Maze maze, Square location)
   {
      super(maze, location);
   }
   
}

/**
 * Square.java
 * Square class that creates a Square object that represents a specific location of Treasures, Monsters, and Explorers within the maze
 * 
 * @author Alex Castanares section 14
 * @version May 1st, 2015
 */

import java.util.*;

public class Square implements DelimitedTextIO
{
   public static final int SQUARE_SIZE = 50;
   public static final int UP = 0;
   public static final int RIGHT = 1;
   public static final int DOWN = 2;
   public static final int LEFT = 3;
   private boolean[] walls;
   private boolean seen;
   private boolean viewable;
   private int row;
   private int col;
   private Treasure treasure;

   public Square(int row, int col)
   {
      this.row = row;
      this.col = col;
   }

   public Square(boolean up, boolean right, boolean down, boolean left, int row, int col)
   {
      walls = new boolean[4];
      walls[0] = up;
      walls[1] = right;
      walls[2] = down;
      walls[3] = left;
      this.row = row;
      this.col = col;
   }

   public boolean wall(int direction)
   {
      if(direction == UP)
      {
         return walls[0];
      }
      else if(direction == RIGHT)
      {
         return walls[1];
      }
      else if(direction == DOWN)
      {
         return walls[2];
      }
      else if(direction == LEFT)
      {
         return walls[3];
      }
      else
      {
         return true;
      }
   }

   public boolean seen()
   {
      return seen;
   }

   public boolean inView()
   {
      return viewable;
   }

   public int x()
   {
      return SQUARE_SIZE * col;
   }

   public int y()
   {
      return SQUARE_SIZE * row;
   }

   public int row()
   {
      return row;
   }
   
   public int col()
   {
      return col;
   }

   public void setInView(boolean inView)
   {
      viewable  = inView;
      if (viewable == true)
      {
         seen = true;
      }
   }

   public void setTreasure(Treasure t)
   {
      treasure = t;
   }

   public void enter()
   {
      if(treasure != null)
      {
         treasure.setFound();
      }
   }
   
   public Treasure treasure()
   {
      return treasure;
   }  

   public String toText(char delimiter)
   {
      return getClass().getName() + delimiter + row() + delimiter + col() + delimiter + walls[UP] + delimiter + walls[RIGHT] + delimiter + walls[DOWN] + delimiter + walls[LEFT] + delimiter + seen() + delimiter + inView();
   }   

   public void toObject(Scanner scan)
   {
         walls = new boolean[4];
         for(int i=0; i<6; i++)
         {
            if(i <= 3)
            {
               walls[i] = scan.nextBoolean();
            }
            else if(i == 4)
            {
               seen = scan.nextBoolean();
            }
            else
            {
               viewable = scan.nextBoolean();
            }

         }
   }
         
}

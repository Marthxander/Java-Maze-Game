public class MazeReadException extends Exception
{
   private String line;
   private int lineNumber;
   public MazeReadException(String msg, String line, int lineNumber)
   {
      super(msg);
      this.line = line;
      this.lineNumber = lineNumber;
   }

   public String getLine()
   {
      return line;
   }
 
   public int getLineNum()
   {
      return lineNumber;
   }
}

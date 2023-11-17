package src;
import java.io.PrintWriter;

/**
 * Holds the main method that will perform the character set conversions.
 * @author Jayson Kunkel
 */
public class BrailleASCII {
  
  // +--------+---------------------------------------------------
  // | Fields |
  // +--------+

  // +--------------+---------------------------------------------------
  // | Constructors |
  // +--------------+

  // +---------+---------------------------------------------------
  // | Methods |
  // +---------+

  /** 
   * Takes two command-line arguments, the first of which represents the target character set and the second of 
   * which represents the source characters, and then translates the text.
   */
  public static void main(String[] args) throws Exception{

    PrintWriter pen = new PrintWriter(System.out, true);

    // perform initial checks for command-line arguments
    initialCheck(args);

    String output = args[0];
    String input = args[1];

    // perform specified conversion
    convert(output, input, pen);
  } // main(String[])

  /**
   * Checks that there are only two command-line arguments and that the first is either
   * "braille", "ascii", or "unicode"
   */
  public static void initialCheck(String[] args) throws Exception{

    if(args.length != 2){
      throw new Exception("Error: Incorrect number of arguments");
    } // if there are not 2 command line arguments

    String target = args[0];
    if(!target.equals("braille") && !target.equals("ascii") && !target.equals("unicode")){
      throw new Exception("Error: Incorrect target character set");
    } // if first argument is not a valid character set
  } // initialCheck(String[])

  /**
   * Handles the conversions for given target character set and source characters
   */
  public static void convert(String output, String input, PrintWriter pen){
    if(output.equals("braille")){
      for(int i = 0; i < input.length(); i++){
        pen.print(BrailleASCIITables.toBraille(input.charAt(i)));
        pen.flush();
      } // for each character in input string
    } // if converting to braille
    else if(output.equals("ascii")){
      for(int i = 0; i < input.length(); i += 6){
        pen.print(BrailleASCIITables.toASCII(input.substring(i, i + 6)));
        pen.flush();
      }
    } // if converting to ASCII
    else{
      for(int i = 0; i < input.length(); i++){
        pen.print(BrailleASCIITables.toUnicode(BrailleASCIITables.toBraille(input.charAt(i))));
        pen.flush();
      }
    } // else converting to Unicode
  } // convert(String, String, PrintWriter)

} // class BrailleASCII

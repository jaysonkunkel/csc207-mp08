package src;
import java.io.File;
import java.io.FileInputStream;

/**
 * Performs the character set conversions and stores the information in bit trees.
 * @author Jayson Kunkel
 */
public class BrailleASCIITables {
  
  // +--------+---------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Stores the Braille to ASCII mappings.
   */
  static BitTree BrailleToASCII = new BitTree(6);

  /**
   * Stores the Braille to Unicode mappings.
   */
  static BitTree BrailleToUnicode = new BitTree(6);

  /**
   * Stores the ASCII to Braille mappings.
   */
  static BitTree ASCIIToBraille = new BitTree(8);

  // +--------------+---------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Sets all valid paths in each conversion tree.
   */
  public BrailleASCIITables() throws Exception{
    BrailleToASCII.load(new FileInputStream(new File("BrailleToASCII.txt")));
    BrailleToUnicode.load(new FileInputStream(new File("BrailleToUnicode.txt")));
    ASCIIToBraille.load(new FileInputStream(new File("ASCIIToBraille.txt")));
  } // BrailleASCIITables()

  // +---------+---------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Converts an ASCII character to a string of bits representing the corresponding braille character.
   */
  public static String toBraille(char letter){
    try {
      // convert letter to its binary representation
      return ASCIIToBraille.get("0" + Integer.toBinaryString((int)letter));
    } catch (Exception e) {
      return null;
    } //try...catch
  } // toBraille(char)

  /**
   * Converts a string of bits representing a braille character to the corresponding ASCII character.
   */
  public static String toASCII(String bits){
    try {
      return BrailleToASCII.get(bits);
    } catch (Exception e) {
      return null;
    } // try...catch
  } // toASCII(String)

  /**
   * Converts a string of bits representing a braille character to the corresponding Unicode character for those bits.
   */
  public static String toUnicode(String bits){
    try {
      String uni = BrailleToUnicode.get(bits);
      // parse the codepoint of the Unicode string
      int hex = Integer.parseInt(uni, 16);
      // cast codepoint to char
      char c = (char) hex;
      // convert it to a string
      return Character.toString(c);
    } catch (Exception e) {
      return null;
    } // try...catch
  } // toUnicode(String)

} // class BrailleASCIITables

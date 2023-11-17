package src;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Stores mappings from bits to values.
 * @author Jayson Kunkel
 */
public class BitTree{

  // +--------+---------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The number of levels in the tree.
   */
  int size;

  /**
   * The root of the bit tree.
   */
  BitTreeNode root;

  // +--------------+---------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Builds a tree that will store mappings from strings of n bits to strings.
   */
  public BitTree(int n){
    this.size = n;
    //this.root = null;
    this.root = new BitTreeNode();
  } // BitTree(int)

  // +---------+---------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Follows the path through the tree given by bits and adds or replaces the end value with given value.
   * Throws an exception if bits is an inappropriate length or contains values other than 0 and 1.
   */
  public void set(String bits, String value) throws Exception{

    if(bits.length() != this.size){
      throw new Exception("Error: Bits is the incorrect length");
    } // if bits and this tree are different sizes

    //this.root = new BitTreeNode();
    BitTreeNode current = this.root;

    // follow the path specified by bits and add or replace the value at the end with value
    for(int i = 0; i < bits.length(); i++){
  
      if(bits.charAt(i) == '0'){
        if(current.left == null){
          current.left = new BitTreeNode();
        } // if left subtree does not exist
        current = current.left;
      } // if current bit is 0
      else if(bits.charAt(i) == '1'){
        if(current.right == null){
          current.right = new BitTreeNode();
        } // if right subtree does not exist
        current = current.right;
      } // else if current bit is 1
      else {
        throw new Exception("Error: bits should only contain 0s and 1s");
      } // else current bit is neither 0 nor 1
    } // for each value in bits

    // set value at end of tree to given value
    current.value = value;
  } // set(String, String)

  /**
   * Follows the path through the tree given by bits and returns the value at the end.
   * Throws an exception if there is no such path or if bits is the incorrect length.
   */
  public String get(String bits) throws Exception{

    if(bits.length() != this.size){
      throw new Exception("Error: Bits is the incorrect length");
    } // if bits and this tree are different sizes

    if(this.root == null){
      throw new Exception("Error: Root is null");
    } // if the root of this tree is null

    int i = 0;
    BitTreeNode current = this.root;

    try{
      while(current.left != null || current.right != null){
        // move to the left subtree
        if(bits.charAt(i) == '0'){
          current = current.left;
          i++;
        } // if current bit is 0
        else if (bits.charAt(i) == '1'){
          // move to the right subtree
          current = current.right;
          i++;
        } // else if current bit is 1
        else{
           throw new Exception("Error: bits should only contain 0s and 1s");
        } // else current bit is neither 0 nor 1
      } // while a left or right subtree exists

      // return value at end of tree
      return current.value;
    } catch(Exception e){
      return("Error: Invalid path");
    } // try...catch
  } // get(String)

  // /**
  //  * Prints out the contents of the tree in CSV format. For example, "101100,M"
  //  */
  // public void dump(PrintWriter pen){
  //   String result = "";
  //   BitTreeNode current = this.root;
  //   while(current.left != null || current.right != null){
  //     if(current.left == null){
  //       current = current.right;
  //       result = result + "1";
  //     } // if only right subtree exists
  //     else{
  //       current = current.left;
  //       result = result + "0";
  //     } // else if only left subtree exists
  //   } // while left and right subtrees exist
  //   pen.println("Result: " + result + "," + current.value);
  // } // dump(PrintWriter)

  /**
   * Prints out the contents of the tree in CSV format. For example, "101100,M"
   */
  public void dump(PrintWriter pen){
    dump(pen, this.root, "");
  } // dump (PrintWriter)

  public void dump(PrintWriter pen, BitTreeNode node, String result){
    pen.flush();
    if(node == null){
      // do nothing
    } // if node is null
    else if(node.left == null && node.right == null){
      // no subtrees exist, so node is a leaf; print its path and value
      pen.println(result + "," + node.value);
    } // if node is a leaf
    else{
      if((node.left != null) || (node.right != null)){
        // node is an interior node; recursively dump its subtrees
        dump(pen, node.left, new String(result + "0"));
        dump(pen, node.right, new String(result + "1"));
      } // if a left or right subtree exists
    } // else node is an interior node
  } // dump (PrintWriter, BitTreeNode, String)

  /**
   * Reads a series of lines of the form bits,value and stores them in the tree.
   */
  public void load(InputStream source){
    try {
      // create a new buffered reader
      BufferedReader br = new BufferedReader(new InputStreamReader(source));
      String line;
      String[] info;
      while((line = br.readLine()) != null){
        // separate the line into the bit string and value
        info = line.split(",");
        // create a new path in this tree
        this.set(info[0], info[1]);
      } // while there are lines to read
    } catch (Exception e) {
      System.err.println("Error: Could not read from source");
    } // try...catch
  } // load(InputStream)

} // class BitTree
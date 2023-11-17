package src;
/**
 * Nodes in a bit tree. 
 * @author Jayson Kunkel
 */
public class BitTreeNode{

  // +--------+---------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The character represented by the tree. Null if not a leaf.
   */
  String value;

  /**
   * The left subtree.
   */
  BitTreeNode left;

  /**
   * The right subtree.
   */
  BitTreeNode right;

  // +--------------+---------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new node.
   */
  public BitTreeNode(){
    this(null);
  } // BitTreeNode()

  /**
   * Create a new node with given value.
   */
  public BitTreeNode(String value){
    this.value = value;
    this.left = null;
    this.right = null;
  } // BitTreeNode()

} // class BitTreeNode

// /**
//  * Leaves in a bit tree.
//  */
// class BitTreeLeaf extends BitTreeNode{

//   // +--------+---------------------------------------------------
//   // | Fields |
//   // +--------+

//   /**
//    * The character represented by the entire bit tree.
//    */
//   String value;

//   /**
//    * The left subtree.
//    */
//   BitTreeNode left;

//   /**
//    * The right subtree.
//    */
//   BitTreeNode right;

//   /**
//    * Create a new leaf.
//    */
//   BitTreeLeaf(String value){
//     super();
//     this.value = value;
//   } // BitTreeLeaf(String)

// } // class BitTreeLeaf
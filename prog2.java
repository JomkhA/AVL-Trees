import java.io.*;
import java.util.*;

 /*--------------------------------------------------
    Author: Tum Jomkhanthiphol
    Class: COMP282 (M & W, 2:00 - 3:15 pm)
    Assignment #2
    Date handed in: 10/19/2021
    Programming assignment #2, AVL Trees
   --------------------------------------------------*/

class StringAVLNode {
  private String item;
  private int balance;
  private StringAVLNode lf, rt;

  public StringAVLNode(String str) { 
    this.item = str;
    setBalance(0);
    setLeft(null);
    setRight(null);
  }

  public int getBalance() { 
    return balance;
  }

  public void setBalance(int bal) { 
    this.balance = bal;
  }
  
  public String getItem() { 
    return item;
  }
  
  public StringAVLNode getLeft() { 
    return lf;
  }
  
  public void setLeft(StringAVLNode pt) { 
    this.lf = pt;
  }
  
  public StringAVLNode getRight() { 
    return rt;
  }
  
  public void setRight(StringAVLNode pt) { 
    this.rt = pt;
  }
} // StringAVLNode

class StringAVLTree {
  StringAVLNode root;

  // Just one constructor
  public StringAVLTree() { 
    root = null;
  }
  
  public void insert(String d) { 
    root = insert(d, root);
  }
  
  private StringAVLNode insert(String d, StringAVLNode t) {
    int OldBalance, NewBalance;
    if (t == null)
      t = new StringAVLNode(d);

    // insert left if d is smaller
    else if (d.compareToIgnoreCase(t.getItem()) < 0) {
      if (t.getLeft() == null)   // d gets inserted into the tree here
        OldBalance = 282;      // kludgey, but simple
      else
        OldBalance = t.getLeft().getBalance();

      t.setLeft(insert(d, t.getLeft()));
      NewBalance = t.getLeft().getBalance();
  
      // has the height of the left subtree increased?
      if ((OldBalance == 0 && NewBalance != 0) || OldBalance == 282) {
        if (t.getBalance() == 1) // fix balances
          t.setBalance(0);

        else if (t.getBalance() == 0) {
          t.setBalance(-1);
        } 

        else {  // must rotate
          if (t.getLeft().getBalance() == -1) {
            // LL insert, so single rotation
            t = rotateRight(t);
            t.setBalance(0);
            t.getRight().setBalance(0);
          } 

          else { 
            // LR insert, so double rotation
            t.setLeft(rotateLeft(t.getLeft()));
            t = rotateRight(t);
  
            // update balances
            if (t.getBalance() == 0) {
              t.getLeft().setBalance(0);
              t.getRight().setBalance(0); 
            } 

            else if (t.getBalance() == -1) {
              t.getLeft().setBalance(0);
              t.getRight().setBalance(1);
            } 
  
            else {
              t.getLeft().setBalance(-1);
              t.getRight().setBalance(0);
            }

            t.setBalance(0);
          }
        }
      }
    } 

    // else insert right
    else if (d.compareToIgnoreCase(t.getItem()) > 0) { 
      if (t.getRight() == null)   // d gets inserted into the tree here
        OldBalance = 282;      // kludgey, but simple
      else
        OldBalance = t.getRight().getBalance();

      t.setRight(insert(d, t.getRight()));
      NewBalance = t.getRight().getBalance();

      // has the height of the right subtree increased?
      if ((OldBalance == 0 && NewBalance != 0) || OldBalance == 282) {
        if (t.getBalance() == -1) // fix balances
          t.setBalance(0);

        else if (t.getBalance() == 0) {
          t.setBalance(1);
        } 
  
        else {  // must rotate
          if (t.getRight().getBalance() == 1) {
            // RR insert, so single rotation
            t = rotateLeft(t);
            t.setBalance(0);
            t.getLeft().setBalance(0);
          } 

          else { 
            // RL insert, so double rotation
            t.setRight(rotateRight(t.getRight()));
            t = rotateLeft(t);
  
            // update balances
            if (t.getBalance() == 0) {
              t.getLeft().setBalance(0);
              t.getRight().setBalance(0); 
            } 

            else if (t.getBalance() == 1) {
              t.getLeft().setBalance(-1);
              t.getRight().setBalance(0); 
            } 

            else {
              t.getLeft().setBalance(0);
              t.getRight().setBalance(1);
            }

            t.setBalance(0);
          }
        }
      }
    }

    // else d is already in the tree
    return t;
  }
  
  // Rotate the node to the right
  private static StringAVLNode rotateRight(StringAVLNode t) { 
    StringAVLNode i = t.getLeft();
    StringAVLNode j = i.getRight();
    i.setRight(t);
    t.setLeft(j);
    return i;
  }
  
  // Rotate the node to the left
  private static StringAVLNode rotateLeft(StringAVLNode t) { 
    StringAVLNode i = t.getRight();
    StringAVLNode j = i.getLeft();
    i.setLeft(t);
    t.setRight(j);
    return i;
  }

  // Return the height of the tree – not to be used anywhere in insert or delete
  public int height() { 
    return height(root);
  }

  public int height(StringAVLNode n) { 
    if (n == null)
      return 0;

    else {
      int lHeight = height(n.getLeft());
      int rHeight = height(n.getRight());

      if (lHeight > rHeight)
        return lHeight + 1;
      else
        return rHeight + 1;
    }
  }
  
  // Return the depth of the closest leaf to the root
  public int nadir() { 
    return nadir(root);
  }

  public int nadir(StringAVLNode n) { 
    if (n == null)
      return 0;

    int lDepth = nadir(n.getLeft());
    int rDepth = nadir(n.getRight());

    if (n.getLeft() == null)
      return 1 + rDepth;
    if (n.getRight() == null)
      return 1 + lDepth;

    return Math.min(lDepth, rDepth) + 1;
  }
  
  // Return the number of leaves in the tree
  public int leafCt() {
    return leafCt(root);
  }

  public int leafCt(StringAVLNode n) { 
    if (n == null)
      return 0;
    if (n.getLeft() == null && n.getRight() == null)
      return 1;

    else
      return leafCt(n.getLeft()) + leafCt(n.getRight());
  }
  
  // Return the number of perfectly balanced AVL nodes
  public int balanced() { 
    return balanced(root);
  }

  public int balanced(StringAVLNode n) { 
    if (n == null)
      return 0;
    if (n.getBalance() == 0)
      return 1 + balanced(n.getLeft()) + balanced(n.getRight());
    else
      return balanced(n.getLeft()) + balanced(n.getRight());
  }
  
  // Return the inorder successor, i.e., the next larger value in the tree
  // or null if there is none or str is not in the tree
  public String successor(String str) {
    StringAVLNode answer = successor(root, null, str);
    if (answer != null)
      return answer.getItem();
    else
      return null;
  }

  private StringAVLNode successor(StringAVLNode focus, StringAVLNode old, String str) {
    if (focus == null) {
      return null;
    }

    if (focus.getItem().compareToIgnoreCase(str) == 0) {
      if (focus.getRight() != null) 
        return minimum(focus.getRight());
      else
        return old;
    }

    else if (focus.getItem().compareToIgnoreCase(str) > 0) {
      old = focus;
      return successor(focus.getLeft(), old, str);
    }

    else if (focus.getItem().compareToIgnoreCase(str) < 0)
      return successor(focus.getRight(), old, str);

    return null;
  }

  public static StringAVLNode minimum(StringAVLNode n) {
    while (n.getLeft() != null)
      n = n.getLeft();
    return n;
  }

  public static String myName() { 
    return "Tum Jomkhanthiphol";
  }
} 
// StringAVLTree class
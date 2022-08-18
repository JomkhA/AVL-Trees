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

	} 

	else // must rotate
	  if (t.getLeft().getBalance() == -1) { // LL insert, so single rotation
	    t = rotateRight(t);
	    t.setBalance(0);
	    t.getRight().setBalance(0);
	  } 

	  else { 
	    // LR insert, so double rotation
	    // update balances
        if (t.getBalance() == 0) {
	  					
	    } 

	    else if (t.getBalance() == -1) {
	  					
	    } 

	    else {
	  					
	    }

	    t.setBalance(0);
	  }	
  } 

  // else insert right
  else if (d.compareToIgnoreCase(t.getItem()) > 0) { 
			
  }

  // else d is already in the tree
  return t;
}

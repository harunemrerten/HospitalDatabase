
// normally ı did not want my value was extends a interface how ever my key sorts things with Date but not names so in order to search doctor patient or staffmember ı needed a interface and all of the class should have get name thing or stuff. 
public class MyBSTree <Key extends Comparable<Key>, Value extends Person>{
	
	// inner class for encapsulation matters
	private class Node {
		//--------------------------------------------------------
		 // Summary: This is not a method but a private inner class it is Node
		 // Precondition: Key and Value of the generic part of MYBSTree is here 
		 // Key is generic and Value is generic but ı make them extends some interfaces so that ı can get some specific methods from 
		 // the thing that is ı putted in the generic values
		 // Postcondition: create key value and Node for left and right child.
		 //-------------------------------------------------------
		
		private Key key;
		private Value val;
		private Node left, right;
		
		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}// I know it is not seems good code but it was needed for space complexity ı need generic type search tree however ı need specific case method so ı create getter to these.
		
	}
	
	private Node root;
	
	
	public MyBSTree() {
		root=null;
	}
	
	// for put methods. there is one main put method and second put method as put method helper. 
	public void put(Key key, Value val) {
		//--------------------------------------------------------
		 // Summary: it is a recursive method it is for the adding thing to the search tree if we have key value
		 // Precondition: key is generic for Key val is generic for Value 
		 // and we have private method that is the same name as this method 
		 // Postcondition: root = put
		 //--------------------------------------------------------
		root = put(root, key, val);
	}
	private Node put(Node x, Key key, Value val) {
		//--------------------------------------------------------
		 // this is the real put value we call this in the put public method parameters are the same but we have node which is root for public method
		 // Precondition: key is generic for Key val is generic for Value and Node 
		 // compares every thing if new key is bigger from the nodes key go right if not go left.
		 // Postcondition: The value of the variable is set.
		 //--------------------------------------------------------
		
		if (x == null) 
			return new Node(key, val);
		
		int cmp = key.compareTo(x.key);
		
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else if (cmp == 0)
			x.val = val;
		
		return x;
	}

	// for getting value that node holds.
	public Value get(Key key) { 
		//--------------------------------------------------------
		 // Summary: search and get the value whose Key value is same as Key
		 // name is given.
		 // Precondition: Key key is generic 
		 // Warning: this is only for search by Key if the key does not exist or if it is the tree is not ordered by the same type of key it does not work properly
		 // Postcondition: if key bigger go right and if it is smaller go left if is equal return.
		 //--------------------------------------------------------
		Node x = root;
		
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else if (cmp == 0) return x.val;
		}
		
		return null;
	}
	
	// for displaying things Ascendingly but it requires value to have instance or value should have to String
	public void displayAscendingOrder() {
		//--------------------------------------------------------
		 // Summary: It is public method and it calls private version of the method which has the same name to him
		 // And we send root in it.
		 //--------------------------------------------------------
		displayAscendingOrder(root);
	}
	private void displayAscendingOrder(Node x) {
		//--------------------------------------------------------
		 // Summary: gets the root and go all the elements from left to right one by one
		 // Precondition: x is a node which starts from root
		 // Postcondition: goes left and starts the print x.val which works thanks to the toString method
		 // and does the same thing to the remaining parts.
 		 //--------------------------------------------------------
		if (x!=null) {
			displayAscendingOrder(x.left);
			System.out.println(x.val);// it works with toString method
			displayAscendingOrder(x.right);
		}
	}
	// for displaying things discendingly but it requires value to have instance or value should have to String
	public void displayDescendingOrder() {
		//--------------------------------------------------------
		 // Summary: Exactly like the displayAscendingOrder method
		 //--------------------------------------------------------
		displayDiscendingOrder(root);
	}
	private void displayDiscendingOrder(Node x) {
		//--------------------------------------------------------
		 // Summary: Exactly like the displayAscendingOrder method but this starts from right to left so it is in discendingOrders
		 //--------------------------------------------------------
		if (x!=null) {
			displayDiscendingOrder(x.right);
			System.out.println(x.val);// it works with toString method
			displayDiscendingOrder(x.left);
		}
	}
	
	
	
	// I created this method for different usage. In order to get different string from it
	public void diferentDisplay(boolean condition,int isTimeExist) {
		//--------------------------------------------------------
		 // Summary: this work same as the displayAscending method however there are some differences which is this takes a boolean and int 
		 // Precondition: condition is a boolean and isTimeExist is an int in homework we had some different type of display query and they all print different things about the val Value and toString was not enough
		 // So ı created a new method in interface called person and this metod also returns a String however it has if else condition if true printing type is changing if false again changing into different type of printing 
		 // Postcondition: we add the root in the helper method that has same name for private
		 //--------------------------------------------------------
		diferentDisplay(root,condition,isTimeExist);
		
	}
	private void diferentDisplay(Node x,boolean condition,int isTimeExist) {
		// I could not take the thing in the same year. Hoca suggested that we could get the same years to the left part or right part to the tree but ı did not want to use that solution. So ı use something weird ı made this method gerneric
		// and this method take this date and if needed it uses in an if condittion if it is not needed it uses else condition
		
		 //--------------------------------------------------------
		 // Summary: it takes the root as in x and already as ı explained before it the boolean is true which is condition
		 // it prints the values is same format as the showPatients(int visitYear) method in HospitalDatabase class 
		 // Precondition: x is root condition is boolean isTimeExist is int
		 // and if the boolean is false it prints things in the same format as showDoctorPatients(String dName) method in HospitalDatebase class
		 // Postcondition: prints everything in the tree according to the the queries :)
		 //--------------------------------------------------------
		if (x!=null) {
			if(condition) {
				diferentDisplay(x.right,condition,isTimeExist);
				if(isTimeExist==x.val.getYearIfexist(true))
				System.out.println(x.val.getNameAndDate(true));
				diferentDisplay(x.left,condition,isTimeExist);
				
			}else {
				diferentDisplay(x.left,condition,isTimeExist);
				System.out.println(x.val.getNameAndDate(false));
				diferentDisplay(x.right,condition,isTimeExist);
			}
		}
	}
	

	
	// this is made a little bit hard and it does not use an ordered tree according to name so it does work inefficiently but the work that is wanted from me is require to search by name so ı created an interface then look all the element one by one.
	public Value search(String fullName) {
		//--------------------------------------------------------
		 // Summary: it search people from objects which is interface that Value extends with a String 
		 // But this is important this string is not Key for tree. if it is use get method of mine this search if key is different and if it needs to search everything one by one
		 // Precondition: fullName is string
		 // Postcondition: node created and it calls the private search method which has the node and string as parameters.
		 //--------------------------------------------------------
		
		Node x = search(fullName,root);
		if(x==null)
			return null;
		return x.val;

	}
	private Node search(String fullName,Node x) {
		//--------------------------------------------------------
		 // Summary: ı summarized in public search method
		 // Precondition: x is root fullname is string
		 // Postcondition: if x== nul return otherwise compare getFullName of val with the given string for all nodes then return that node which is equal. but recursively
		 //--------------------------------------------------------
		if(x==null)
			return null;
		if(x.val.getFullName().compareTo(fullName)==0)
			return x;
		// my implementations was similar to the bottom part but even if a could find the result ı could not return it. it gives me error so ı take help from ChatGpt
			Node controllingNode=search(fullName,x.left);
			if(controllingNode!= null) {
				return controllingNode;
			}
			return search(fullName,x.right);
	}
	
	// before delete method ı write delete minimum method
	public void deleteMin() {
		//--------------------------------------------------------
		 // Summary: this is for calling the private deleteMin as public 
		 // Postcondition: we defined root into private deletemin with root in it
		 //--------------------------------------------------------
		root = deleteMin(root);
	}
	private Node deleteMin(Node x) {
		//--------------------------------------------------------
		 // Summary: gets x which is root at first then it iterate itself until it finds the mind and makes it null.
		 // Precondition: x is root Node
		 // Postcondition: Node with minimum value deleted.
		 //--------------------------------------------------------
		if (x.left == null) 
			return x.right;
			x.left = deleteMin(x.left);
			return x;
	}
	
	// before delete method ı write find minimum method
	private Node findMin(Node x) {
		//--------------------------------------------------------
		 // Summary: it is same as deleteMin root but it stops without deleting an returns.
		 // Postcondition:  x turned the min value and method returned as the smallest.
		 //--------------------------------------------------------
		if (x.left == null) 
			return x;
			return findMin(x.left);	
	}
	
	//this method is not efficient because ı am searching with name and not a key if the name is not key ı think its n*log(n) in general but if three is way further than balanced it is time complexity becomes n^2.
	public void deleteBySearching (String fullName) {
		//--------------------------------------------------------
		 // Summary: btw this was on of the challenging thing. now we get name to search a node and delete it. we will send it to the private deletebysearch.
		 // Postcondition: fullName is string.
		 //--------------------------------------------------------
		
		if(search(fullName)==null) {
			return ;
		}
		else {
			Node x = root;
			deleteBySearching(fullName, x);
		}
	}
	private Node deleteBySearching(String fullName,Node x) {
		//--------------------------------------------------------
		 // Summary: now we will search the node but not get the node itself if we do we can not get the parent and can not delete the node
		 // so find the parent of it then delete it
		 // Precondition: Node is x for root at first and we have string fullname.
		 // Postcondition: use search method on left if we have such that node if not for the right. if one of them has go that way but not with itself instead with its web child or whatever it is
		 // if we can not find on right or left anymore that means we get there now delete this if left is not there turn into right if we dont have left return right so it is deleted exactly we will reverse
		 // if not right and left is not null then copy the minimum value with findmin then copy old left and old right to changed note then delete the min.
		 //--------------------------------------------------------
		if (x==null) {
			return null;
		}
		if(search(fullName,x.left)!=null) {
			 x.left=deleteBySearching(fullName, x.left);
		}else if(search(fullName,x.right)!=null) {
			  x.right=deleteBySearching(fullName, x.right);
		}else {
			
			if(x.left==null)
				return x.right;
			if(x.right==null) 
				return x.left;
			// we will change the found one with the node's parts maximum node and copy it after then we will delete it.
			Node tmp= x;
			x=findMin(tmp.right);
			x.right = deleteMin(tmp.right);
			x.left=tmp.left;
		}
		return x;
	}

	
	public void delete (Key key) {
		//--------------------------------------------------------
		 // I will not explain this this is exactly like the deleteby searching but easier
		 //--------------------------------------------------------
		Node x = root;
		if(get(key)!=null){
			delete(x, key);
		}
	}
	private Node delete (Node x,Key key ) {
		//--------------------------------------------------------
		 // Summary: I already explained for the deleteSearch it is nearly the same only difference we dont look if we have such that node in left or right instead compare the key with all then go that way.
		 // normal bts deletebysearch method was extreme because it is for such a state if our string was not key for that bst
		 //--------------------------------------------------------
		if (x==null) {
			return null;
		}
		if(key.compareTo(x.key)>0)
			x.right=delete(x.right, key);
		else if(key.compareTo(x.key)<0) 
			x.left=delete(x.left, key);
		else {
			if(x.left==null)
				return x.right;
			if(x.right==null) 
				return x.left;
			// we will change the found one with the node's parts maximum node and copy it after then we will delete it.
			Node tmp= x;
			x=findMin(tmp.right);
			x.right = deleteMin(tmp.right);
			x.left=tmp.left;
		}
		return x;
	}
	
	
	public boolean isNotNull() {
		if (root!=null)
			return true;
		else
			return false;
	}
	
}

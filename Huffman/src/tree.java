// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.io.*;
import java.util.*; // for Stack class

////////////////////////////////////////////////////////////////
class Node
{   ////changed the node to include a Data type that accepted characters
	public int iData; // data item (key) //frequency of appearances
	public char dData; // data character seen in string
	public Node leftChild; // this node's left child
	public Node rightChild; // this node's right child
	public void displayNode() // display ourself
	{
		System.out.print('{');
		System.out.print(iData);
		System.out.print(", ");
		System.out.print(dData);
		System.out.print("} ");
	}
        //checks to see if there are no children to this node
        public boolean isLeaf() {
            if (leftChild == null && rightChild == null) {
                return true;
            }
            return false;
        }
        
} // end class Node
////////////////////////////////////////////////////////////////
class Tree
{
	public Node root; // first node of tree
	// -------------------------------------------------------------
	public Tree() // constructor
	{ root = null; } // no nodes in tree yet
	//-------------------------------------------------------------
	public Node find(int key) // find node with given key
	{ // (assumes non-empty tree)
		Node current = root; // start at root
		while(current.iData != key) // while no match,
		{
			if(key < current.iData) // go left?
				current = current.leftChild;
			else // or go right?
				current = current.rightChild;
			if(current == null) // if no child,
				return null; // didn't find it
		}
		return current; // found it
	} // end find()
        
        
        ////Function posted by Serge Ballesta on StackOverflow (08/11/2014)
        public String recordPath(char input, Node tmproot, String path) {
            String result;
            if (! tmproot.isLeaf()) {
                if ((result = recordPath(input, tmproot.leftChild, path + '0')) == null) {
                    result = recordPath(input, tmproot.rightChild, path + '1');
                }
            }
            else {
                result = (input == root.dData) ? path : null;
            }
            return result;
        }
        //////////////////////////////////////////////////////////////////////
        //generates a map of all the nodes in a tree.
        public Map<Character, String> genMap(Node tmproot) {
            Map<Character, String> map = new HashMap<Character, String>();
            huffmanTreeAdd(map, tmproot, "");
            return map;
        }
        private void huffmanTreeAdd(Map<Character, String> map, Node tmproot, String path) {
            if (tmproot.isLeaf()) {
                map.put(tmproot.dData, path);
            }
            else {
                huffmanTreeAdd(map, tmproot.leftChild, path + '0');
                huffmanTreeAdd(map, tmproot.rightChild, path + '1');
            }
        }
        
        
        
        

///////////////////////        //////////////////////////////////////////////
	public int findPath(int key, char data) // find node with given key
	{ // (assumes non-empty tree)
		Node current = root; // start at root
		int currentPath = 0b0000000;
                while(current.iData != key) // while no match,
		{
			if(key < current.iData) // go left?
                                    
                                    current = current.leftChild;
                        
                        else if(key > current.iData){
				current = current.rightChild;
                     }
                                if(current == null) // if no child,
				return currentPath; // didn't find it
		}
		return currentPath; // found it
	} // end find()
	// -------------------------------------------------------------
        
        //custom methods to set the child of a node for left and right.
        //arbitrary terms
        public void setLeft(Tree ptree,Node lchild){
            if(root == null){
                
            }else if(ptree.root.leftChild == null){
                ptree.root.leftChild = lchild;
            }
        }
        public void setRight(Tree ptree,Node rchild){
            
            if(ptree.root.rightChild == null){
                ptree.root.rightChild = rchild;
            }else{
                
            }
        }
        //some prototype methods to see if they would work
        //not used
        public void insertRight(int id, char dd)
	{
		Node newNode = new Node(); // make new node
		newNode.iData = id; // insert data
		newNode.dData = dd;
		if(root==null) // no node in root
			root = newNode;
		else // root occupied
		{
			Node current = root; // start at root
			Node parent;
			while(true) // (exits internally)
			{
				parent = current;
				current = current.rightChild;
                                    if(current == null) // if end of the line
                                    { // insert on right
                                            parent.rightChild = newNode;
                                            return;
                                    }else{
                                        System.out.println("there is something here x.x");
                                        return;
                            } 
			} // end while
		} // end else not root
	} 
        
        
        
        
	public void insert(int id, char dd)
	{
		Node newNode = new Node(); // make new node
		newNode.iData = id; // insert data
		newNode.dData = dd;
		if(root==null) // no node in root
			root = newNode;
		else // root occupied
		{
			Node current = root; // start at root
			Node parent;
			while(true) // (exits internally)
			{
				parent = current;
				if(id < current.iData) // go left?
				{
					current = current.leftChild;
					if(current == null) // if end of the line,
					{ // insert on left
						parent.leftChild = newNode;
						return;
					}
				} // end if go left
				else // or go right?
				{
					current = current.rightChild;
					if(current == null) // if end of the line
					{ // insert on right
						parent.rightChild = newNode;
						return;
					}
				} // end else go right
			} // end while
		} // end else not root
	} // end insert()
	// -------------------------------------------------------------
	public boolean delete(int key) // delete node with given key
	{ // (assumes non-empty list)
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		while(current.iData != key) // search for node
		{
			parent = current;
			if(key < current.iData) // go left?
			{
				isLeftChild = true;
				current = current.leftChild;
			}
			else // or go right?
			{
				isLeftChild = false;
				current = current.rightChild;
			}
			if(current == null) // end of the line,
				return false; // didn't find it
		} // end while
		// found node to delete
		// if no children, simply delete it
		if(current.leftChild==null &&
				current.rightChild==null)
		{
			if(current == root) // if root,
				root = null; // tree is empty
			else if(isLeftChild)
				parent.leftChild = null; // disconnect
			else // from parent
				parent.rightChild = null;
		}
		// if no right child, replace with left subtree
		else if(current.rightChild==null)
			if(current == root)
				root = current.leftChild;
			else if(isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;
		// if no left child, replace with right subtree
		else if(current.leftChild==null)
			if(current == root)
				root = current.rightChild;
			else if(isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		else // two children, so replace with inorder successor
		{
			// get successor of node to delete (current)
			Node successor = getSuccessor(current);
			// connect parent of current to successor instead
			if(current == root)
				root = successor;
			else if(isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			// connect successor to current's left child
			successor.leftChild = current.leftChild;
		} // end else two children
		// (successor cannot have a left child)
		return true; // success
	} // end delete()
	// -------------------------------------------------------------
	// returns node with next-highest value after delNode
	// goes to right child, then right child's left descendents
	private Node getSuccessor(Node delNode)
	{
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild; // go to right child
		while(current != null) // until no more
		{ // left children,
			successorParent = successor;
			successor = current;
			current = current.leftChild; // go to left child
		}
		// if successor not
		if(successor != delNode.rightChild) // right child,
		{ // make connections
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	// -------------------------------------------------------------
	public void traverse(int traverseType)
	{
		switch(traverseType)
		{
		case 1: System.out.print("\nPreorder traversal: ");
		preOrder(root);
		break;
		case 2: System.out.print("\nInorder traversal: ");
		inOrder(root);
		break;
		case 3: System.out.print("\nPostorder traversal: ");
		postOrder(root);
		break;
		}
		System.out.println();
	}
	// -------------------------------------------------------------
	private void preOrder(Node localRoot)
	{
		if(localRoot != null)
		{
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	// -------------------------------------------------------------
	private void inOrder(Node localRoot)
	{
		if(localRoot != null)
		{
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);
		}
	}
	// -------------------------------------------------------------
	private void postOrder(Node localRoot)
	{
		if(localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}
	// -------------------------------------------------------------
	public void displayTree()
	{
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(
				"......................................................");
		while(isRowEmpty==false)
		{
			Stack localStack = new Stack();
			isRowEmpty = true;
			for(int j=0; j<nBlanks; j++)
				System.out.print(' ');
			while(globalStack.isEmpty()==false)
			{
				Node temp = (Node)globalStack.pop();
				if(temp != null)
				{       //will show the freq and char assosiated with it
					System.out.print(temp.iData);
                                        //inserted a statement that would allow for showing the char with the freq
                                        if(temp.dData == '?'){
                                            System.out.println();
                                        }
                                        else if(temp.dData == ' '){
                                            System.out.print("]");
                                        }else{
                                            System.out.print(temp.dData);
                                        }
                                            localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if(temp.leftChild != null ||
							temp.rightChild != null)
						isRowEmpty = false;
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<nBlanks*2-2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		} // end while isRowEmpty is false
		System.out.println(
				"......................................................");
	} // end displayTree()
	// -------------------------------------------------------------
} // end class Tree
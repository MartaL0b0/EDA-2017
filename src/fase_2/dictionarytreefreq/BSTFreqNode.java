package fase_2.dictionarytreefreq;


public class BSTFreqNode {

	FList wordlist;
	int key;

	BSTFreqNode parent;
	BSTFreqNode left;
	BSTFreqNode right;

	public BSTFreqNode(){
		wordlist = new FList();
		key = 0;
	}
	public BSTFreqNode(String word, int f) {
		wordlist = new FList();
		wordlist.addLast(word);
		key = f; //frecuencia
	}

	public boolean isInternal() {
		return (left != null || right != null);
	}
	
	public boolean isExternal() {
		return (left == null && right == null) ;
	}
	
	public boolean isRoot() {
		return (parent == null);
	}

	public int getSize() {
		return getSize(this);
	}

	private static int getSize(BSTFreqNode node) {
		if (node == null)
			return 0;
		else
			return 1 + getSize(node.left) + getSize(node.right);
	}

	public int getHeight() {
		return getHeight(this);
	}

	private static int getHeight(BSTFreqNode node) {
		if (node == null)
			return -1;
		else
			return 1 + Math.max(getHeight(node.left),
					getHeight(node.right));
	}

	public int getDepth() {
		return getDepth(this);
	}

	private static  int getDepth(BSTFreqNode node) {
		if (node == null)
			return -1;
		else
			return 1 + getDepth(node.parent);
	}
	
	public BSTFreqNode getRoot() {
		if (parent == null) {
			return this;
		} else {
			return parent.getRoot();
		}
	}
	
	public BSTFreqNode getRootIterativo() {
		BSTFreqNode root=this;
		if (root!=null) {				
			while (root.parent != null) 
				root=root.parent;				
		}
		return root;
	}		
	
	public void getPreorder() {		
		getPreorder(this);
	}
	
	public void getInorder() {
		getInorder(this);
	}
	
	public void getPostorder() {
		getPostorder(this);
	}
	
	private static void getPreorder(BSTFreqNode node) {
		if (node == null) return;
		
		System.out.println("("+node.key+"; "+node.wordlist.toString()+")");
		getPreorder(node.left);
		getPreorder(node.right);
	}
	
	private static  void getInorder(BSTFreqNode node) {
		if (node == null) return;
		
		getInorder(node.left);
		System.out.println("("+node.key+"; "+node.wordlist.toString()+")");
		getInorder(node.right);
	}
	
	private static  void getPostorder(BSTFreqNode node) {
		if (node == null) return;
		getPostorder(node.left);
		getPostorder(node.right);
		System.out.println("("+node.key+"; "+node.wordlist.toString()+")");
	}

}

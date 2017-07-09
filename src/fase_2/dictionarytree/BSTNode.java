package fase_2.dictionarytree;

public class BSTNode {

	public String key;
	public int frec;

	BSTNode parent;
	BSTNode left;
	BSTNode right;

	
	public BSTNode(String k, int f) {
		key = k;
		frec = f;
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

	private static int getSize(BSTNode node) {
		if (node == null)
			return 0;
		else
			return 1 + getSize(node.left) + getSize(node.right);
	}

	public int getHeight() {
		return getHeight(this);
	}

	private static int getHeight(BSTNode node) {
		if (node == null)
			return -1;
		else
			return 1 + Math.max(getHeight(node.left),
					getHeight(node.right));
	}

	public int getDepth() {
		return getDepth(this);
	}

	private static  int getDepth(BSTNode node) {
		if (node == null)
			return -1;
		else
			return 1 + getDepth(node.parent);
	}
	
	public BSTNode getRoot() {
		if (parent == null) {
			return this;
		} else {
			return parent.getRoot();
		}
	}
	
	public BSTNode getRootIterativo() {
		BSTNode root=this;
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
	
	private static void getPreorder(BSTNode node) {
		if (node == null) return;
		
		System.out.println("("+node.key+","+node.frec+")");
		getPreorder(node.left);
		getPreorder(node.right);
	}
	
	private static  void getInorder(BSTNode node) {
		if (node == null) return;
		
		getInorder(node.left);
		System.out.println("("+node.key+","+node.frec+")");
		getInorder(node.right);
	}
	
	private static  void getPostorder(BSTNode node) {
		if (node == null) return;
		getPostorder(node.left);
		getPostorder(node.right);
		System.out.println("("+node.key+","+node.frec+")");
	}


}

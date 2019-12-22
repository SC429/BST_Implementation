public class BSTIndex {

	private class Node {
		private String key;
		private MovieInfo data;
		private Node left, right;

		public Node(MovieInfo info) {
			data = info;
			key = data.shortTitle;
		}
	}

	private Node root;

	public BSTIndex() {
		root = null;
	}

	// --------- [DO NOT MODIFY!] public BST methods  --------- //
	/* Important: Notice that each public method here calls another private method while passing it a reference to the tree root. This is a common way of structuring BST functions such that external client code will not have direct access to the tree root. You will be implementing the code in the private methods, not the public ones. */

	/* Calculate and return the height of the current tree. */
	public int calcHeight(){
			return calcNodeHeight(this.root);
	}

	/* Insert the given data element into the proper place in the BST structure. */
	public void insertMovie(MovieInfo data) {
		root = insertMovie(data, this.root);
	}

	/* Find and return the data element (i.e. the MovieInfo object)
	of the node where the movie's shortTitle matches the given key.
	Return null if the movie is not found. */
	public MovieInfo findMovie(String key) {
		return findMovie(this.root, key);
	}

	/* Print out all movies in the database whose shortTitle begins with
	the passed prefix string. If no movies match the given prefix, nothing
	will be printed. The order of printing does not matter, but make sure
	to print each match in a separate line. */
	public void printMoviesPrefix(String prefix) {
		printMoviesPrefix(this.root, prefix);
	}
	// ----------------- end of public methods ------------------ //


	// ------------- private BST methods --------------- //
	private int calcNodeHeight(Node t) { // calculate the height of the tree
		if (t == null) { //base case
			return 0;
		}
		
		int leftside = calcNodeHeight(t.left); //iterate through the left subtree
		
		int rightside = calcNodeHeight(t.right); //iterate through the right subtree
		
		if(rightside >= leftside) {
			return (rightside + 1);
		}
		
		return (leftside + 1);
	}

	private Node insertMovie(MovieInfo data, Node t) { //insert new movie data
		if (t == null) { //base case
			Node newMovie = new Node(data);
			return newMovie;
		}
		if (data.shortTitle.compareToIgnoreCase(t.key) < 0) { // left subtree
			t.left =  insertMovie(data, t.left);
		}
		else if (data.shortTitle.compareToIgnoreCase(t.key) > 0) { // right subtree
			t.right = insertMovie(data, t.right);
		}
		return t;
	}

	private MovieInfo findMovie(Node t, String key) {
		if (key.compareToIgnoreCase(t.key) < 0) { // compare for left subtree
			return findMovie(t.left, key);
		}
		else if (key.compareToIgnoreCase(t.key) > 0) { // compare for right subtree
			return findMovie(t.right, key);
		}
		return t.data;
	}

	private void printMoviesPrefix(Node t, String prefix) {
		if (t == null) { // if t is empty
			return;
		}
		else {
			String sub;
			String pre;
			if (prefix.length() - 1 > t.key.length()) { //prefix is longer than the movie
				return;
			}
			else {
				if (prefix.length() - 1 == 0) { // accounts for only *
					return;
				}
				else if (prefix.length() - 1 == 1) { // accounts for the * in the prefix
					sub = t.key.substring(0, 1);
					pre = prefix.substring(0, 1);
				}
				else {
					sub = t.key.substring(0, prefix.length() - 1);
					pre = prefix.substring(0, prefix.length()-1);
				}
			if (sub.compareToIgnoreCase(pre) == 0) { // if the prefix matches the movie's prefix
				System.out.println(t.data.fullTitle);
			}
			printMoviesPrefix(t.left, prefix); // left subtree
			printMoviesPrefix(t.right, prefix); // right subtree
			}
		}
	}
}

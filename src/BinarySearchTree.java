public class BinarySearchTree {
    Node root;

    public void insert(Node node) {
        root = insertHelper(root, node);
    }

    private Node insertHelper(Node root, Node node) {
        int data = node.data;

        if (root == null) {
            root = node;
        } else if (data < root.data) {
            root.left = insertHelper(root.left, node);
        } else if (data > root.data) {
            root.right = insertHelper(root.right, node);
        }

        return root;
    }

    public void display() {
        displayHelper(root);
    }

    private void displayHelper(Node root) {
        if (root != null) {
            displayHelper(root.left);
            System.out.println(root.data);
            displayHelper(root.right);
        }
    }

    public boolean search(int data) {
        return searchHelper(root, data);
    }

    private boolean searchHelper(Node root, int data) {
        if (root == null) {
            return false;
        } else if (data == root.data) {
            return true;
        } else if (data < root.data) {
            return searchHelper(root.left, data);
        } else {
            return searchHelper(root.right, data);
        }
    }

    public void remove(int data) {
        if (search(data)) {
            removeHelper(root, data);
        }
    }

    private Node removeHelper(Node root, int data) {
        if (root == null) {
            return root;
        } else if (data < root.data) {
            root.left = removeHelper(root.left, data);
        } else if (data > root.data) {
            root.right = removeHelper(root.right, data);
        } else { // root.data == data (node found)
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.data = successor(root); // find the smallest value below the right child of this root and replace the removed data
                root.right = removeHelper(root.right, root.data); // remove the replaced data original node
            } else {
                root.data = predecessor(root); // find the largest value below the left child of this root and replace the removed data
                root.left = removeHelper(root.left, root.data); // remove the replaced data original node
            }
        }

        return root;
    }

    private int successor(Node root) {  // find the smallest value below the right child of this root
        root = root.right; // get the right child of this root
        while (root.left != null) { // and loop left child of the right child and find the last left child
            root = root.left;
        }

        return root.data;
    }

    private int predecessor(Node root) {
        root = root.left;
        if (root.right != null) {
            root = root.right;
        }

        return root.data;
    }
}

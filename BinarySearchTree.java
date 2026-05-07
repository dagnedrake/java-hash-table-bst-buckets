/**
 * Binary search tree implementation for Integer values.
 *
 * This class is currently unfinished and preserved as an early draft.
 *
 * @author Dagne Drake
 */
public class BinarySearchTree extends BinaryTree
{
    /**
     * This class represents the result of removing
     * a node from a binary tree.
     */
    private class RemovalResult
    {
        Node node;
        Node tree;

        RemovalResult(Node node, Node tree)
        {
            this.node = node;
            this.tree = tree;
        }
    }

    /**
     * Public add method adds a value to the tree.
     *
     * @param x value to add to the tree
     * @return true
     */
    public boolean add(Integer x)
    {
        if (root == null)
            root = new Node(x);
        else
            root = add(x, root);

        return true;
    }

    /**
     * Contains method checks to see if a value
     * is in the binary search tree.
     *
     * @param x value for which to check
     * @return true if x is in the tree, false otherwise
     */
    public boolean contains(Integer x)
    {
        return contains(x, root);
    }

    /**
     * Private add method adds a value to the search tree.
     *
     * @param x value to add
     * @param bstree root of the BST
     * @return root of the resulting BST
     */
    private Node add(Integer x, Node bstree)
    {
        if (bstree == null)
            return new Node(x);

        if (x < bstree.value)
        {
            bstree.left = add(x, bstree.left);
        }
        else
        {
            bstree.right = add(x, bstree.right);
        }

        return bstree;
    }

    /**
     * Private contains method checks whether an item is in a BST.
     *
     * @param x item for which to check
     * @param bstree root of the BST
     * @return true if found, false otherwise
     */
    private boolean contains(Integer x, Node bstree)
    {
        if (bstree == null)
            return false;

        if (x.equals(bstree.value))
            return true;

        if (x < bstree.value)
            return contains(x, bstree.left);

        return contains(x, bstree.right);
    }

    /**
     * Remove method removes a value from the BST.
     *
     * @param x value to remove
     * @return true if x was removed, false if x was not found
     */
    public boolean remove(Integer x)
    {
        RemovalResult result = remove(root, x);

        if (result == null)
            return false;

        root = result.tree;
        return true;
    }

    /**
     * Private remove method removes a value from a BST
     * and returns the removed node and remaining tree.
     *
     * @param bTree binary search tree
     * @param x value to be removed
     * @return null if x is not found in bTree
     */
    private RemovalResult remove(Node bTree, Integer x)
    {
        if (bTree == null)
            return null;

        if (x < bTree.value)
        {
            RemovalResult result = remove(bTree.left, x);

            if (result == null)
                return null;

            bTree.left = result.tree;
            result.tree = bTree;
            return result;
        }

        if (x > bTree.value)
        {
            RemovalResult result = remove(bTree.right, x);

            if (result == null)
                return null;

            bTree.right = result.tree;
            result.tree = bTree;
            return result;
        }

        if (bTree.right == null && bTree.left == null)
            return new RemovalResult(bTree, null);

        if (bTree.right != null && bTree.left != null)
        {
            RemovalResult remResult = removeLargest(bTree.left);
            Node newRoot = remResult.node;

            newRoot.left = remResult.tree;
            newRoot.right = bTree.right;

            bTree.left = null;
            bTree.right = null;

            return new RemovalResult(bTree, newRoot);
        }

        Node node = bTree;
        Node tree;

        if (bTree.left != null)
            tree = bTree.left;
        else
            tree = bTree.right;

        node.left = null;
        node.right = null;

        return new RemovalResult(node, tree);
    }

    /**
     * RemoveLargest method removes the largest node from a BST.
     *
     * @param bTree BST root
     * @return result of removing the largest node
     */
    private RemovalResult removeLargest(Node bTree)
    {
        if (bTree == null)
            return null;

        if (bTree.right == null)
        {
            Node tree = bTree.left;
            bTree.left = null;
            return new RemovalResult(bTree, tree);
        }

        RemovalResult remResult = removeLargest(bTree.right);
        bTree.right = remResult.tree;
        remResult.tree = bTree;
        return remResult;
    }

    /**
     * Method to find smallest item in subtree.
     *
     * @return node value containing smallest item
     */
    public Integer findMin()
    {
        if (root != null)
            return findMin(root).value;

        return null;
    }

    /**
     * Helper method to find smallest item in subtree.
     *
     * @param t root node of subtree
     * @return node containing the smallest item
     */
    private Node findMin(Node t)
    {
        if (t == null)
            return null;

        if (t.left == null)
            return t;

        return findMin(t.left);
    }

    /**
     * Method to count number of elements in BST.
     *
     * @return count
     */
    public Integer countTraversal()
    {
        return countTraversal(root);
    }

    /**
     * Private helper method for countTraversal.
     *
     * @param root node root
     * @return count
     */
    private Integer countTraversal(Node root)
    {
        if (root == null)
            return 0;

        return countTraversal(root.left) + 1 + countTraversal(root.right);
    }

    /**
     * Method to add elements in BST to a string.
     *
     * @param str StringBuilder string
     * @return string
     */
    public StringBuilder stringBuilderTraversal(StringBuilder str)
    {
        stringBuilderTraversal(root, str);
        return str;
    }

    /**
     * Private helper method for stringBuilderTraversal.
     *
     * @param root node root
     * @param str StringBuilder string
     * @return string
     */
    private StringBuilder stringBuilderTraversal(Node root, StringBuilder str)
    {
        if (root != null)
        {
            stringBuilderTraversal(root.left, str);
            str.append(root.value).append(" ");
            stringBuilderTraversal(root.right, str);
        }

        return str;
    }
}
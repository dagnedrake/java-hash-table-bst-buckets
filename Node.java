/**
 * Basic node class for binary tree structures.
 */
public class Node
{
    int value;
    Node left;
    Node right;

    /**
     * Constructor for leaf nodes.
     *
     * @param val node value
     */
    Node(int val)
    {
        value = val;
        left = null;
        right = null;
    }

    /**
     * Constructor for non-leaf nodes.
     *
     * @param val node value
     * @param leftChild left child node
     * @param rightChild right child node
     */
    Node(int val, Node leftChild, Node rightChild)
    {
        value = val;
        left = leftChild;
        right = rightChild;
    }
}
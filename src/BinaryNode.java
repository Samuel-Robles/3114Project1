
/**
 * This class represents a node in a BST. It holds information (such as left and
 * right neighbors)
 * and can be modified
 * 
 * @author Sam Robles <robleshs>
 * @author John Hoskinson <johnh98>
 * @version 9/21/19
 * 
 * @param <T>
 *            the type held in the node
 */
public class BinaryNode<T> {
    private T value;
    private BinaryNode<T> left;
    private BinaryNode<T> right;


    /**
     * Constructor, creates a node with no children.
     * 
     * @param dataValue
     *            the type-less value to store in this node.
     */
    BinaryNode(T dataValue) {
        value = dataValue;
        left = null;
        right = null;
    }


    /**
     * Get the current data value stored in this node.
     * 
     * @return the value
     */
    public T getValue() {
        return value;
    }


    /**
     * Set the data value stored in this node.
     * 
     * @param newValue
     *            the new data value to set
     */
    public void setValue(T newValue) {
        value = newValue;
    }


    /**
     * Get the left child of this node.
     * 
     * @return a reference to the left child.
     */
    public BinaryNode<T> getLeft() {
        return left;
    }


    // ----------------------------------------------------------
    /**
     * Set this node's left child.
     * 
     * @param valueHeld
     *            the node to point to as the left child.
     */
    public void setLeft(BinaryNode<T> valueHeld) {
        left = valueHeld;
    }


    // ----------------------------------------------------------
    /**
     * Get the right child of this node.
     * 
     * @return a reference to the right child.
     */
    public BinaryNode<T> getRight() {
        return right;
    }


    // ----------------------------------------------------------
    /**
     * Set this node's right child.
     * 
     * @param valueHeld
     *            the node to point to as the right child.
     */
    public void setRight(BinaryNode<T> valueHeld) {
        right = valueHeld;
    }


    /**
     * Provides an in-order representation of the tree
     * 
     * @return a string representation of the tree in list form
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (left != null) {
            builder.append(left.toString() + " \n");
        }
        builder.append(value.toString());
        if (right != null) {
            builder.append(" \n" + right.toString());
        }
        return builder.toString();
    }
}

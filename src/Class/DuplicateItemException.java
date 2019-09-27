package Class;

// -------------------------------------------------------------------------
/**
 * An exception for an add called on an item already in a tree
 */
public class DuplicateItemException extends RuntimeException {
    /**
     * The version ID of the error
     */
    private static final long serialVersionUID = 1L;


    /**
     * The basic exception with no message
     */
    public DuplicateItemException() {
        super();
    }


    /**
     * An exception with a custom message to inform the user
     * 
     * @param message
     *            the error message (Likely what item was duplicated,
     *            possibly its position or data)
     */
    public DuplicateItemException(String message) {
        super(message);
    }
}

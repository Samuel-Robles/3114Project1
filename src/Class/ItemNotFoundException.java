package Class;
//-------------------------------------------------------------------------
/**
 * Exception for a search or remove called on a target that does not exist
 */
public class ItemNotFoundException
    extends RuntimeException
{

    /**
     * The version ID of the error
     */
    private static final long serialVersionUID = 1L;

    /**
     * The exception in basic form
     */
    public ItemNotFoundException()
    {
        super();
    }

    /**
     * The exception with a custom message to display in case of an error
     * @param message the error message.
     */
    public ItemNotFoundException(String message)
    {
        super(message);
    }
}

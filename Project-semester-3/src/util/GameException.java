package util;

/**
 *
 * @author frederic
 */
public class GameException
        extends RuntimeException
{
    
    public GameException()
    {
        super();
    }
    
    public GameException(String message)
    {
        super(message);
    }
    
    public GameException(Throwable t)
    {
        super(t);
    }
    
    public GameException(String message, Throwable t)
    {
        super(message, t);
    }
    
}

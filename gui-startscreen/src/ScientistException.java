/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arne
 */
public class ScientistException extends RuntimeException {
    
    public ScientistException()
    {
        super();
    }
    
    public ScientistException(String message)
    {
        super(message);
    }
    
    public ScientistException(String message, Throwable t)
    {
        super(message, t);
    }
    
    public ScientistException(Throwable t)
    {
        super(t);
    }
}

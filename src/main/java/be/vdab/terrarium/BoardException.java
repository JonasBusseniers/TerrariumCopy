
package be.vdab.terrarium;

public class BoardException extends Exception
{
	private static final long serialVersionUID = 1L;

	public BoardException() {}
    
    public BoardException(String omschrijving)
    {
        super ("BoardException: " + omschrijving);
    }
}

package api.exceptions;

public class ConnectionException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8996459987957580772L;
	private Exception exception;
	private String problem;
	
	public ConnectionException(String msg, Exception e)
	{
		super(msg);
		this.exception = e;
	}
	
	public ConnectionException(String msg, String o)
	{
		super(msg);
		this.problem = o;
	}
	
	public ConnectionException(String msg)
	{
		super(msg);
	}	
	
	public Exception getException()
	{
		return exception;
	}
	
	public String getProblem()
	{
		return problem;
	}
}

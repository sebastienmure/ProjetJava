import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public abstract class SupaLogga
{
	private static Logger logga = null;
	private static FileHandler fh = null;
	
	public static void log(String msg)
	{
		if(logga == null)
			logga = Logger.getLogger("MyLog");
		
		
		try
		{
			if(fh == null)
				fh = new FileHandler("./MyLogFile.log");
			
	        // This block configure the logger with handler and formatter  
	        logga.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
	        logga.info(msg);  
	    }
		catch (SecurityException e)
		{  
			e.printStackTrace();
	    }
		catch (IOException e)
		{
	        e.printStackTrace();  
	    }
	}
}

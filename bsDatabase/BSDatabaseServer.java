package bsDatabase;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class BSDatabaseServer {

	public BSDatabaseServer(String c, String t) throws IOException
	  {
	    

	    // Get the command line arguments.
	    String command = c;
	    String type = t;
	    
	    // Create the database object.
	    Database database = new Database();
	    
	    // Execute a query if Q was specified.
	    if (type.equals("Q"))
	    {
	      // Do the query.
	      ArrayList<String> result = database.query(command);
	     
	      // Print the result.
	      if (result != null)
	      {
	        for (String row : result)
	        {
	          System.out.println(row);
	        }
	      }
	      else
	      {
	        System.out.println("Error executing query.");
	      }
	    }
	    
	    // Execute DML if D was specified.
	    else if (type.equals("D"))
	    {
	      // Run the DML.
	      try
	      {
	         database.executeDML(command);
	       }
	       catch(SQLException sql)
	       {
	           System.out.println("Error executing DML.");
	        }

	    }
	  }
}
	


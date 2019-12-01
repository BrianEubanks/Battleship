package bsDatabase;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class BSDatabaseServer {

	ArrayList<String> result = new ArrayList<String>();
	
	public BSDatabaseServer(String c, String t) throws IOException
	  {
	    

	    // Get the command line arguments.
	    String command = c;
	    String type = t;
	    System.out.println(command);
	    
	    // Create the database object.
	    Database database = new Database();
	    
	    // Execute a query if Q was specified.
	    if (type.equals("Q"))
	    {
	      // Do the query.
	      result = database.query(command);
	     
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
	        result = new ArrayList<String>();
	        result.add("");
	        result.add("");
	        result.add("");
	        
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

	public boolean validateUser(String username, String password) {
		System.out.println("validateUser");
		System.out.println("validateUser: "+password);
		System.out.println("validateUser: "+result.get(2));
		if(result.get(2).equals(password)){
			return true;
		}
		return false;
	}
}
	


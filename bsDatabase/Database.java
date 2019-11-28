package bsDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

//import com.mysql.jdbc.Connection;

public class Database
{
  private Connection con;
  //Add any other data fields you like  at least a Connection object is mandatory
  FileInputStream fis;
  Statement stmt;
  ResultSet rs;
  ResultSetMetaData rmd;
  Properties prop;
  String url;
  String pass;
  String user;


  public Database() throws IOException
  {
    //Add your code here
	    
	  try {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_space","student","hello");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	    //Read properties file
	    prop = new Properties();
	    fis = new FileInputStream("bsDatabase/db.properties");
	    prop.load(fis);
	    
		  url = prop.getProperty("url");
		  user = prop.getProperty("user");
		  pass = prop.getProperty("password");  

	  
  }
  
  public ArrayList<String> query(String query)
  {
    //Add your code here

	  
	    try
	    {
	      //Read the connection properties as Strings
	    
	      
	      //Perform the Connection
	      con = DriverManager.getConnection(url,user,pass);
	    
	      //Create a statement
	      stmt=con.createStatement();  
	    
	      //Execute a query
	      rs=stmt.executeQuery("select * from user");  
	      
	      //Get metadata about the query
	      rmd = rs.getMetaData();
	      
	      //Get the # of columns
	      int no_columns = rmd.getColumnCount();
	    
	      //Get a column name
	      String name = rmd.getColumnName(1);
	      
	      //Fetch each row (use numeric numbering
	      while(rs.next()) 
	      {
	        System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
	      }
	      
	      con.close();  
	    
	      System.out.println("Success");
	    } 
	    catch (SQLException e)
	    {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } 
	  
	return null;
  }
  
  public void executeDML(String dml) throws SQLException
  {
    //Add your code here
      //Perform the Connection
      con = DriverManager.getConnection(url,user,pass);
    
      //Create a statement
      stmt=con.createStatement();  
      
      //Execute a DML statement
      //stmt.execute("insert into test1 values('Jim','1215 Main','5014503111')");
  }
  
}
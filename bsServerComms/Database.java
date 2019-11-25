package bsServerComms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import bsClientComms.User;

public class Database {
	
	  private ArrayList<User> userdblist;
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

	public Database() throws IOException{
		//userdb = new ArrayList();
		//Add your code here
	    
	    //Read properties file
	    prop = new Properties();
	    fis = new FileInputStream("bsServerComms/db.properties");
	    prop.load(fis);
	    
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		pass = prop.getProperty("password");  
	}

	public ArrayList<String> query(String query)
	  {
	    //Add your code here
		//ArrayList<String> tables = new ArrayList();
		  
		    try
		    {
		      //Read the connection properties as Strings
		    
		      
		      //Perform the Connection
		      con = DriverManager.getConnection(url,user,pass);
		    
		      //Create a statement
		      stmt=con.createStatement();  
		    
		      //Execute a query
		      rs=stmt.executeQuery("select * from test1");  
		      
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
		        
		        userdblist.add(new User(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3)));
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
		//return tables;
	  }
	  
	public void executeDML(String dml) throws SQLException
	{
	    //Add your code here
	      //Perform the Connection
	      con = DriverManager.getConnection(url,user,pass);
	    
	      //Create a statement
	      stmt=con.createStatement();  
	      
	      //Execute a DML statement
	      stmt.execute("insert into test1 values('Jim','1215 Main','5014503111')");
	}	
	
	public User getUser(int i) {
		return userdblist.get(i);
	}
	
	
	
	public boolean addUser(User u) throws SQLException {
		if (userdblist.contains(u)) {
			return false;
		}else {
			executeDML(new String("insert into test1 values('"+Integer.toString(u.getID())+"','"+u.getUsername()+"','"+u.getPassword()+"')"));
			query(""); //update userdblist
			return true;
		}
	}
	public boolean validateUser(User us) {
		for (User u : userdblist) {
			if(u.getUsername().equals(us.getUsername())){
				if(u.getPassword().equals(us.getPassword())){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean verifyUsername(String usern)
	{
		for (User u : userdblist)
		{
			if(u.getUsername().equals(usern));
				return false;
		}
		return true;
	}
}

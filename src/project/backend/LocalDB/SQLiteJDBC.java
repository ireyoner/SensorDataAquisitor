package project.backend.LocalDB;

import java.sql.SQLException;

import localDB.LocalDataBaseMenager;
import project.data.Datagram;

public class SQLiteJDBC
{
  public static void main( String args[] )
  {
	  LocalDataBaseMenager mm = new LocalDataBaseMenager();
	  try {
//		mm.SetupDataBase();
//		mm.SaveDatagram(new Datagram("dadada"));
//		mm.SaveDatagram(new Datagram("dasdads"));
		  for(Datagram d : mm.GetDatagramsToSend()){
			  System.out.println(d.getId() + " : "+d.getData());
		  }
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
//    Connection c = null;
//    Statement stmt = null;
//    try {
//      Class.forName(Settings.getString("sqliteJDBC"));
//      c = DriverManager.getConnection("jdbc:sqlite:"+Settings.getString("BDPath"));
//      System.out.println("Opened database successfully");
//
//      c.close();
//    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//      System.exit(0);
//    }
    System.out.println("Table created successfully");
  }
}
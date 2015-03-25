package project.backend.LocalDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import project.Settings;

public class SQLiteJDBC
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName(Settings.getString("sqliteJDBC"));
      c = DriverManager.getConnection("jdbc:sqlite:"+Settings.getString("BDPath"));
      System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE DATAGRAMS( "
					+ " ID             INTEGER    PRIMARY KEY AUTOINCREMENT,"
					+ " DATA           CHAR(128)  NOT NULL)";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE SEND_HISTORY( "
					+ " ID             INTEGER   PRIMARY KEY AUTOINCREMENT,"
					+ " DATAGRAM_ID    INTEGER   NOT NULL,"
					+ " SEND_OK        BOOLEAN   NOT NULL DEFAULT FALSE,"
					+ " SEND_DATE      DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP)";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE DATAGRAMS_HISTORY( "
					+ " DATAGRAM_ID    INTEGER   NOT NULL UNIQUE,"
					+ " INSERT_DATE    DATETIME  DEFAULT CURRENT_TIMESTAMP)";
			stmt.executeUpdate(sql);

			sql = "CREATE TRIGGER \"ins_to_DGR_HIST\" AFTER INSERT ON \"DATAGRAMS\" "
					+ "BEGIN "
					+ "INSERT INTO DATAGRAMS_HISTORY(DATAGRAM_ID) VALUES (NEW.ID);"
					+ "END";
			stmt.executeUpdate(sql);

			sql = "CREATE TRIGGER \"del_from_DGR_HIST\" AFTER DELETE ON \"DATAGRAMS\" "
					+ "BEGIN "
					+ "INSERT INTO SEND_HISTORY(DATAGRAM_ID,SEND_OK) VALUES (OLD.ID,'TRUE');"
					+ "END";
			stmt.executeUpdate(sql);
			stmt.close();

      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
}
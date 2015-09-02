package project.backend.LocalDB;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import project.Settings;
import project.data.Datagram;

public class LocalSQLiteMenager {
	Connection c;

	private Connection getConnection() throws SQLException {
		if (c != null) {
			return c;
		} else {
			c = DriverManager.getConnection("jdbc:sqlite:"
					+ Settings.getString("BDPath"));
			return c;
		}
	}

	protected boolean setupDB() throws Exception {
		if (testBDExists() == false) {
			Statement stmt = getConnection().createStatement();
//			String sql = "CREATE TABLE DATAGRAMS( "
//					+ " ID             INTEGER    PRIMARY KEY AUTOINCREMENT,"
//					+ " DATA           CHAR(128)  NOT NULL)";
//			stmt.executeUpdate(sql);
//
//			sql = "CREATE TABLE SEND_HISTORY( "
//					+ " ID             INTEGER   PRIMARY KEY AUTOINCREMENT,"
//					+ " DATAGRAM_ID    INTEGER   NOT NULL,"
//					+ " SEND_OK        BOOLEAN   NOT NULL DEFAULT FALSE,"
//					+ " SEND_DATE      DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,"
//					+ " ERROR          CHAR(200) DEFAULT NULL)";
//			stmt.executeUpdate(sql);
//
//			sql = "CREATE TABLE DATAGRAMS_HISTORY( "
//					+ " DATAGRAM_ID    INTEGER   NOT NULL UNIQUE,"
//					+ " INSERT_DATE    DATETIME  DEFAULT CURRENT_TIMESTAMP)";
//			stmt.executeUpdate(sql);
//
//			sql = "CREATE TRIGGER \"ins_to_DGR_HIST\" AFTER INSERT ON \"DATAGRAMS\" "
//					+ "BEGIN "
//					+ "INSERT INTO DATAGRAMS_HISTORY(DATAGRAM_ID) VALUES (NEW.ID);"
//					+ "END";
//			stmt.executeUpdate(sql);
//
//			sql = "CREATE TRIGGER \"del_from_DGR_HIST\" AFTER DELETE ON \"DATAGRAMS\" "
//					+ "BEGIN "
//					+ "INSERT INTO SEND_HISTORY(DATAGRAM_ID,SEND_OK) VALUES (OLD.ID,'TRUE');"
//					+ "END";
//			stmt.executeUpdate(sql);
//			stmt.close();

			return testBDExists();
		}
		throw new Exception("LocalDBExists!");
	}

	protected boolean testBDExists() throws ClassNotFoundException {
		Class.forName(Settings.getString("sqliteJDBC"));

		File BDFile = new File(Settings.getString("BDPath"));

		if (BDFile.exists())
		{
			return true;
		} else {
			return false;
		}
	}

	public boolean saveDatagram(Datagram datagram)
			throws ClassNotFoundException, SQLException {
		if (testBDExists() == true) {
			String sql = "INSERT INTO DATAGRAMS(DATA) VALUES (?)";

			CallableStatement cs = getConnection().prepareCall(sql);
			cs.setString(1, datagram.getData());
			cs.execute();
			cs.close();

			return true;
		}
		return false;
	}

	public boolean deleteDatagram(Datagram datagram)
			throws ClassNotFoundException, SQLException {
		if (testBDExists() == true && datagram.getId() != null) {
			String sql = "DELETE FROM DATAGRAMS WHERE ID = (?)";

			CallableStatement cs = getConnection().prepareCall(sql);
//			cs.setLong(1, datagram.getId());
			cs.execute();
			cs.close();

			return true;
		} else {
			return false;
		}
	}

	public boolean reportSendErrorForDatagram(Datagram datagram, String error)
			throws ClassNotFoundException, SQLException {
		if (testBDExists() == true && datagram.getId() != null) {
			String sql = "INSERT INTO SEND_HISTORY(DATAGRAM_ID,ERROR) VALUES (?,?)";

			CallableStatement cs = getConnection().prepareCall(sql);
//			cs.setLong(1, datagram.getId());
			cs.setString(2, error.substring(0, 200));
			cs.execute();
			cs.close();

			return true;
		} else {
			return false;
		}
	}

}

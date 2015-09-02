package localDB;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import project.Settings;
import project.data.Datagram;

public class LocalDataBaseMenager {
	
	private Connection c;
	
	private String DBPatch;

	private String getDBPatch() {
		return DBPatch;
	}

	private void setDBPatch(String dBPatch) {
		DBPatch = dBPatch;
	}
	
	public LocalDataBaseMenager() {
		this.setDBPatch(Settings.getString("DBPath"));
	}
	
	public Connection getConnection() throws SQLException {
		if (c != null) {
			return c;
		} else {
			c = DriverManager.getConnection("jdbc:sqlite:"
					+ getDBPatch());
			return c;
		}
	}

	protected boolean testBDExists() throws ClassNotFoundException {
		Class.forName(Settings.getString("sqliteJDBC"));
		File BDFile = new File(getDBPatch());
		if (BDFile.exists())
		{
			return true;
		} else {
			return false;
		}
	}

	public void SetupDataBase() throws ClassNotFoundException, SQLException{
		if (testBDExists() == false) {
			new SetupDB(getConnection());
		}
	}

	public boolean SaveDatagram(Datagram datagram)
			throws Exception {
		if (testBDExists() == true) {
			String sql = "INSERT INTO Datagrams(MESSAGE) VALUES (?)";
			PreparedStatement cs = getConnection().prepareStatement(sql);
			cs.setString(1, datagram.getData());
			cs.execute();
			
			cs = getConnection().prepareStatement(
					"select last_insert_rowid()");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				datagram.setId(rs.getBigDecimal(1));
			}
			rs.close();
			
			sql = "INSERT INTO Datagram_statistics(datagram_id) VALUES (?)";
			cs = getConnection().prepareStatement(sql);
			cs.setBigDecimal(1, datagram.getId());
			cs.execute();
			
			cs.close();
			return true;
		}
		return false;
	}

	public Set<Datagram> GetDatagramsToSend() 
			throws ClassNotFoundException, SQLException {
		Set<Datagram> datagrams = new HashSet<Datagram>();
		if (testBDExists() == true) {
			PreparedStatement cs = getConnection().prepareStatement(
					"select * from Datagrams");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				BigDecimal id = rs.getBigDecimal(1);
				String data = rs.getString(2);
				datagrams.add(new Datagram(id, data));
			}
			rs.close();
			cs.close();
		}
		return datagrams;
	}

}

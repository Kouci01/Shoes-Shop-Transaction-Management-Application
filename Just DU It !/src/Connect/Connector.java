package Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Connector {
	private static Connection connection;
	
	public static Connection connect() {
		if (connection == null) {
			MysqlDataSource source = new MysqlDataSource();
			source.setServerName("localhost");
			source.setUser("root");
			source.setPassword("");
			source.setDatabaseName("justduit");
			try {
				connection = source.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return connection;
	}
}

package oa.util;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.ibatis.common.resources.Resources;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class CreateMySqlDatabase {

	public static void createDatabase() throws SQLException {
		Connection conn;
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "1234");
		Statement stmt = (Statement) conn.createStatement();
		String sql = "CREATE DATABASE UTILITY";
		stmt.executeUpdate(sql);
	}
	
	
	public static void executeSql() throws IOException, SQLException {
		Properties props = Resources.getResourceAsProperties("mysql.properties");
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		Connection conn = (Connection) DriverManager.getConnection(url, username, password);
		ScriptRunner runner = new ScriptRunner(conn);
		runner.setErrorLogWriter(null);
		runner.setLogWriter(null);
		runner.runScript(Resources.getResourceAsReader("sql/utility.sql"));
		conn.close();
		System.out.println("==SUCCESS==");
	}
}

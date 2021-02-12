package ru.ItzDarth.DarthRPG.api.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLAPI {
  
	public static MySQL connect(String host, int port, String database, String user, String password) {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL("jdbc:mysql://"+host+":"+port+"/"+database+"?autoReconnect=true&useSSL=false&jdbcCompliantTruncation=false");
		ds.setUser(user);
		ds.setPassword(password);
		return new MySQL(ds);
	}
}


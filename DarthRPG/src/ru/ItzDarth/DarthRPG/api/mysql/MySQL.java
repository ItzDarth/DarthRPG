package ru.ItzDarth.DarthRPG.api.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import ru.ItzDarth.DarthRPG.api.mysql.handlers.AbstractHandler;
import ru.ItzDarth.DarthRPG.api.mysql.handlers.DeleteHandler;
import ru.ItzDarth.DarthRPG.api.mysql.handlers.InsertHandler;
import ru.ItzDarth.DarthRPG.api.mysql.handlers.SelectHandler;
import ru.ItzDarth.DarthRPG.api.mysql.handlers.UpdateHandler;

public class MySQL {
	private static ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private AsyncMySQL async;
	private Connection connection;
	private MysqlDataSource ds;
	private Stats stats;
	
	public MySQL(MysqlDataSource ds) {
		this.ds = ds;
		this.stats = new Stats();
		this.async = new AsyncMySQL(this, executor);
		connect();
	}
	
	public MySQL(MysqlDataSource ds, Connection connection) {
		this.ds = ds;
		this.stats = new Stats();
		this.connection = connection;
	}
	
	public AsyncMySQL async() {
		return this.async;
	}
  
	public Stats getStats() {
		return this.stats;
	}
	
	private Connection connect() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				return this.connection;
			}
			this.connection = this.ds.getConnection();
			this.stats.online = System.currentTimeMillis();
			return this.connection;
		} catch (SQLException e) {
			e.printStackTrace();
			this.stats.online = 0L;
			return null;
		} 
	}
	
	
	private boolean checkConnection() {
		try {
			if(this.connection == null && this.connection.isClosed()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void insert(String query, InsertHandler handler, Object... args) {
		checkConnection();
		if(handler == null) {
			handler = (rs -> {});
		}
		try(PreparedStatement ps = fill(this.connection.prepareStatement(query, 1), args)) {
			ps.executeUpdate();
			try(ResultSet rs = ps.getGeneratedKeys()) {
				handler.execute(rs.next() ? rs.getInt(1) : -1);
			}
			this.stats.queryInsert++;
		} catch (SQLException e) {
			handler.handleException(e);
		} 
	}
	
	public void update(String query, UpdateHandler handler, Object... args) {
		checkConnection();
		if(handler == null) {
			handler = (() -> {});  
		}
		try(PreparedStatement ps = fill(this.connection.prepareStatement(query), args)) {
			ps.executeUpdate();
			handler.execute();
			this.stats.queryUpdate++;
		} catch (SQLException e) {
			handler.handleException(e);
		} 
	}
	
	public void delete(String query, DeleteHandler handler, Object... args) {
		checkConnection();
		if(handler == null) {
			handler = (() -> {});  
		}
		try(PreparedStatement ps = fill(this.connection.prepareStatement(query), args)) {
			ps.executeUpdate();
			handler.execute();
			this.stats.queryDelete++;
		} catch (SQLException e) {
			handler.handleException(e);
		} 
	}
	
	public void select(String query, SelectHandler handler, Object... args) {
		checkConnection();
		if(handler == null) {
			handler = (rs -> {});  
		}
		try(PreparedStatement ps = fill(this.connection.prepareStatement(query), args)) {
			try(ResultSet rs = ps.executeQuery()) {
				handler.execute(rs);
			} 
			this.stats.querySelect++;
		} catch(SQLException e) {
			handler.handleException(e);
		} 
	}
	
	public void execute(String query) {
		checkConnection();
		AbstractHandler handler = new AbstractHandler() {  };
		try(PreparedStatement ps = this.connection.prepareStatement(query)) {
			ps.execute();
		} catch (SQLException e) {
			handler.handleException(e);
		} 
	}
	
	private PreparedStatement fill(PreparedStatement ps, Object... args) throws SQLException {
		for(int i = 0; i < args.length; i++) {
			ps.setObject(i + 1, args[i]); 
		}
		return ps;
	}
  
	public Connection getConnection() {
		return this.connection;
	}
	
	public class Stats {
		private long online;
		private long querySelect;
		private long queryUpdate;
		private long queryInsert;
		private long queryDelete;
    
		public long getQueryDelete() {
			return this.queryDelete;
		}
    
		public long getQueryInsert() {
			return this.queryInsert;
		}
    
		public long getOnline() {
			return this.online;
		}
    
		public long getQuerySelect() {
			return this.querySelect;
		}
    
		public long getQueryUpdate() {
			return this.queryUpdate;
		}
    
		public long getQueryTotal() {
			return this.querySelect + this.queryUpdate + this.queryInsert + this.queryDelete;
		}
	}
  
	public class AsyncMySQL {
		
		private MySQL MySQL;
		private ExecutorService exec;
    
		public AsyncMySQL(MySQL MySQL, ExecutorService exec) {
			this.MySQL = MySQL;
			this.exec = exec;
		}
    
		public void insert(String query, InsertHandler handler, Object... args) {
			this.exec.submit(() -> this.MySQL.insert(query, handler, args));
		}
    
		public void execute(String query) {
			this.exec.submit(() -> this.MySQL.execute(query));
		}
    
		public void update(String query, UpdateHandler handler, Object... args) {
			this.exec.submit(() -> this.MySQL.update(query, handler, args));
		}
    
		public void select(String query, SelectHandler handler, Object... args) {
			this.exec.submit(() -> this.MySQL.select(query, handler, args));
		}
    
		public void delete(String query, DeleteHandler handler, Object... args) {
			this.exec.submit(() -> this.MySQL.delete(query, handler, args));
		}
	}
	
}
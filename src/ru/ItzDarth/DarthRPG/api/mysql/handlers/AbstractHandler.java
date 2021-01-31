package ru.ItzDarth.DarthRPG.api.mysql.handlers;

import java.sql.SQLException;

public interface AbstractHandler {
	
	default void handleException(SQLException e) {
		e.printStackTrace();
	}
	
	default void debug(String string) {
		System.out.println(string);
	}
	
}
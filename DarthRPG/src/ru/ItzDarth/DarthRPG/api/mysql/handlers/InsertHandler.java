package ru.ItzDarth.DarthRPG.api.mysql.handlers;

import java.sql.SQLException;

public interface InsertHandler extends AbstractHandler {
	
	void execute(int paramInt) throws SQLException;
	
}
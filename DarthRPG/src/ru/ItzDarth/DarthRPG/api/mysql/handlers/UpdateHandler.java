package ru.ItzDarth.DarthRPG.api.mysql.handlers;

import java.sql.SQLException;

public interface UpdateHandler extends AbstractHandler {
	
	void execute() throws SQLException;
	
}
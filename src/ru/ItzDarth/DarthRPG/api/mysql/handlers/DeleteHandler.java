package ru.ItzDarth.DarthRPG.api.mysql.handlers;

import java.sql.SQLException;

public interface DeleteHandler extends AbstractHandler {
	
	void execute() throws SQLException;
	
}
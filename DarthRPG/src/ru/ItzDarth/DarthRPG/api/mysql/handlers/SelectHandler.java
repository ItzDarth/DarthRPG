package ru.ItzDarth.DarthRPG.api.mysql.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SelectHandler extends AbstractHandler {
	
	void execute(ResultSet paramResultSet) throws SQLException;
	
}
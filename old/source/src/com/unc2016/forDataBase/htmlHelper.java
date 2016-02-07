package com.unc2016.forDataBase;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class htmlHelper {
	public static String printRow(String... data){
		StringBuffer stringBuffer = new StringBuffer("<tr>");
			for(int i=0; i<data.length; i++)
				stringBuffer.append("<td>").append(data[i]).append("</td>");
		stringBuffer.append("</tr>");
		return stringBuffer.toString();
	}
	
	private static String displayTheColumns(ResultSetMetaData rsmd) throws SQLException{
		StringBuffer buffer = new StringBuffer("<tr>");
		for(int i=1; i<=rsmd.getColumnCount(); i++)
			buffer.append("<th>").append(rsmd.getColumnName(i)).append("</th>");
		buffer.append("</tr>");
		return buffer.toString();
	}
	
	public static String createTable(ResultSet resultSet) throws SQLException{
		StringBuffer buffer = new StringBuffer("<table>");
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		buffer.append(displayTheColumns(resultSetMetaData));
		while(resultSet.next()){	
			String[] row = new String[resultSetMetaData.getColumnCount()];
			for(int i=1; i<=row.length; i++)
				row[i-1] = resultSet.getString(i);
			buffer.append(printRow(row));
		}
		buffer.append("</table>");
		return buffer.toString();
	}
}
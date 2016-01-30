package com.test.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class htmlHelper {
	public static String printRow(Object... data){
		StringBuffer stringBuffer = new StringBuffer("<tr>");
		if(data != null){
			//for(Object object: data){
			for(int i=0; i<data.length; i++){
				stringBuffer.append("<td>");
				stringBuffer.append(data[i]);
				//stringBuffer.append(object.toString());
				stringBuffer.append("</td>");
			}
		}
		stringBuffer.append("</tr>");
		return stringBuffer.toString();
	}
	
	
	public static String printRow(Boolean a, String... data){//устаревший метод
		StringBuffer stringBuffer = new StringBuffer("<tr>");
			//for(Object object: data){
		for(int i=0; i<data.length;i++){
				stringBuffer.append("<td>");
				stringBuffer.append(data[i]);
				//stringBuffer.append(object.toString());
				stringBuffer.append("</td>");
			}
		stringBuffer.append("</tr>");
		return stringBuffer.toString();
	}
	
	/*public static String printRow(Boolean isHeader, Object... data){
		StringBuffer stringBuffer = new StringBuffer("<tr>");
		if(data != null){
			for(Object object: data){
				stringBuffer.append("<td>");
				stringBuffer.append(object.toString());
				stringBuffer.append("</td>");
			}
		}
		stringBuffer.append("</tr>");
		return stringBuffer.toString();
	}*/
	
	
	private static String displayTheColumns(ResultSetMetaData rsmd) throws SQLException{
		StringBuffer buffer = new StringBuffer("<tr>");
		for(int i=1; i<=rsmd.getColumnCount(); i++){
			buffer.append("<th>");
			buffer.append(rsmd.getColumnName(i));
			buffer.append("</th>");
		}
		buffer.append("</tr>");
		return buffer.toString();
	}
	
	public static String createTable(ResultSet resultSet) throws SQLException{
		StringBuffer buffer = new StringBuffer("<table>");
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		buffer.append(displayTheColumns(resultSetMetaData));//Columns name
		while(resultSet.next()){
			Object[] row = new Object[resultSetMetaData.getColumnCount()];	
			for(int i=1; i<=row.length; i++){
				row[i-1] = resultSet.getString(i);
			}
			buffer.append(printRow(row));
		}
		buffer.append("</table>");
		return buffer.toString();
	}
}
package com.test.jdbc;


public class htmlHelper {
	public static String printRow(boolean isHeader, Object... data){
		StringBuffer stringBuffer = new StringBuffer("<table><tr>");//потом убрать table
		if(data != null){
			for(Object object: data){
				stringBuffer.append("<th>");
				stringBuffer.append(object.toString());
				stringBuffer.append("</th>");
			}
		}
		stringBuffer.append("</tr></table>");
		return stringBuffer.toString();
	}
}
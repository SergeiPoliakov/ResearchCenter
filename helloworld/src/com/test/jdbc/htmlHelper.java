package com.test.jdbc;


public class htmlHelper {

	
	protected static String countUser(Object data){
		int dataint =(Integer) data;
		if(dataint>0 && dataint<300)
			return dataint+" ";
		else
			return 42+" ";
	}
	
	public static String printErr(String err){
		//глупо, но пусть пока так.
		return err;
	}
}

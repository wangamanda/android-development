package com.cloudeeva.expenses.constants;

public class AppConstants {

	/**
	 * Returns the login urls for specified username and password 
	 * @param pUserName Username enteredby the user
	 * @param pPassword Password entered by the user
	 * @return
	 */
	public static String getLoginURL(String pUserName, String pPassword) {
		return "http://146.145.105.252/ServiceHRMS/HrmsService.svc/web/LoginUser/"+pUserName+"/"+pPassword;
	}
}

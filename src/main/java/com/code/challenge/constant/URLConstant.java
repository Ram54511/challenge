package com.code.challenge.constant;

public class URLConstant {
	private URLConstant() {
	}

	public static final String BASEURL = "/api/v1";
	public static final String REGISTER = BASEURL + "/register";
	public static final String LOGIN = BASEURL + "/login";
	public static final String FIND_EMAIL = BASEURL + "/findByEmail/";
	public static final String FIND_LASTNAME = BASEURL + "/findByLastname/";
	public static final String FIND_FIRSTNAME = BASEURL + "/findByFirstname/";
	public static final String UPDATE = BASEURL + "/updateUser";
	public static final String FIND_USERNAME = BASEURL + "/findByUsername";
	public static final String DELETE_BY_ID = BASEURL + "/deleteById";

}

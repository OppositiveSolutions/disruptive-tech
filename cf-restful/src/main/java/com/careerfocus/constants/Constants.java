package com.careerfocus.constants;

public class Constants {

	public static int TRUE = 1;
	public static int FALSE = 0;

	public static final int SESSION_MAX_INACTIVE_TIMEOUT_WEBAPPP = 30 * 60; // 30
																			// minutes
	public static final int SESSION_MAX_INACTIVE_TIMEOUT_MOBILEAPP = 0 * 60; // never
																				// expires

	public static final String RESTFUL_PATH_PREFIX = "/cf-restful";

	public static final int ROLE_STUDENT = 1;
	public static final int ROLE_SUPER_ADMIN = 2;
	public static final int ROLE_BRANCH_ADMIN = 3;
	public static final int ROLE_STAFF = 4;

	public static final String CF_EMAIL_ID = "career.focus@ymail.com";
	
	public static final int DEFAULT_CENTER_ID = 1;
	
	public static final int IS_LIVE = 0;
	
	public static final int STUDENT_ACTIVE = 1;
	public static final int STUDENT_INACTIVE = 0;
	public static final int STUDENT_DELETED = 2;
	
	public static final int STUDENT_ADDED = 1;
	public static final int STUDENT_REGISTERED = 2;
	public static final int STUDENT_REG_AND_ONCE_ACTIVE = 3;

}

package com.atlas.k12netframe.utils.userSelection;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

public class AsistoUserReferences {
	
	public static String DATA_FILE_PATH = Environment.getExternalStorageDirectory() + "/MobiDers/context/";
	public static final String IMG_FILE_PATH = DATA_FILE_PATH + "temp_img/";
	public static String FILE_ENCODING_CHARSET = "UTF-8";

    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";

	private static final String SETTINGS_FILE_NAME = "userSettings";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
    private static final String LANGAUGE = "language";
	private static final String CONNECTION_ADDRESS = "connectionAddress";
    private static final String FILE_SERVER_ADDRESS = "fileServerAddress";
	private static final String REMEMBER_PASSWORD = "rememberPassword";
	private static final String LIGHT_OPTION = "lightOption";
    private static final String CALENDAR_PROVIDER_ID = "calendarProviderId";
    private static final String SYNC_CALENDAR = "syncCalendar";
	private static AsistoUserReferences references = null;

	private SharedPreferences settings;
	private String username;
	private String password;
	private String connectionString;
    private String fileServerAddress;
    private String appRegisterId;
    private int appVersionNo;
	private boolean rememberPassword;
    private int calendarProviderId;
    private int syncCalendar;
    private String languageCode;
	
	public AsistoUserReferences(Context context) {
		settings = context.getSharedPreferences(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
		username = settings.getString(USERNAME, null);
		password = settings.getString(PASSWORD, null);
		connectionString = settings.getString(CONNECTION_ADDRESS, "https://okul.k12net.com");
        fileServerAddress = settings.getString(FILE_SERVER_ADDRESS, "fs.k12net.com/FS/");
		rememberPassword = settings.getBoolean(REMEMBER_PASSWORD, false);
        appRegisterId = settings.getString(PROPERTY_REG_ID, "");
        appVersionNo = settings.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        calendarProviderId = settings.getInt(CALENDAR_PROVIDER_ID, 1);
        syncCalendar = settings.getInt(SYNC_CALENDAR, 0);
        languageCode = settings.getString(LANGAUGE, "en");
	}

	public static void initUserReferences(Context context) {
		references = new AsistoUserReferences(context);
	}

    public static String getAsistoRegisterId() {
        return references.appRegisterId;
    }

    public static int getAsistoVersionNo() {
        return references.appVersionNo;
    }

    private void storeString(String key, String value) {
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	private void storeBoolean(String key, boolean value) {
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

    private void storeInt(String key, int value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }
	
	public static String getConnectionAddress(){
		String http_address = references.connectionString;
		if(http_address.startsWith("http") == false) {
			http_address = "http://" + http_address;
		}
		return http_address;
	}
	
	public static String getUsername(){
		return references.username;
	}
	
	public static String getPassword(){
		return references.password;
	}
	public static void setConnectionAddress(String conAddress){
		references.connectionString = conAddress;
		references.storeString(CONNECTION_ADDRESS, references.connectionString);
	}

	public static void setUsername(String username) {
		references.username = username;
		references.storeString(USERNAME, references.username);
	}

	public static void setPassword(String password) {
		references.password = password;
		references.storeString(PASSWORD, references.password);
	}

    public static void increaseCalendarId() {
        references.calendarProviderId++;
        references.storeInt(CALENDAR_PROVIDER_ID, references.calendarProviderId);
    }

	public static boolean getRememberMe() {
		return references.rememberPassword;
	}

    public static int getCalendarId() {
        return references.calendarProviderId;
    }

    public static int getSyncCalendar() {
        return references.syncCalendar;
    }
	
	public static void setRememberMe(boolean rememberMe) {
		references.rememberPassword = rememberMe;
		references.storeBoolean(REMEMBER_PASSWORD, references.rememberPassword);
	}

    public static String getFileServerAddress(){
        String http_address = references.fileServerAddress;
        if(http_address.startsWith("http") == false) {
            http_address = "https://" + http_address;
        }
        return http_address;
    }

    public static void setFileServerAddress(String conAddress){
        references.fileServerAddress = conAddress;
        references.storeString(FILE_SERVER_ADDRESS, references.fileServerAddress);
    }

    public static void setAsistoRegisterId(String appRegisterId) {
        references.appRegisterId = appRegisterId;
        references.storeString(PROPERTY_REG_ID, references.appRegisterId);
    }

    public static void setAsistoVersionNo(int appVersionNo) {
        references.appVersionNo = appVersionNo;
        references.storeInt(PROPERTY_APP_VERSION, references.appVersionNo);
    }

    public static void setLanguage(String languageCode) {
        references.languageCode = languageCode;
        references.storeString(LANGAUGE, references.languageCode);
    }

    public static String getLanguageCode(){
        return references.languageCode;
    }

}

package automationFramework;

public class Config {
	
	public static String fol_failedScreenshotPath =System.getProperty("user.dir")+"/Failed_Screenshots";
	public static String fol_passScreenshotPath =System.getProperty("user.dir")+"/Pass_Screenshots";
	public static String fol_jasonFilePath=System.getProperty("user.dir")+"/DataFiles/TestData.json";
	public static String fol_downloadFilePath=System.getProperty("user.dir")+"\\Downloads";
	//if getting error then change to System.getProperty("user.dir")+"/Dummy_Test_File"+"/dummy.doc";
	public static String fol_dummyTestDocFilePath=System.getProperty("user.dir")+"\\Dummy_Test_File"+"\\dummy.doc";
	//public static String fol_dummyTestDocFolderPath=System.getProperty("user.dir")+"/Dummy_Test_File";
	public static String fol_HolidayjsonFilePath=System.getProperty("user.dir")+"./DataFiles/HolidayCalendar.json";
}

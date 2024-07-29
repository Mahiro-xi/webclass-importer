package wc;

public class osChecker {
    public static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("win");
    public static final boolean IS_MAC = System.getProperty("os.name").toLowerCase().startsWith("mac");
    public static final boolean IS_LINUX = System.getProperty("os.name").toLowerCase().startsWith("linux");
    
	public static String whos(){
		if(IS_WINDOWS) {
			return "win".toString();
		}else if(IS_MAC) {
			return "mac".toString();
		}else {
			return "linux".toString();
		}
		
	}
}

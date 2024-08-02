
package wc;

public class main {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Webclass-importer");
		if (!DependsChecker.checkAll()[1]) {
			PrepareDepends.download("chrome",VersionChecker.getVer());
		}
		
		if (!DependsChecker.checkAll()[2]) {
			PrepareDepends.download("chromedriver", VersionChecker.getVer());
			
		}
		
	}
}

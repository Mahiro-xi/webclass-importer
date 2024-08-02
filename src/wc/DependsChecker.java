package wc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DependsChecker {
	static boolean init() throws IOException {
		Path p = Paths.get( "./depends");
		
		if (Files.exists(p)) {
			return true;
		} else {
			Files.createDirectory(p);
			return true;
		}
		
	}
	
	static boolean[] checkList = {false,false,false};
	public static void checkdo(Path webdriver,Path chrome) throws Exception{
		// Chromeの存在確認
		if(Files.exists(chrome)) {
			checkList[1] = true;
			
		}
		
		// WebDriverの存在確認
		if(Files.exists(webdriver)) {
			checkList[2] = true;
		}
	}
	
	// Chrome, WebDriver二点セット確認くん
	public static boolean[] checkAll() throws Exception {
		if (OsChecker.whos().equals("win")) {
			Path webdriver = Paths.get("./depends/chromedriver-win64/chromedriver.exe");
			Path chrome = Paths.get("./depends/chrome-win64/chrome.exe");
			checkdo(webdriver, chrome);
		}else{
			Path webdriver = Paths.get("./depends/webdriver");
			Path chrome = Paths.get("./depends/chrome");
			checkdo(webdriver, chrome);
		}
		
		// 依存関係の実行ファイルが入ってるディレクトリの存在
		if(init() == true) {
			checkList[0] = true;
		}
		
		return checkList;
	}
}

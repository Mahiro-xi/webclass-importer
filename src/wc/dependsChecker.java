package wc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class dependsChecker {
	static boolean init() throws IOException {
		Path p = Paths.get( "./depends");
		
		if (Files.exists(p)) {
			return true;
		} else {
			Files.createDirectory(p);
			return true;
		}
		
	}
	
	// Chrome, WebDriver二点セット確認くん
	public static boolean[] checkAll() throws IOException {
		Path webdriver = Paths.get("./webdriver.exe");
		Path chrome = Paths.get("./chrome.exe");
		boolean[] checkList = {false,false,false};
		
		// 依存関係の実行ファイルが入ってるディレクトリの存在
		if(init() == true) {
			checkList[0] = true;
		}
		
		// Chromeの存在確認
		if(Files.exists(chrome)) {
			checkList[1] = true;
		}
		
		// WebDriverの存在確認
		if(Files.exists(webdriver)) {
			checkList[2] = true;
		}
		
		return checkList;
	}
}

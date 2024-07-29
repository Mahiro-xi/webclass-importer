
package wc;

import java.io.IOException;

public class main {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Webclass-importer");
		for(int i=0;i<3;i++) {
			System.out.println(dependsChecker.checkAll()[i]);
		}
//		prepareDepends.prepare(dependsChecker.checkAll());
		System.out.println(osChecker.whos());
		
		dependsDownloader.someget();
	}

}

package wc;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class PrepareDepends {
	public static void download(String req, String ver) throws IOException {
		
		try {
			URI uri = new URI("https://storage.googleapis.com/chrome-for-testing-public/"+ver+"/win64/"+req.toLowerCase()+"-win64.zip");
			URL url = uri.toURL();
			HttpURLConnection conn =
					(HttpURLConnection) url.openConnection();
			conn.setAllowUserInteraction(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.connect();
			
			int httpStatusCode = conn.getResponseCode();
			if (httpStatusCode != HttpURLConnection.HTTP_OK) {
				throw new Exception("HTTP Status" + httpStatusCode);
			}
			
			String contentType = conn.getContentType();
			System.out.println("Content-Type: "+contentType);
			
			DataInputStream dataInStream
				= new DataInputStream(
						conn.getInputStream());
			
			DataOutputStream dataOutStream
				= new DataOutputStream(
						new BufferedOutputStream (
								new FileOutputStream("./depends/"+req+".zip")));
			
	      byte[] b = new byte[4096];
	      int readByte = 0;

	      while (-1 != (readByte = dataInStream.read(b))) {
	        dataOutStream.write(b, 0, readByte);
	      }
			
			dataInStream.close();
			dataOutStream.close();
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ProtocolException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		
		unzip(req);
	}
	
	public static void unzip(String s) throws IOException {
		var target = Paths.get("./depends/");
		Files.createDirectories(target);
		
		var zipfile = Paths.get("./depends/"+s+".zip");
		try (var in = new ZipInputStream(Files.newInputStream(zipfile))) {
				ZipEntry e;
				while ((e = in.getNextEntry()) != null) {
					var dst = Paths.get(target.toString(), e.getName());
					Files.createDirectories(dst.getParent());
					Files.write(dst, in.readAllBytes());
					System.out.printf("inflating: %s%n",dst);
				}
		}
	}
}

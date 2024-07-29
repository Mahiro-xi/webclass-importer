package wc;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class dependsDownloader {
	
	public static void someget() throws IOException {
		ObjectMapper objectmapper = new ObjectMapper();
		JsonNode json = objectmapper.readTree(get());
//		@SuppressWarnings("deprecation")
//		URL url = new URL("https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json");
		
//        Object obj = ObjectMapper.readValue(url, Object.class); -> Error code.

        // マッピングされたオブジェクトを使用
		String version = json.get("version").textValue();
		System.out.println(version);
	}
	
//	public static void chromeDL() {}
	
	
	public static String get() {
		String url = "https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();
			
		try {
			HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
//			System.out.println(res);
//			System.out.println(res.body());
			return res.body();
		} catch ( IOException | InterruptedException ex ) {
			ex.printStackTrace();
			return "invalid";
		}
	}
}	
package wc;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class prepareDepends {
	public static void prepare() {
		String url = "https://googlechromelabs.github.io/chrome-for-testing/#stable";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();
		
		try {
			HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
			System.out.println(res);
			System.out.println(res.body());
		} catch ( IOException | InterruptedException ex ) {
			ex.printStackTrace();
		}
	}
}

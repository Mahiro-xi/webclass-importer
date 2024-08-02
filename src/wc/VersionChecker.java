package wc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VersionChecker {

	
	@JsonIgnoreProperties(ignoreUnknown = true)
	    private String timestamp;
	    private Map<String, Channel> channels;

	    public String getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(String timestamp) {
	        this.timestamp = timestamp;
	    }

	    public Map<String, Channel> getChannels() {
	        return channels;
	    }

	    public void setChannels(Map<String, Channel> channels) {
	        this.channels = channels;
	    }
	
	    public static StringBuffer getjson() throws Exception {
	        URI uri = new URI("https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions.json");
	        URL url = uri.toURL();
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.connect();
	        
	        int responseCode = conn.getResponseCode();
	        if (responseCode != 200) {
	            throw new RuntimeException("HttpResponseCode: " + responseCode);
	        } else {
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String inputLine;
	            StringBuffer content = new StringBuffer();
	            while ((inputLine = in.readLine()) != null) {
	                content.append(inputLine);
	            }
	            
	            in.close();
	            conn.disconnect();
	            return content;
	        }
	    }
	
	public static String getVer() throws Exception {
			try {
	            ObjectMapper mapper = new ObjectMapper();
	            String jsonString = getjson().toString();
	            
	            VersionChecker downloader = mapper.readValue(jsonString, VersionChecker.class);

//	            System.out.println("Timestamp: " + downloader.getTimestamp());
//	            System.out.println("Stable Channel Version: " + downloader.getChannels().get("Stable").getVersion());
	            return downloader.getChannels().get("Stable").getVersion().toString();
	           
		  } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		  			}
	}
}
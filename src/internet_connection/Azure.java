package internet_connection;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import c_connection.Node;

public class Azure {
	
    private static final String subscriptionKey = "fb2468dd4de448cd9a851f36365b8217";
    private static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze";
    private static String imageToAnalyze;
    private JsonArray tags;
    private List<Integer> confidence;
    
    public Azure(String image) {
    	
    	imageToAnalyze = image;
    	CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
        	
            URIBuilder builder = new URIBuilder(uriBase);
            builder.setParameter("visualFeatures", "Tags");
            builder.setParameter("language", "en");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            StringEntity requestEntity = new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
            request.setEntity(requestEntity);
          
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                
                String jsonString = EntityUtils.toString(entity);
                System.out.println(jsonString); //Es para ver que es lo que tiene todo el json
                       		
                JsonParser parser = new JsonParser();
                Object obj = parser.parse(jsonString);
                JsonObject jsonObject = (JsonObject) obj;
                	       
                tags = (JsonArray) jsonObject.get("tags");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	public List<Tag> getTags() {
		Gson gson=new Gson();
		
		List<Tag> listTags = new ArrayList<Tag>();
		
		for (int posTag = 0; posTag < tags.size(); posTag++) {
			JsonObject tagFromJson = (JsonObject) tags.get(posTag);			
			Tag tagObtained = gson.fromJson(tagFromJson, Tag.class);
			listTags.add(tagObtained);
			System.out.println(tagObtained.getName());
		}
		
		return listTags;
	}

	public List<Integer> getConfidence() {
		return confidence;
	}
}
